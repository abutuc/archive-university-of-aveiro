import java.util.ArrayList;
import java.util.List;

public class Card {
	private static int seqId = 0;
	private static List<Card> cards = new ArrayList<>();
	
	private Person person;
	private int id;
	
	private Card(Person p) {
		person = p;
		id = seqId++;
	}
	
	public static void create(Person p) {
		cards.add(new Card(p));
	}
}
