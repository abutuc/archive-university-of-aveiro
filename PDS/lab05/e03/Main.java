
public class Main {

	public static void main(String[] args) {
		Movie movie = new Movie.Builder("Shrek", 2001)
				.director(new Person("Andrew Adamson"))
				.writer(new Person("William Steig"))
				.series("Shrek")
				.cast(new Person("Mike Myers"))
				.cast(new Person("Eddie Murphy"), new Person("Cameron Diaz"))
				.locations(new Place("Duloc"), new Place("Princess Fiona's Tower"))
				.languages("English", "Portuguese")
				.genres("Animation", "Adventure", "Comedy")
				.build();

		System.out.println(movie);
	}

}
