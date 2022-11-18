
public class Conserva extends Produto {
	private String name;
	private double weight;

	public Conserva(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}

	public double totalWeight() {
		return weight;
	}

	public void draw() {
		System.out.println("\t".repeat(nTabs) + this);
	}

	@Override
	public String toString() {
		return "Conserva '" + name + "' - Weight : " + weight;
	}
}
