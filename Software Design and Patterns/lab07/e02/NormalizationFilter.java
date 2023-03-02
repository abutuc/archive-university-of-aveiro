import java.text.Normalizer;
import java.text.Normalizer.Form;

public class NormalizationFilter extends TextProcessingDecorator {

	public NormalizationFilter(TextProcessingInterface tp) {
		super(tp);
	}

	@Override
	public boolean hasNext() {
		return tp.hasNext();
	}

	@Override
	public String next() {
		return Normalizer.normalize(tp.next().replaceAll("\\p{Punct}", ""), Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
