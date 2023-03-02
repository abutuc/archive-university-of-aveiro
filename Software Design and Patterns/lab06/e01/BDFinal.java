
public class BDFinal {

	private BDComum db;
	private BDComum r;

	public BDFinal(BDComum db, BDComum r) {
		this.db = db;
		this.r = r;
	}

	public void adicionar(Empregado e) {

		if (!isEmpregado(e.codigo())) {
			r.adicionar(e); // Utilizar esta porque reaproveita o objeto Empregado
		} else {
			System.out.println("Employee no. " + e.codigo() + "already exists!");
		}
	}

	public void apagar(int codigo) {

		if (isEmpregado(codigo)) {
			db.apagar(codigo);
			r.apagar(codigo);
		}
		else {
			System.out.println("Employee no. " + codigo + "doesn't exist!");			
		}
	}

	public boolean isEmpregado(int codigo) {
		return db.isEmpregado(codigo) || r.isEmpregado(codigo);
	}

	public void imprimirEmpregados() {
		db.imprimirEmpregados();
		r.imprimirEmpregados();
	}

}
