package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Key;

public class RedLockTo extends NoSlipReaction {

    private RedLockTo() {
    }
    private static RedLockTo mInstance = null;

    public static synchronized RedLockTo getInstance() {
        if (mInstance == null) {
            mInstance = new RedLockTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        useKey(Key.RED);
        standing.destroy();
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() && hasKey(Key.RED);
    }
}
