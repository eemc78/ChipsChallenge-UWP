package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;

/**
 * Move to exit
 */
public class ExitTo extends BlockReaction {

    private ExitTo() {
    }
    private static ExitTo mInstance = null;

    public static synchronized ExitTo getInstance() {
        if (mInstance == null) {
            mInstance = new ExitTo();
        }
        return mInstance;
    }

    // Completes level
    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (moving.isChip()) {
            game().levelComplete();
        }
    }

    // Only chip and blocks can move
    @Override
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() || moving.isBlock();
    }
}
