public class Main {

	public static void main(String[] args) {
		TextProcessingInterface reader;

		System.out.println("\nReader: paragraphs\n");

		reader = new TextReader("sample_text.txt");
		while (reader.hasNext()) {
			System.out.println(reader.next());
		}

		System.out.println("\nReader: paragraphs and normalize\n");

		reader = new NormalizationFilter(new TextReader("sample_text.txt"));
		while (reader.hasNext()) {
			System.out.println(reader.next());
		}

		System.out.println("\nReader: terms and vowels\n");

		reader = new VowelFilter(new TermFilter(new TextReader("sample_text.txt")));
		while (reader.hasNext()) {
			System.out.print(reader.next() + "; ");
		}
		System.out.println();

		System.out.println("\nReader: terms, normalize and capitalize\n");

		reader = new CapitalizationFilter(new NormalizationFilter(new TermFilter(new TextReader("sample_text.txt"))));
		while (reader.hasNext()) {
			System.out.print(reader.next() + "; ");
		}
		System.out.println();
	}
}
