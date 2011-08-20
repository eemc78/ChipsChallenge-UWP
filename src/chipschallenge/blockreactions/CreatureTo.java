package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 * Move to creature
 */
public class CreatureTo extends NoSlipReaction {

    private CreatureTo() {
    }
    private static CreatureTo mInstance = null;

    public static synchronized CreatureTo getInstance() {
        if (mInstance == null) {
            mInstance = new CreatureTo();
        }
        return mInstance;
    }

    // Kills chip
    public void react(Block moving, Block standing) {
        if (moving != standing) {
            die("Ooops! Look out for creatures!");
        }
    }

    // Only chip can move
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
