package lab03.exe02;

import java.util.Scanner;

public class MainProgram {

	public MainProgram(FlightManagementInterface mainProgram) {
		
		while(true) {
			System.out.println("\nEscolha uma opção: (H para ajuda) \n");
			
			Scanner sc = new Scanner(System.in);
		    String[] userInput = sc.nextLine().split(" ");
		    
		    switch(userInput[0]) {
		    
			    case "H":
			    	System.out.print("-----------------------------------------------------------MENU DE OPÇÕES"
			    			+ "-------------------------------------------------------------");
			    	System.out.println("\n<I filename> \n\t--> Ler ficheiro com informação sobre um voo e mostrar resultados \n");
			    	System.out.println("<M flight_code> \n\t--> Exibir mapa das reservas de um voo \n");
			    	System.out.println("<F flight_code [num_seats_executive] num_seats_tourist> \n\t--> Acrescentar um novo voo, "
			    			+ "com codigo, lugares em executiva (opcionais) e lugares em turistica \n");
			    	System.out.println("<R flight_code class number_seats> \n\t --> Acrescentar uma nova reserva a um voo, "
			    			+ "com indicação do código do voo, da classe (T/E), e do número de lugares \n");
			    	System.out.println("<C reservation_code> \n\t --> Cancelar uma reserva. O código de reserva "
			    			+ "tem o formato flight_code:sequential_reservation_number \n");
			    	System.out.println("<Q> --> Terminar o programa \n");
			    	System.out.print("----------------------------------------------------------------"
			    			+ "-----------------------------------------------------------------------");
			    	break;
			    
			    case "I":
			    	mainProgram.readFile(userInput);
			    	break;
			    	
			    case "M":
			    	mainProgram.seeFlightReservations(userInput);
			    	break;
	
			    case "F":
			    	mainProgram.addFlight(userInput);
			    	break;
			    
			    case "R":
			    	mainProgram.addFlightReservation(userInput);
			    	break;
			    	
			    case "C":
			    	mainProgram.cancelReservation(userInput);
			    	break;
			    	
			    case "Q":
			    	System.out.println("\nA sair do programa...Obrigado!");
			    	sc.close();
			    	System.exit(0);
			    	break;
			      
			    default:
			      System.out.println("\nPor favor escolha uma opção válida!\n");
		    }
		}
	}
	
	public static void main(String args[]) {
		new MainProgram(new FlightManagement());
	}
}
