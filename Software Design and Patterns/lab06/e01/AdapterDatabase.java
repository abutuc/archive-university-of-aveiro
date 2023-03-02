
public class AdapterDatabase implements BDComum {

	private Database db;

	public AdapterDatabase(Database db) {
		this.db = db;
	}

	@Override
	public void adicionar(Empregado e) {
		db.addEmployee(new Employee(e.nome() + " " + e.apelido(), (long) e.codigo(), e.salario()));
	}

	@Override
	public void apagar(int codigo) {
		db.deleteEmployee((long) codigo);
	}

	@Override
	public boolean isEmpregado(int codigo) {
		boolean flag = false;
		for (Employee e : db.getAllEmployees())
			if (e.getEmpNum() == codigo) {
				flag = true;
				break;
			}
		return flag;
	}

	@Override
	public void imprimirEmpregados() {
		for (Employee e : db.getAllEmployees())
			System.out.println("nome=" + e.getName() + "; code=" + e.getEmpNum() + "; salary=" + e.getSalary());
	}

}
