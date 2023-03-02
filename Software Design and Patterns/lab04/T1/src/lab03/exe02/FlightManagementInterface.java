package lab03.exe02;

public interface FlightManagementInterface {
	
	public abstract void readFile(String[] userInput);
	public abstract void seeFlightReservations(String[] userInput);
	public abstract void addFlight(String[] userInput);
	public abstract void addFlightReservation(String[] userInput);
	public abstract void cancelReservation(String[] userInput);
}
