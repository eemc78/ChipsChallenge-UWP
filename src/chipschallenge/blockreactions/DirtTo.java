package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;

/**
 * Move to dirt
 */
public class DirtTo extends NoSlipReaction {

    private DirtTo() {
    }
    private static DirtTo mInstance = null;

    public static synchronized DirtTo getInstance() {
        if (mInstance == null) {
            mInstance = new DirtTo();
        }
        return mInstance;
    }

    // Convert to floor
    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        standing.replace(createBlock(Type.FLOOR));
    }

    // Only chip can move
    @Override
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
