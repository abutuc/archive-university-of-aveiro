
public class AdapterRegistos implements BDComum {

	private Registos r;

	public AdapterRegistos(Registos r) {
		this.r = r;
	}

	@Override
	public void adicionar(Empregado e) {
		r.insere(e);
	}

	@Override
	public void apagar(int codigo) {
		r.remove(codigo);
	}

	@Override
	public boolean isEmpregado(int codigo) {
		return r.isEmpregado(codigo);
	}

	@Override
	public void imprimirEmpregados() {
		for (Empregado e : r.listaDeEmpregados())
			System.out.println("nome=" + e.nome() + " " + e.apelido() + "; code=" + e.codigo() + "; salary=" + e.salario());

	}

}
