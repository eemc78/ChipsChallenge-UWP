package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 * Move to chip
 */
public class ChipTo extends BlockReaction {

    private ChipTo() {
    }
    private static ChipTo mInstance = null;

    public static synchronized ChipTo getInstance() {
        if (mInstance == null) {
            mInstance = new ChipTo();
        }
        return mInstance;
    }

    // All moving things kill chip
    public void react(Block moving, Block standing) {
        if (!moving.isChip()) {
            die("Ooops! Look out for creatures!");
        }
    }

    // All things can move
    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
