
public class CapitalizationFilter extends TextProcessingDecorator {

	public CapitalizationFilter(TextProcessingInterface tp) {
		super(tp);
	}

	@Override
	public boolean hasNext() {
		return tp.hasNext();
	}

	@Override
	public String next() {
		String str = tp.next();
		int len = str.length();

		if (len < 2) {
			return str.toUpperCase();
		} else {
			return str.substring(0, 1).toUpperCase() + str.substring(1, len - 1).toLowerCase()
					+ str.substring(len - 1, len).toUpperCase();
		}
	}
}
