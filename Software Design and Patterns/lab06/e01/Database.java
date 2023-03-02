
import java.util.Vector;

class Database {
	// Data elements
	private Vector<Employee> employees; // Stores the employees

	public Database() {
		employees = new Vector<>();
	}

	public boolean addEmployee(Employee employee) {
		// Code to add employee
		return employees.add(employee);
	}

	public void deleteEmployee(long emp_num) {
		// Code to delete employee
		employees.removeIf(e -> e.getEmpNum() == emp_num);
	}

	public Employee[] getAllEmployees() {
		// Code to retrieve collection
		return employees.toArray(new Employee[employees.size()]);
	}
}
