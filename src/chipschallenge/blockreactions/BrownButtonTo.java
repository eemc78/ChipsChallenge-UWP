package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Buttons;

/**
 * Moving to brown button
 */
public class BrownButtonTo extends BlockReaction {

    private BrownButtonTo() {
    }
    private static BrownButtonTo mInstance = null;

    public static synchronized BrownButtonTo getInstance() {
        if (mInstance == null) {
            mInstance = new BrownButtonTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        Buttons.brownButtonDown(standing);
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
