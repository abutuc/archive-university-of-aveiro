
public class Seat {

	private char id;
	private int reservationId;

	public Seat(char id) {
		this.id = id;
		this.reservationId = 0; // Sem reserva
	}

	public char getId() {
		return id;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

}
