package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Key;
import chipschallenge.SoundPlayer.sounds;

/**
 * Moving to blue lock
 */
public class BlueLockTo extends BlockReaction {

    private BlueLockTo() {
    }
    private static BlueLockTo mInstance = null;

    public static synchronized BlueLockTo getInstance() {
        if (mInstance == null) {
            mInstance = new BlueLockTo();
        }
        return mInstance;
    }

    // Use key
    public void react(Block moving, Block standing) {
        useKey(Key.BLUE);
        standing.destroy();
        sound().playSound(sounds.DOOR);
    }

    // Only chip can move, when he has a blue key
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() && hasKey(Key.BLUE);
    }
}
