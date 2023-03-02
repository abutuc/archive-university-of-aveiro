public class Employee implements EmployeeInterface {
	private String name;

	public Employee(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void start(Date date) {
		System.out.print("\n" + name + " started working on " + date);
	}

	@Override
	public void terminate(Date date) {
		System.out.print("\n" + name + " stopped working on " + date);
	}

	@Override
	public void work() {
		System.out.print("\n" + name + " is working");
	}

}
