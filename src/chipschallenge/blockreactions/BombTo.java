package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.SoundPlayer.sounds;

/**
 * Move to bomb
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

    // If collision, both bomb and other block is removed
    public void react(Block moving, Block standing) {
        if (moving.isChip()) {
            die("Ooops! Don't touch the bombs!");
        } else {
            moving.destroy();
            standing.destroy();
            sound().playSound(sounds.BOMB);
        }
    }

    // All can move here
    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
