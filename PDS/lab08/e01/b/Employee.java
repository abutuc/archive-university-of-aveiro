
public class Employee {
	private Person person;
	private double salary;

	public Employee(Person p, double s) {
		person = p;
		salary = s;
	}

	public String getName() {
		return person.getName();
	}

	public double getSalary() {
		return salary;
	}
	
	public void pay(double amount) {
		person.pay(amount);
	}
}
