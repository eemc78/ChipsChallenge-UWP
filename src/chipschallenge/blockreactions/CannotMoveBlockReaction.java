package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 * Standard behavior for when things CANNOT move
 */
public class CannotMoveBlockReaction extends BlockReaction {

    private CannotMoveBlockReaction() {
    }
    private static CannotMoveBlockReaction mInstance = null;

    public static synchronized CannotMoveBlockReaction getInstance() {
        if (mInstance == null) {
            mInstance = new CannotMoveBlockReaction();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        // No reaction
    }

    public boolean canMove(Block moving, Block standing) {
        return false;
    }
}
