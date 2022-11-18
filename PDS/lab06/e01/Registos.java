

import java.util.ArrayList;
import java.util.List;

class Registos {
	// Data elements
	private ArrayList<Empregado> empregados; // Stores the employees

	public Registos() {
		empregados = new ArrayList<>();
	}

	public void insere(Empregado emp) {
		// Code to insert employee
		empregados.add(emp);
	}

	public void remove(int codigo) {
		// Code to remove employee
		empregados.removeIf(e -> e.codigo() == codigo);
	}

	public boolean isEmpregado(int codigo) {
		// Code to find employee
		boolean flag = false;
		for (Empregado e : empregados)
			if (e.codigo() == codigo) {
				flag = true;
				break;
			}
		return flag;
	}

	public List<Empregado> listaDeEmpregados() {
		// Code to retrieve collection
		return (List<Empregado>) empregados;
	}
}
