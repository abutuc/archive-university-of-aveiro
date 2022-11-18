public class TextProcessingDecorator implements TextProcessingInterface {
	protected TextProcessingInterface tp;

	public TextProcessingDecorator(TextProcessingInterface tp) {
		this.tp = tp;
	}

	@Override
	public boolean hasNext() {
		return tp.hasNext();
	}

	@Override
	public String next() {
		return tp.next();
	}
}
