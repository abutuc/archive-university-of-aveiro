
public class TestSets {

	public static void main(String[] args) {

		// Sweets: Database
		System.out.println("< Sweets: Database >");
		Database db = new Database();

		db.addEmployee(new Employee("Julie Dillon", 1, 1300.00));
		db.addEmployee(new Employee("Zak Rawlings", 2, 1450.00));
		db.addEmployee(new Employee("Mcauley Muir", 3, 1200.00));

		for (Employee e : db.getAllEmployees())
			System.out.println("name=" + e.getName() + "; emp_num=" + e.getEmpNum() + "; salary=" + e.getSalary());

		db.deleteEmployee(2); // removes employee
		System.out.println("Removed Employee with emp_num=2");

		for (Employee e : db.getAllEmployees())
			System.out.println("name=" + e.getName() + "; emp_num=" + e.getEmpNum() + "; salary=" + e.getSalary());

		// Petiscos: Registos
		System.out.println("< Petiscos: Registos >");
		Registos rg = new Registos();

		rg.insere(new Empregado("Julie", "Dillon", 1, 1300.00));
		rg.insere(new Empregado("Zak", "Rawlings", 2, 1450.00));
		rg.insere(new Empregado("Mcauley", "Muir", 3, 1200.00));

		for (Empregado e : rg.listaDeEmpregados())
			System.out.println("nome=" + e.nome() + "; apelido=" + e.apelido() + "; codigo=" + e.codigo() + "; salario=" + e.salario());

		rg.remove(2); // removes employee
		System.out.println("Removed Empregado with codigo=2");

		System.out.println("Empregado with codigo=1 exists? " + rg.isEmpregado(1));
		System.out.println("Empregado with codigo=2 exists? " + rg.isEmpregado(2));

		for (Empregado e : rg.listaDeEmpregados())
			System.out.println("nome=" + e.nome() + "; apelido=" + e.apelido() + "; codigo=" + e.codigo() + "; salario=" + e.salario());

	}
}
