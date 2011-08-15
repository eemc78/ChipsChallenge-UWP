package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public class GravelTo extends BlockReaction {

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

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
