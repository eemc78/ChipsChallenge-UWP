package chipschallenge;

import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patrik
 */
public class Move {
    private static Random mRandom = null;
    public enum Moves {UP, DOWN, LEFT, RIGHT};
    public static Moves getRandom() {
        if(mRandom == null) {
           mRandom = new Random();
        }
        int random = mRandom.nextInt(4);
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
