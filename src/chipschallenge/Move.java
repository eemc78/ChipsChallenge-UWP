package chipschallenge;

import java.awt.Point;

/**
 *
 * @author patrik
 */
public class Move {

    public enum Moves {UP, DOWN, LEFT, RIGHT};

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
        switch(random) {
            case 0:
                ret = Moves.UP;
                break;
            case 1:
                ret = Moves.DOWN;
                break;
            case 2:
                ret = Moves.LEFT;
                break;
            case 3:
                ret = Moves.RIGHT;
                break;
        }
        return ret;
    }

    public static Moves reverse(Moves m) {
        Moves ret = null;
        switch(m) {
            case UP:
                ret = Moves.DOWN;
                break;
            case DOWN:
                ret = Moves.UP;
                break;
            case LEFT:
                ret = Moves.RIGHT;
                break;
            case RIGHT:
                ret = Moves.LEFT;
                break;
        }
        return ret;
    }
    
}
