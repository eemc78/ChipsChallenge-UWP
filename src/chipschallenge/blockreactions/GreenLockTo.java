package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Key;

public class GreenLockTo extends NoSlipReaction {

    private GreenLockTo() {
    }
    private static GreenLockTo mInstance = null;

    public static synchronized GreenLockTo getInstance() {
        if (mInstance == null) {
            mInstance = new GreenLockTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        useKey(Key.GREEN);
        standing.destroy();
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() && hasKey(Key.GREEN);
    }
}
