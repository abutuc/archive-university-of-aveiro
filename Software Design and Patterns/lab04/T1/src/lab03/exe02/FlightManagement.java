package lab03.exe02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FlightManagement implements FlightManagementInterface {
	
	private List<String> listOfCommands = new ArrayList<String>();
	private ArrayList<Flight> listOfFlights = new ArrayList<Flight>();
	
	public FlightManagement() {
		
	}

	@Override
	public void readFile(String[] userInput) {
		
		if(userInput.length < 2) {
			System.out.println("ERRO: Introduza comando no formato <I filename>");
		}
		
		else {
			try {
				File inFile = new File(userInput[1]);
				Scanner scanner = new Scanner(inFile);
				
				String firstLine = scanner.nextLine();
				String[] flightDetails = validateAndProcessFlightFile(firstLine);
				ArrayList<String> flightReservations = new ArrayList<String>();
				
				/* Check if file is valid and process it */
				if(flightDetails.length == 0) {
					System.out.println("ERRO: O ficheiro " + userInput[1] + " não tem o formato correto!");
					scanner.close();
					return;
				}
				
				while (scanner.hasNext()) {
					flightReservations.add(scanner.nextLine());
			   }
				scanner.close();
				
				Flight createdFlight = new Flight(flightDetails, flightReservations);
				listOfFlights.add(createdFlight);
				System.out.println(createdFlight.toString());
			} 
			catch (FileNotFoundException e) {
				System.out.println("ERRO: O ficheiro " + userInput[1] + " não foi encontrado!");
			} 
			catch (NoSuchElementException e) {
				System.out.println("ERRO: O ficheiro " + userInput[1] + " é um ficheiro vazio!");
			} 
		}
	}

	@Override
	public void seeFlightReservations(String[] userInput) {
		if(userInput.length != 2) {
			System.out.println("ERRO: Introduza comando no formato <M flight_code>");
			return;
		}
		
		else {
			System.out.println("Não implementado");
		}
	}

	@Override
	public void addFlight(String[] userInput) {
		if(userInput.length < 3 || userInput.length > 4) {
			System.out.println("ERRO: Introduza comando no formato <F flight_code [num_seats_executive] num_seats_tourist>");
			return;
		}
		
		else {
			System.out.println("Não implementado");
		}
	}

	@Override
	public void addFlightReservation(String[] userInput) {
		if(userInput.length != 4) {
			System.out.println("ERRO: Introduza comando no formato <R flight_code class number_seats>");
			return;
		}
		else {
			System.out.println("Não implementado");
		}
	}

	@Override
	public void cancelReservation(String[] userInput) {
		if(userInput.length != 2) {
			System.out.println("ERRO: Introduza comando no formato <C reservation_code>");
			return;
		}
		else {
			System.out.println("Não implementado");
		}
	}
	
	/* Check if file containing flight details is valid */
	private String[] validateAndProcessFlightFile(String firstLine) {
		
		/* Get flight code, number of rows and seats per row for economic class and for executive class */
		String[] flightDetails = firstLine.trim().split("\\s+");
		
		/* First line needs to start with '>' */
		if(firstLine.trim().isEmpty() || flightDetails[0].isBlank() || flightDetails[0].charAt(0) != '>')  {
			return new String[0];
		}
		
		/* First line must at least contain the flight code and number of rows and number of seats by row 
		 * in economic class (executive class is optional) */
		if(flightDetails.length < 2 || flightDetails.length > 3) {
			return new String[0];
		}
		
		/* For flight with only economic class, check if number of rows and 
		 * number of seats per row is formated correctly [intxint]*/
		if(flightDetails.length == 2) {
			if(!flightDetails[1].contains("x")) {
				return new String[0];
			}
			String[] rowsAndSeatsEconomic = flightDetails[1].split("x");
			
			if(rowsAndSeatsEconomic.length < 2 || !isInteger(rowsAndSeatsEconomic[0]) || !isInteger(rowsAndSeatsEconomic[1])) {
				return new String[0];
			}
		}
		
		/* For flight with economic and executive class, check if number of rows and 
		 * number of seats per row is formated correctly [intxint] for both */
		if(flightDetails.length == 3) {
			if(!flightDetails[1].contains("x") || !flightDetails[2].contains("x")) {
				return new String[0];
			}
			String[] rowsAndSeatsEconomic = flightDetails[2].split("x");
			String[] rowsAndSeatsExecutive = flightDetails[1].split("x");
			
			if(rowsAndSeatsEconomic.length < 2 || rowsAndSeatsExecutive.length < 2 || !isInteger(rowsAndSeatsEconomic[0]) || !isInteger(rowsAndSeatsEconomic[1]) 
					|| !isInteger(rowsAndSeatsExecutive[0]) || !isInteger(rowsAndSeatsExecutive[1])) {
				return new String[0];
			}
		}
		
		flightDetails[0] = flightDetails[0].replace('>', ' ').trim();
		return flightDetails;
	}
	
	/* Checks if string in a valid integer */
	private static boolean isInteger(String str) {
		try {
			int parsedValue = Integer.parseInt(str);
		    return true;
		} 
		catch (NumberFormatException e) {
			return false;
		}
	}
}
