package chipschallenge;

import java.awt.Point;

public class Move {

    public enum Moves {

        UP, DOWN, LEFT, RIGHT
    };

    public static void updatePoint(Point p, Moves m) {
        switch (m) {
            case UP:
                p.y--;
                break;
            case DOWN:
                p.y++;
                break;
            case LEFT:
                p.x--;
                break;
            case RIGHT:
                p.x++;
                break;
        }
    }

    public static Moves getRandom() {
        int random = Utils.r.nextInt(4);
        Moves ret = null;
        switch (random) {
            case 0:
                return Moves.UP;
            case 1:
                return Moves.DOWN;
            case 2:
                return Moves.LEFT;
            case 3:
                return Moves.RIGHT;
        }
        return ret;
    }

    public static Moves reverse(Moves m) {
        switch (m) {
            case UP:
                return Moves.DOWN;
            case DOWN:
                return Moves.UP;
            case LEFT:
                return Moves.RIGHT;
            case RIGHT:
                return Moves.LEFT;
        }
        return null;
    }
}
