
public class Contact {
	private final String name;
	private final int phone;

	public Contact(String name, int phone) {
		this.name = name;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public int getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", phone=" + phone + "]";
	}
}
