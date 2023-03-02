
public class FruitJuice implements Portion {

	private String fruitName;

	public FruitJuice() {
		this.fruitName = "Orange";
	}

	public FruitJuice(String fruitName) {
		this.fruitName = fruitName;
	}

	public State getState() {
		return State.Liquid;
	}

	public Temperature getTemperature() {
		return Temperature.COLD;
	}

	public String toString() {
		return "FruitJuice: " + fruitName + ", Temperature " + getTemperature() + ", State " + getState();
	}
}
