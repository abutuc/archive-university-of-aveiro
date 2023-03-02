package lab03.exe02;

import java.util.ArrayList;

public class Flight {
	
	private int numEconomySeats;
	private int numExecutiveSeats;
	private String flightCode;
	private Plane flightPlane;

	public Flight(String[] flightDetails, ArrayList<String> flightReservations) {
		processDetails(flightDetails);
	}
	
	/* Receive Flight details and process them to class attributes*/
	private void processDetails (String[] flightDetails) {
		this.flightCode = flightDetails[0];
		
		if(flightDetails.length == 2) {
			this.numExecutiveSeats = 0;
			String[] rowsAndSeatsEconomic = flightDetails[1].split("x");
			this.numEconomySeats = Integer.parseInt(rowsAndSeatsEconomic[0]) * Integer.parseInt(rowsAndSeatsEconomic[1]);
		}
		
		else {
			String[] rowsAndSeatsEconomic = flightDetails[2].split("x");
			String[] rowsAndSeatsExecutive = flightDetails[1].split("x");
			this.numEconomySeats = Integer.parseInt(rowsAndSeatsEconomic[0]) * Integer.parseInt(rowsAndSeatsEconomic[1]);
			this.numExecutiveSeats = Integer.parseInt(rowsAndSeatsExecutive[0]) * Integer.parseInt(rowsAndSeatsExecutive[1]);
		}
	}
	
	@Override
	public String toString() {
		if(numExecutiveSeats==0) {
			return "Código de Voo " + flightCode + ". Lugares Disponiveis: " + numEconomySeats + " lugares em classe Turistica." 
					+ "\nClasse Executiva não disponivel neste voo.";
		}
		else {
			return "Código de Voo " + flightCode + ". Lugares Disponiveis: " + numExecutiveSeats + " lugares " 
		+ "em classe Executiva; " + numEconomySeats + " lugares em classe Turistica.";
		}
	}
}
