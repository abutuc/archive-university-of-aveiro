import java.util.*;

public class Caixa extends Produto {
	private String name;
	private double weight;
	private List<Produto> produtos = new ArrayList<>();

	public Caixa(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public void add(Produto p) {
		produtos.add(p);
	}

	public double totalWeight() {
		double total = 0;
		for (Produto p : produtos)
			total += p.totalWeight();
		return total;
	}

	public void draw() {
		System.out.println("\t".repeat(nTabs++) + this);
		for (Produto p : produtos)
			p.draw();
		nTabs--;
	}

	@Override
	public String toString() {
		return "* Caixa '" + name + "' [ Weight: " + weight + " ; Total: " + totalWeight() + "]";
	}
}
