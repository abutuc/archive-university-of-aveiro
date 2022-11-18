package lab01.exe2;

import java.util.Random;

public enum Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT,
    DOWNRIGHT,
    DOWNLEFT,
    UPLEFT,
    UPRIGHT;

    public static Direction getRandomDirection() {
    	return Direction.values()[new Random().nextInt(Direction.values().length)];
    }
}
