import java.util.*;

public class Flight {

	private int seqReservationNumber;
	private String code;
	private Plane plane;

	public Flight(String code, int nFilasE, int nLugaresFilaE, int nFilasT, int nLugaresFilaT) {
		this.seqReservationNumber = 1;
		this.code = code;
		this.plane = new Plane(nFilasE, nLugaresFilaE, nFilasT, nLugaresFilaT);
	}

	public String getCode() {
		return code;
	}

	public Plane getPlane() {
		return plane;
	}

	public int getLastReservationNumber() {
		return seqReservationNumber - 1;
	}

	public ArrayList<String> createReservation(ClassType classType, int nSeats) {

		ArrayList<String> reservedRowSeatIds = new ArrayList<>();

		ArrayList<Integer> freeRowIds = plane.getFreeRowIds(classType); // Ids das filas livres
		ArrayList<String> freeRowSeatIds = plane.getRowSeatIds(classType, 0); // Ids filaLugar dos lugares livres

		// Não há lugares disponíveis
		if (freeRowSeatIds.size() < nSeats) {
			System.err.println("Não foi possível obter lugares para a reserva: " + classType + " " + nSeats);
			return null;
		}

		// Índice em freeRowSeatIds
		int index = (freeRowIds.size() > 0) // Há pelo menos uma fila vazia
				? freeRowSeatIds.indexOf(freeRowIds.get(0) + "A") // Índice do primeiro lugar livre da primeira fila livre
				: 0; // Índice do primeiro lugar livre disponível

		// Enquanto houver passageiros sem lugar
		while (nSeats-- > 0) {
			String rowSeatId = freeRowSeatIds.get(index);

			int rowId = Integer.parseInt(rowSeatId.substring(0, rowSeatId.length() - 1));
			char seatId = rowSeatId.charAt(rowSeatId.length() - 1);
			plane.setSeatReservationId(rowId, seatId, seqReservationNumber);

			reservedRowSeatIds.add(rowSeatId);
			index = (index + 1) % freeRowSeatIds.size(); // Lista circular
		}
		seqReservationNumber++; // Incrementar número da reserva

		return reservedRowSeatIds;
	}

	public void cancelReservation(int reservationId) {

		ArrayList<String> toCancelRowSetIds = new ArrayList<>();
		
		toCancelRowSetIds.addAll(plane.getRowSeatIds(ClassType.E, reservationId));
		toCancelRowSetIds.addAll(plane.getRowSeatIds(ClassType.T, reservationId));
		
		for (String rowSeatId : toCancelRowSetIds) {
			int rowId = Integer.parseInt(rowSeatId.substring(0, rowSeatId.length() - 1));
			char seatId = rowSeatId.charAt(rowSeatId.length() - 1);
			plane.setSeatReservationId(rowId, seatId, 0);
		}
	}

	public void printReservationMap() {
		plane.printReservationMap();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Código de voo " + code + ". ");
		sb.append(plane);
		// Não há classe executiva
		if (plane.getnSeats(ClassType.E) == 0)
			sb.append("\nClasse executiva não disponível neste voo.");
		return sb.toString();
	}

}
