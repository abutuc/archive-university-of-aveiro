import java.util.ArrayList;
import java.util.stream.Collectors;

public class Plane {

	private ArrayList<Row> rows;

	public Plane(int nRowsE, int nSeatsPerRowE, int nRowsT, int nSeatsPerRowT) {
		this.rows = new ArrayList<>();
		int rowId = 1;
		// Classe executiva
		for (; rowId <= nRowsE; rowId++)
			this.rows.add(new Row(rowId, ClassType.E, nSeatsPerRowE));
		// Classe turística
		for (; rowId <= nRowsE + nRowsT; rowId++)
			this.rows.add(new Row(rowId, ClassType.T, nSeatsPerRowT));
	}

	public int getnSeats(ClassType classType) {
		return rows.stream()
				.filter(row -> row.getClassType() == classType)
				.map(row -> row.getnSeats())
				.reduce(0,(x,y) -> x + y);
	}

	public ArrayList<Integer> getFreeRowIds(ClassType classType) {
		return rows.stream()
				.filter(row -> (row.getClassType() == classType) && row.isFree())
				.map(row -> row.getId())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public ArrayList<String> getRowSeatIds(ClassType classType, int reservationId) {
		ArrayList<String> ids = new ArrayList<>();
		for (Row row : rows)
			if (row.getClassType() == classType)
				for (char seatId : row.getSeatIds(reservationId))
					ids.add(row.getId() + "" + seatId);
		return ids;
	}

	public int getSeatReservationId(int rowId, char seatId) {
		return rows.get(rowId - 1).getSeatReservationId(seatId);
	}

	public void setSeatReservationId(int rowId, char seatId, int reservationId) {
		rows.get(rowId - 1).setSeatReservationId(seatId, reservationId);
	}

	public void printReservationMap() {
		// Dimensões do mapa
		int nRows = rows.size();
		int maxnSeats = rows.stream()
				.map(row -> row.getnSeats())
				.max(Integer::compare)
				.get();
		//
		// Matriz com reservationId de todos os lugares
		//
		// Linhas - Lugares
		// Colunas - Filas
		//
		Integer[][] reservationIdMatrix = new Integer[maxnSeats][nRows];
		// Para cada fila
		for (int iRow = 0; iRow < nRows; iRow++) {
			Row row = rows.get(iRow);
			// Para cada lugar
			for (int iSeat = 0; iSeat < row.getnSeats(); iSeat++) {
				char seatId = (char) ((int) 'A' + iSeat);
				reservationIdMatrix[iSeat][iRow] = row.getSeatReservationId(seatId);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		// Número da fila
		sb.append(" ");
		for (int iRow = 0; iRow < nRows; iRow++)
			sb.append(String.format(" %2d", iRow + 1));
		sb.append("\n");
		// Para cada lugar
		for (int iSeat = 0; iSeat < maxnSeats; iSeat++) {
			sb.append((char) ((int) 'A' + iSeat));
			// Para cada fila
			for (int iRow = 0; iRow < nRows; iRow++) {
				if (reservationIdMatrix[iSeat][iRow] != null)
					sb.append(String.format(" %2d", reservationIdMatrix[iSeat][iRow]));
				else
					sb.append("   ");
			}
			sb.append("\n");
		}
		
		// Imprimir mapa
		System.out.println(sb.toString());
	}
	
	@Override
	public String toString() {
		int nSeatsE = getnSeats(ClassType.E);
		int nSeatsT = getnSeats(ClassType.T);

		StringBuilder sb = new StringBuilder();
		sb.append("Lugares disponíveis: ");
		// Há classe executiva
		if (nSeatsE > 0)
			sb.append(nSeatsE + " lugares em classe " + ClassType.E.getLabel() + "; ");
		sb.append(nSeatsT + " lugares em classe " + ClassType.T.getLabel() + ".");
		return sb.toString();
	}

}
