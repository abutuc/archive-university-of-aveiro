public class WordInfo {

	private final String word;
	private final int length;
	private final int x;
	private final int y;
	private final Orientation orientation;

	public WordInfo(String word, int x, int y, Orientation orientation) {
		this.word = word;
		this.length = word.length();
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public String getWord() {
		return this.word;
	}

	public int getLength() {
		return this.length;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Orientation getOrientation() {
		return this.orientation;
	}
	
}