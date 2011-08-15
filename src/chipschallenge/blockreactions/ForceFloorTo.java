package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

/**
 * Move to force floor
 */
public class ForceFloorTo extends BlockReaction {

    private ForceFloorTo() {
    }
    private static ForceFloorTo mInstance = null;

    public static synchronized ForceFloorTo getInstance() {
        if (mInstance == null) {
            mInstance = new ForceFloorTo();
        }
        return mInstance;
    }

    // Force moves, except on chip with suction boots.
    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (!(moving.isChip() && hasBoots(Boots.SUCTIONBOOTS))) {
            game().addForcedMove(moving, standing.getFacing());
        }
    }

    // Everything can move here
    @Override
    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
