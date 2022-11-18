package lab03.exe02;

import java.util.Map;
import java.util.HashMap;

public class Plane {
	
	/* Class, Number of seats available in that class*/
	private Map <String, Integer> numSeatsPerClass = new HashMap<String, Integer>();
	
	public Plane() {

	}

	public Map<String, Integer> getNumSeatsPerClass() {
		return numSeatsPerClass;
	}

	public void setNumSeatsPerClass(Map<String, Integer> numSeatsPerClass) {
		this.numSeatsPerClass = numSeatsPerClass;
	}
}
