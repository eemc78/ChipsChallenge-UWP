package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 * Standard behavior for when things CANNOT move
 */
public class CannotMove extends NoSlipReaction {

    private CannotMove() {
    }
    private static CannotMove mInstance = null;

    public static synchronized CannotMove getInstance() {
        if (mInstance == null) {
            mInstance = new CannotMove();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        // No reaction
    }

    public final boolean canMove(Block moving, Block standing) {
        return false;
    }

}
