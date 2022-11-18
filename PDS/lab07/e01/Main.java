public class Main {
	public static void main(String[] args) {

		EmployeeInterface e1 = new Employee("Ana");
		TeamMember tm = new TeamMember(e1);

		EmployeeInterface e2 = new Employee("Bruno");
		TeamLeader tl = new TeamLeader(new TeamMember(e2));

		EmployeeInterface e3 = new Employee("Carlos");
		Manager m = new Manager(e3);

		// TeamMember
		tm.work();
		tm.colaborate();
		tm.start(new Date(3, Month.MAY, 2018));
		tm.start(new Date(10, Month.JUNE, 2020));

		// TeamLeader
		tl.work();
		tl.planning();

		// Manager
		m.work();
		m.managing();

	}
}