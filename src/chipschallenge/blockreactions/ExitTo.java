package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;

/**
 *
 * @author patrik
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

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (moving.isChip()) {
            game().levelComplete();
        }
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() || moving.isBlock();
    }
}
