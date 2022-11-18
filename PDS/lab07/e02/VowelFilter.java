
public class VowelFilter extends TextProcessingDecorator {

	public VowelFilter(TextProcessingInterface tp) {
		super(tp);
	}

	@Override
	public boolean hasNext() {
		return tp.hasNext();
	}

	@Override
	public String next() {
		return tp.next().replaceAll("[aAeEiIoOuUàÀèÈìÌòÒùÙáÁéÉíÍóÓúÚãÃõÕâÂêÊîÎôÔûÛäÄëËïÏöÖüÜ]", "");
	}
}
