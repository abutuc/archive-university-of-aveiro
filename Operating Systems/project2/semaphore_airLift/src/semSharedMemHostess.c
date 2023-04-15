/**
 *  \file semSharedMemHostess.c (implementation file)
 *
 *  \brief Problem name: Air Lift.
 *
 *  Synchronization based on semaphores and shared memory.
 *  Implementation with SVIPC.
 *
 *  Definition of the operations carried out by the hostess:
 *     \li waitForNextFlight
 *     \li waitForPassenger
 *     \li checkPassport
 *     \li signalReadyToFlight
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

/** \brief hostess waits for next flight */
static void waitForNextFlight();

/** \brief hostess waits for passenger */
static void waitForPassenger();

/** \brief hostess checks passport */
static bool checkPassport();

/** \brief hostess signals boarding is complete */
static void signalReadyToFlight();

/** \brief getter for number of passengers flying */
static int nPassengersInFlight();

/** \brief getter for number of passengers waiting */
static int nPassengersInQueue();

/**
 *  \brief Main program.
 *
 *  Its role is to generate the life cycle of one of intervening entities in the problem: the hostess.
 */

int main(int argc, char *argv[])
{
    int key;    /*access key to shared memory and semaphore set */
    char *tinp; /* numerical parameters test flag */

    /* validation of command line parameters */

    if (argc != 4)
    {
        freopen("error_HT", "a", stderr);
        fprintf(stderr, "Number of parameters is incorrect!\n");
        return EXIT_FAILURE;
    }
    else
        freopen(argv[3], "w", stderr);

    strcpy(nFic, argv[1]);
    key = (unsigned int)strtol(argv[2], &tinp, 0);
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

    /* simulation of the life cycle of the hostess */

    int nPassengers = 0;
    bool lastPassengerInFlight;

    while (nPassengers < N)
    {
        waitForNextFlight();
        do
        {
            waitForPassenger();
            lastPassengerInFlight = checkPassport();
            nPassengers++;
        } while (!lastPassengerInFlight);
        signalReadyToFlight();
    }

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
 *  \brief wait for Next Flight.
 *
 *  Hostess updates its state and waits for plane to be ready for boarding
 *  The internal state should be saved.
 *
 */

static void waitForNextFlight()
{   
    /* enter critical region */
    if (semDown(semgid, sh->mutex) == -1)
    {                                                                          
        perror("error on the down operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }

    sh->fSt.st.hostessStat = WAIT_FOR_FLIGHT; // muda o estado da hospedeira para WAIT_FOR_FLIGHT
    saveState(nFic, &sh->fSt);                // regista a mudança do estado

    /* exit critical region */
    if (semUp(semgid, sh->mutex) == -1) 
    {
        perror("error on the up operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }

    // espera que o piloto sinalize que já pode começar o boarding
    if (semDown(semgid, sh->readyForBoarding) == -1)
    {
        perror("error on the down operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    } 
}

/**
 *  \brief hostess waits for passenger
 *
 *  hostess waits for passengers to arrive at airport.
 *  The internal state should be saved.
 */

static void waitForPassenger()
{
    /* enter critical region */
    if (semDown(semgid, sh->mutex) == -1) 
    {
        perror("error on the down operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }

    sh->fSt.st.hostessStat = WAIT_FOR_PASSENGER; // muda o estado da hospedeira para WAIT_FOR_PASSENGER
    saveState(nFic, &sh->fSt);                  // guarda o estado

    /* exit critical region */
    if (semUp(semgid, sh->mutex) == -1)
    { 
        perror("error on the up operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }

    // Espera que os passageiros chegam à fila de espera
    if (semDown(semgid, sh->passengersInQueue) == -1)
    {
        perror("error on the down operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }
}

/**
 *  \brief passport check
 *
 *  The hostess checks passenger passport and waits for passenger to show id
 *  The internal state should be saved twice.
 *
 *  \return should be true if this is the last passenger for this flight
 *    that is:
 *      - flight is at its maximum capacity
 *      - flight is at or higher than minimum capacity and no passenger waiting
 *      - no more passengers
 */

static bool checkPassport()
{
    bool last;

    // atende um passageiro
    if (semUp(semgid, sh->passengersWaitInQueue) == -1)
    {
        perror("error on the up operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }

    /* enter critical region */
    if (semDown(semgid, sh->mutex) == -1)
    {                                                                       
        perror("error on the down operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }


    sh->fSt.st.hostessStat = CHECK_PASSPORT; // atualiza o estado da hospedeira para CHECK_PASSAPORT
    saveState(nFic, &sh->fSt);               // guarda o estado

    /* exit critical region */
    if (semUp(semgid, sh->mutex) == -1)
    {                                                                      
        perror("error on the up operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }

    // espera que o passageiro forneça o ID
    if (semDown(semgid, sh->idShown) == -1)
    {
        perror("error on the down operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }
    /* enter critical region */
    if (semDown(semgid, sh->mutex) == -1)
    {                                                                       
        perror("error on the down operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }

    sh->fSt.nPassInQueue--;               // decrementa a fila de espera
    sh->fSt.nPassInFlight++;              // incrementa a lotação no avião
    sh->fSt.totalPassBoarded++;           // incrementa o registo de já embarcados no total
    savePassengerChecked(nFic, &sh->fSt); // imprime a mensagem de que o passageiro deu checked-in
    saveState(nFic, &sh->fSt);            // guarda os valores dos contadores

    // Verifica se o avião está pronto para partir
    if (nPassengersInFlight() == MAXFC)     // se a lotação do avião chegou ao seu máximo
    {
        last = true;
    }
    else if (nPassengersInFlight() >= MINFC && nPassengersInQueue() == 0){      // já há numero minimo de lotação e ninguem na fila de espera
        last = true;
    }
    else if (sh->fSt.totalPassBoarded == N){                // já todos os passageiros embarcaram 
        last = true;
    }
    else
    {
        last = false;
    }

    /* exit critical region */
    if (semUp(semgid, sh->mutex) == -1)
    { 
        perror("error on the up operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }

    return last;
}

static int nPassengersInFlight()
{
    return sh->fSt.nPassInFlight;
}

static int nPassengersInQueue()
{
    return sh->fSt.nPassInQueue;
}

/**
 *  \brief signal ready to flight
 *
 *  The flight is ready to go.
 *  The hostess updates her state, registers the number of passengers in this flight
 *  and checks if the airlift is finished (all passengers have boarded).
 *  Hostess informs pilot that plane is ready to flight.
 *  The internal state should be saved.
 */
void signalReadyToFlight()
{   
    /* enter critical region */
    if (semDown(semgid, sh->mutex) == -1)
    { 
        perror("error on the down operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }

    sh->fSt.st.hostessStat = READY_TO_FLIGHT; // atualiza o estado da hospedeira para READY_TO_FLIGHT
    saveState(nFic, &sh->fSt); // atualiza os dados

    sh->fSt.nPassengersInFlight[sh->fSt.nFlight - 1] = sh->fSt.nPassInFlight;      // regista o número de passageiros que o avião nFlight leva.
    saveFlightDeparted(nFic, &sh->fSt);         // emite o anúncio que o voo descolou

    // avalia se este será o último voo necessário
    if (sh->fSt.totalPassBoarded == N)
    {
        sh->fSt.finished = true;
    }

    /* exit critical region */
    if (semUp(semgid, sh->mutex) == -1)
    { 
        perror("error on the up operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }
    
    // sinaliza ao piloto que já está pronto para voar
    if (semUp(semgid, sh->readyToFlight))
    {                       
        perror("error on the up operation for semaphore access (HT)");
        exit(EXIT_FAILURE);
    }
}
