package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;

public class ToggleWallTo extends NoSlipReaction {

    private ToggleWallTo() {
    }
    private static ToggleWallTo mInstance = null;

    public static synchronized ToggleWallTo getInstance() {
        if (mInstance == null) {
            mInstance = new ToggleWallTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        // No reaction
    }

    public boolean canMove(Block moving, Block standing) {
        return standing.isA(Block.Type.TOGGLEWALLOPEN);
    }
}
