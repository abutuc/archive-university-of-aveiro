import java.util.ArrayList;
import java.util.stream.Collectors;

public class Row {

	private int id;
	private ClassType classType;
	private ArrayList<Seat> seats;

	public Row(int id, ClassType classType, int nSeats) {
		this.id = id;
		this.classType = classType;
		this.seats = new ArrayList<>();
		for (int i = 0; i < nSeats; i++)
			this.seats.add(new Seat((char) ((int) 'A' + i)));
	}

	public int getId() {
		return id;
	}

	public ClassType getClassType() {
		return classType;
	}

	public int getnSeats() {
		return seats.size();
	}
	
	public ArrayList<Character> getSeatIds(int reservationId) {
		return seats.stream()
				.filter(seat -> seat.getReservationId() == reservationId)
				.map(seat -> seat.getId())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public int getSeatReservationId(char seatId) {
		int i = (int) seatId - (int) 'A';
		return seats.get(i).getReservationId();
	}

	public void setSeatReservationId(char seatId, int reservationId) {
		int i = (int) seatId - (int) 'A';
		seats.get(i).setReservationId(reservationId);
	}

	public boolean isFree() {
		return seats.stream()
				.filter(seat -> seat.getReservationId() == 0)
				.count() == seats.size();
	}
}
