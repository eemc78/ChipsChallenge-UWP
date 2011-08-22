package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Key;

public class YellowLockTo extends NoSlipReaction {

    private YellowLockTo() {
    }
    private static YellowLockTo mInstance = null;

    public static synchronized YellowLockTo getInstance() {
        if (mInstance == null) {
            mInstance = new YellowLockTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        useKey(Key.YELLOW);
        standing.destroy();
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() && hasKey(Key.YELLOW);
    }
}
