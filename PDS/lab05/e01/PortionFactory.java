
public class PortionFactory {

	static Portion create(String food, Temperature t) {

		if (food.equals("Beverage") && t == Temperature.WARM)
			return new Milk();
		if (food.equals("Beverage") && t == Temperature.COLD)
			return new FruitJuice();
		if (food.equals("Meat") && t == Temperature.COLD)
			return new Tuna();
		if (food.equals("Meat") && t == Temperature.WARM)
			return new Pork();
		else
			throw new IllegalArgumentException();

	}

	static Portion create(String food, Temperature t, String other) {
		if (food.equals("Beverage") && t == Temperature.COLD)
			return new FruitJuice(other);
		else
			throw new IllegalArgumentException();
	}
	
}
