import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
	public static User user;
	private List<Employee> emps = new ArrayList<>();

	public void admitEmployee(Person p, double salary) {
		Employee e = new Employee(p, salary);
		emps.add(e);
		
		SocialSecurity.regist(p);
		Insurance.regist(p);
		Card.create(p);
		if (salary > avgSalary())
			Parking.allow(p);
	}

	public void paySalaries(int month) {
		for (Employee e : emps) {
			e.pay(e.getSalary());
		}
	}

	public List<Employee> employees() {
		return Collections.unmodifiableList(emps);
	}
	
	public double avgSalary() {
		double sum = 0;
		for (Employee e : employees())
			sum += e.getSalary();
		int cnt = employees().size();
		return sum / (double) cnt;
	}
}
