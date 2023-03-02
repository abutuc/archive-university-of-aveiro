import java.util.ArrayList;
import java.util.List;

public class SocialSecurity {
	private static List<Person> pers = new ArrayList<>();
	
	public static void regist(Person p) {
		pers.add(p);
	}
}
