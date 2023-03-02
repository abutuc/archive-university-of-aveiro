package startypes;
import java.awt.*;

public class StarType {
    private int size;
    private Color color;
    protected String description;
    protected Float[] physicalProperties;
    private int x;
    private int y;


    public StarType(int size, Color color){
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y , size, size);
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public char getSimpleName(){
        return '0';
    }
}
