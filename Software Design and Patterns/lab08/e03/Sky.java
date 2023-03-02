import javax.swing.*;

import startypes.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Sky extends JFrame {
    private Map<StarType, Map<Integer, List<Integer>>>  stars = new HashMap<>();

    public void placeStar(char starType, int x, int y) {
        StarType star = null; 
        switch (starType) {
            case 'O' : star = new OStar(); break;
            case 'A' : star = new AStar(); break;
            case 'B' : star = new BStar(); break;
            case 'F' : star = new FStar(); break;
            case 'G' : star = new GStar(); break;
            case 'K' : star = new KStar(); break;
            case 'M' : star = new MStar(); break;
            default: System.out.println("nope");
        }

        Set<Character> setTypeStars = new HashSet<>();
        for (StarType t : stars.keySet()){
            setTypeStars.add(t.getSimpleName());
        }

        if (!setTypeStars.contains(star.getSimpleName())){
            stars.put(star, new HashMap<>());
        }
        else {
            for (StarType t : stars.keySet()){
                if (t.getSimpleName() == star.getSimpleName())
                    star = t;
            }
        }

        if (!stars.get(star).containsKey(x))
            stars.get(star).put(x, new ArrayList<>());

        stars.get(star).get(x).add(y);
    }

    @Override
    public void paint(Graphics graphics) {
        for (StarType star : stars.keySet()) {
            for (Integer x : stars.get(star).keySet()){
                for (Integer y: stars.get(star).get(x)){
                    star.setPos(x, y);
                    star.draw(graphics);
                }
            }
        }
    }
}