
public class Container {
	
	Portion p;
	
	static Container create(Portion p) {
		if (p.getState() == State.Liquid && p.getTemperature() == Temperature.COLD)
			return new PlasticBottle(p);
		if (p.getState() == State.Liquid && (p.getTemperature() == Temperature.COLD || p.getTemperature() == Temperature.WARM))
			return new TermicBottle(p);
		if (p.getState() == State.Solid && p.getTemperature() == Temperature.COLD)
			return new PlasticBag(p);
		if (p.getState() == State.Solid && (p.getTemperature() == Temperature.COLD || p.getTemperature() == Temperature.WARM))
			return new Tupperware(p);
		else
			throw new IllegalArgumentException();
	}
	
	protected Container (Portion p) {
		this.p = p;
	}

	public String toString() {
		return this.getClass().getSimpleName() + " with portion = " + p;
	}
	
}
