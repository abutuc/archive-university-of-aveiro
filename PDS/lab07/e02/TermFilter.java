import java.util.Scanner;

public class TermFilter extends TextProcessingDecorator {
	private Scanner sc;

	public TermFilter(TextProcessingInterface tp) {
		super(tp);
		sc = new Scanner(tp.next());
	}

	@Override
	public boolean hasNext() {
		return sc.hasNext() || tp.hasNext();
	}

	@Override
	public String next() {
		if (!sc.hasNext() && tp.hasNext()) {
			sc = new Scanner(tp.next()); // read next paragraph
		}
		return sc.next();		
	}
}
