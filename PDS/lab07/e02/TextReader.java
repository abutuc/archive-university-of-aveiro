import java.io.*;
import java.util.*;

public class TextReader implements TextProcessingInterface {
	private String fpath;
	private Scanner sc;

	public TextReader(String fpath) {
		this.fpath = fpath;
		try {
			sc = new Scanner(new File(fpath));
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File \"" + fpath + "\" not found");
			System.exit(1);
		}
	}

	public String getFpath() {
		return fpath;
	}

	@Override
	public boolean hasNext() {
		return sc.hasNext();
	}

	@Override
	public String next() {
		return sc.nextLine();
	}
}