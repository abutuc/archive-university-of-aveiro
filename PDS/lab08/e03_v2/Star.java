import java.awt.Graphics;

import startypes.StarType;

public class Star {
	private StarType type;
	private int x;
	private int y;
	
	public Star(StarType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
        type.draw(g, x, y);
    }
}
