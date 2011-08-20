package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 * Standard behavior for when things can move
 */
public class canMoveNoSlip extends BlockReaction {

    private canMoveNoSlip() {
    }
    private static canMoveNoSlip mInstance = null;

    public static synchronized canMoveNoSlip getInstance() {
        if (mInstance == null) {
            mInstance = new canMoveNoSlip();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        // No reaction
    }

    public final boolean canMove(Block moving, Block standing) {
        return true;
    }

    @Override
    public boolean causesSlip(Block moving, Block standing) {
        return false;
    }
}
