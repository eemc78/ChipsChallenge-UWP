package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;

public class RecessedWallTo extends NoSlipReaction {

    private RecessedWallTo() {
    }
    private static RecessedWallTo mInstance = null;

    public static synchronized RecessedWallTo getInstance() {
        if (mInstance == null) {
            mInstance = new RecessedWallTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        standing.replace(createBlock(Type.WALL));
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
