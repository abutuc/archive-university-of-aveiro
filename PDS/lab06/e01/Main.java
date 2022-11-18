
public class Main {

	public static void main(String[] args) {

		// Sweets: Database
		System.out.println("< Sweets: Database >"); System.out.println();
		Database db = new Database();

		db.addEmployee(new Employee("Julie Dillon", 1, 1300.00));
		db.addEmployee(new Employee("Zak Rawlings", 2, 1450.00));
		db.addEmployee(new Employee("Mcauley Muir", 3, 1200.00));

		for (Employee e : db.getAllEmployees())
			System.out.println("name=" + e.getName() + "; emp_num=" + e.getEmpNum() + "; salary=" + e.getSalary());
		System.out.println();
		
		// Petiscos: Registos
		System.out.println("< Petiscos: Registos >"); System.out.println();
		Registos r = new Registos();

		r.insere(new Empregado("Osama", "Thomas", 10, 1000.00));
		r.insere(new Empregado("Ayah", "Silva", 14, 1750.00));
		r.insere(new Empregado("Rilley", "Rayner", 16, 1900.00));
		
		for (Empregado e : r.listaDeEmpregados())
			System.out.println("nome=" + e.nome() + "; apelido=" + e.apelido() + "; codigo=" + e.codigo() + "; salario=" + e.salario());
		System.out.println();
		
		// Adapters
		BDComum adb = new AdapterDatabase(db);
		BDComum ar = new AdapterRegistos(r);
		
		// BDFinal: Database + Registos
		System.out.println("< BDFinal: Sweets + Petiscos >"); System.out.println();
		BDFinal bdFinal = new BDFinal(adb, ar);
		
		bdFinal.imprimirEmpregados(); System.out.println();
		
		bdFinal.adicionar(new Empregado("John", "Cena", 4, 1420.00));
		bdFinal.adicionar(new Empregado("William", "Shakesapple", 6, 950.00));
		bdFinal.adicionar(new Empregado("Cassandra", "Costa", 70, 1300.00));
		
		bdFinal.apagar(1);  System.out.println("Removed Employee with emp_num=1");
		bdFinal.apagar(14); System.out.println("Removed Employee with emp_num=14");
		bdFinal.apagar(70); System.out.println("Removed Employee with emp_num=70");
		System.out.println();
		
		System.out.println("Empregado with codigo=2 exists? " + bdFinal.isEmpregado(2));
		System.out.println("Empregado with codigo=10 exists? " + bdFinal.isEmpregado(10));
		System.out.println("Empregado with codigo=70 exists? " + bdFinal.isEmpregado(70));
		System.out.println();
		
		bdFinal.imprimirEmpregados();
		
	}
}
