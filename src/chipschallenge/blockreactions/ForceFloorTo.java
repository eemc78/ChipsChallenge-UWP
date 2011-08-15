package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

/**
 *
 * @author patrik
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

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (!(moving.isChip() && hasBoots(Boots.SUCTIONBOOTS))) {
            game().addForcedMove(moving, standing.getFacing());
        }
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
