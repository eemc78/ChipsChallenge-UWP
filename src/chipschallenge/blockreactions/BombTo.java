package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public class BombTo extends BlockReaction {

    private BombTo() {
    }
    private static BombTo mInstance = null;

    public static synchronized BombTo getInstance() {
        if (mInstance == null) {
            mInstance = new BombTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if (moving.isChip()) {
            die("Ooops! Don't touch the bombs!");
        } else {
            moving.destroy();
            standing.destroy();
            //TODO: Play explosion sound
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
