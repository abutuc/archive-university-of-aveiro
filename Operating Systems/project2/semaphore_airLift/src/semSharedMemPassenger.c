/**
 *  \file semSharedMemPassenger.c (implementation file)
 *
 *  \brief Problem name: Air Lift
 *
 *  Synchronization based on semaphores and shared memory.
 *  Implementation with SVIPC.
 *
 *  Definition of the operations carried out by the passengers:
 *     \li travelToAirport
 *     \li waitInQueue
 *     \li waitUntilDestination
 *
 *  \author Nuno Lau - January 2022
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <sys/types.h>
#include <string.h>
#include <math.h>

#include "probConst.h"
#include "probDataStruct.h"
#include "logging.h"
#include "sharedDataSync.h"
#include "semaphore.h"
#include "sharedMemory.h"

/** \brief logging file name */
static char nFic[51];

/** \brief shared memory block access identifier */
static int shmid;

/** \brief semaphore set access identifier */
static int semgid;

/** \brief pointer to shared memory region */
static SHARED_DATA *sh;

static bool travelToAirport();
static void waitInQueue(unsigned int passengerId);
static void waitUntilDestination(unsigned int passengerId);
static void leavePlane(unsigned int passengerId);

/**
 *  \brief Main program.
 *
 *  Its role is to generate the life cycle of one of intervening entities in the problem: the passenger.
 */

int main(int argc, char *argv[])
{
    int key;    /*access key to shared memory and semaphore set */
    char *tinp; /* numerical parameters test flag */
    int n;

    /* validation of command line parameters */

    if (argc != 5)
    {
        freopen("error_PG", "a", stderr);
        fprintf(stderr, "Number of parameters is incorrect!\n");
        return EXIT_FAILURE;
    }
    else
        freopen(argv[4], "w", stderr);

    n = (unsigned int)strtol(argv[1], &tinp, 0);
    if ((*tinp != '\0') || (n >= N))
    {
        fprintf(stderr, "Passenger process identification is wrong!\n");
        return EXIT_FAILURE;
    }
    strcpy(nFic, argv[2]);
    key = (unsigned int)strtol(argv[3], &tinp, 0);
    if (*tinp != '\0')
    {
        fprintf(stderr, "Error on the access key communication!\n");
        return EXIT_FAILURE;
    }

    /* connection to the semaphore set and the shared memory region and mapping the shared region onto the
       process address space */

    if ((semgid = semConnect(key)) == -1)
    {
        perror("error on connecting to the semaphore set");
        return EXIT_FAILURE;
    }
    if ((shmid = shmemConnect(key)) == -1)
    {
        perror("error on connecting to the shared memory region");
        return EXIT_FAILURE;
    }
    if (shmemAttach(shmid, (void **)&sh) == -1)
    {
        perror("error on mapping the shared region on the process address space");
        return EXIT_FAILURE;
    }

    srandom((unsigned int)getpid()); /* initialize random generator */

    /* simulation of the life cycle of the passenger */

    travelToAirport();
    waitInQueue(n);
    waitUntilDestination(n);

    /* unmapping the shared region off the process address space */

    if (shmemDettach(sh) == -1)
    {
        perror("error on unmapping the shared region off the process address space");
        return EXIT_FAILURE;
        ;
    }

    return EXIT_SUCCESS;
}

/**
 *  \brief passenger goes to airport
 *
 *  The passenger takes a random time to reach the airport
 */

static bool travelToAirport()
{
    usleep((unsigned int)floor((MAXTRAVEL * random()) / RAND_MAX + 1000));

    return true;
}

/**
 *  \brief wait for its turn to be checked by hostess
 *
 *  Passenger should update number of passenger in queue, and inform hostess that he is ready for boarding
 *  after being acknowledged by hostess passenger should provide its id to hostess and giver her permission to read the id
 *  The internal state should be saved twice.
 *
 *  \param passengerId passenger id
 */

static void waitInQueue(unsigned int passengerId)
{   
    /* enter critical region */
    if (semDown(semgid, sh->mutex) == -1)
    {
        perror("error on the down operation for semaphore access (PG)");
        exit(EXIT_FAILURE);
    }

    sh->fSt.nPassInQueue++;                           // incrementa o número de passageiros que estão na fila de espera
    sh->fSt.st.passengerStat[passengerId] = IN_QUEUE; // atualiza o estado do passageiro
    saveState(nFic, &sh->fSt);                        // regista o estado do passageiro

    /* exit critical region */
    if (semUp(semgid, sh->mutex) == -1) 
    {
        perror("error on the up operation for semaphore access (PG)");
        exit(EXIT_FAILURE);
    }

    // Sinaliza à hospedeira que já há passageiros na fila de espera
    if (semUp(semgid, sh->passengersInQueue) == -1)
    {
        perror("error on the up operation for semaphore access (PG)");
        exit(EXIT_FAILURE);
    }
    
    // aguarda na fila de espera até ser atendido pela hospedeira
    if (semDown(semgid, sh->passengersWaitInQueue) == -1)
    {
        perror("error on the down operation for semaphore access (PG)");
        exit(EXIT_FAILURE);
    }

    // começa o check-in do passageiro
    /* enter critical region */
    if (semDown(semgid, sh->mutex) == -1)
    {
        perror("error on the down operation for semaphore access (PG)");
        exit(EXIT_FAILURE);
    }

        sh->fSt.passengerChecked = passengerId;            // o passageiro fornece o seu id
        sh->fSt.st.passengerStat[passengerId] = IN_FLIGHT; // entra no aviao
        saveState(nFic, &sh->fSt);                         // regista o estado

    
    /* exit critical region */
    if (semUp(semgid, sh->mutex) == -1)
    { 
        perror("error on the up operation for semaphore access (PG)");
        exit(EXIT_FAILURE);
    }

    // sinaliza à hospedeira que mostrou o ID e assim pode entrar no avião, finalizando o check-in
    if (semUp(semgid, sh->idShown))
    {
        perror("error on the up operation for semaphore access (PG)");
        exit(EXIT_FAILURE);
    }
}

/**
 *  \brief passenger waits for flight to terminate and arrives at destination.
 *
 *  passenger should wait for flight end, update the number of passengers in flight and
 *  arrive at destination.
 *  last passenger must inform pilot that plane is empty.
 *  The internal state should be saved.
 *
 *  \param passengerId passenger id
 */

static void waitUntilDestination(unsigned int passengerId)
{
    // sinaliza ao piloto que está a aguardar no avião
    semDown(semgid, sh->passengersWaitInFlight);

    /* enter critical region */
    if (semDown(semgid, sh->mutex) == -1)
    {
        perror("error on the down operation for semaphore access (PG)");
        exit(EXIT_FAILURE);
    }

    sh->fSt.st.passengerStat[passengerId] = AT_DESTINATION;     // o passageiro chegou ao seu destino
    sh->fSt.nPassInFlight--;                                    // e consequentemente sai do avião

    // caso o passageiro observe que é o ultimo a sair do aviáo, então avisa ao piloto que o avião encontra-se vazio
    if (sh->fSt.nPassInFlight == 0)
    {
        if (semUp(semgid, sh->planeEmpty) == -1)
        {
            perror("error on the up operation for semaphore access (PG)");
            exit(EXIT_FAILURE);
        }
    }

    /* enter critical region */
    if (semUp(semgid, sh->mutex) == -1)
    {
        perror("error on the up operation for semaphore access (PG)");
        exit(EXIT_FAILURE);
    }
}
