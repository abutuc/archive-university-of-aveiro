public class Date {
	private final int day;
	private final Month month;
	private final int year;

	public Date(int day, Month month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public Month getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return day + " of " + month + " " + year;
	}
}
