package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 * Standard behavior for when things CANNOT move
 */
public class CanMove extends NoSlipReaction {

    private CanMove() {
    }
    private static CanMove mInstance = null;

    public static synchronized CanMove getInstance() {
        if (mInstance == null) {
            mInstance = new CanMove();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        // No reaction
    }

    public final boolean canMove(Block moving, Block standing) {
        return true;
    }
}
