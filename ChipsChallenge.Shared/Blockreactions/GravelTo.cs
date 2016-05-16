package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 * Move to gravel
 */
public class GravelTo extends NoSlipReaction {

    private GravelTo() {
    }
    private static GravelTo mInstance = null;

    public static synchronized GravelTo getInstance() {
        if (mInstance == null) {
            mInstance = new GravelTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        // No reaction
    }

    // Only chip can move
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
