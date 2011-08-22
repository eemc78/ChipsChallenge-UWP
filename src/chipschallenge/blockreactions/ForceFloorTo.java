package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;
import chipschallenge.Move.Moves;

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
            //moving.setFacing(standing.getFacing());
        }
    }

    // Everything can move here
    @Override
    public boolean canMove(Block moving, Block standing) {
        return true;
    }

    @Override
    public Moves causesSlip(Block moving, Block standing) {
        if (moving.isChip() && hasBoots(Boots.SUCTIONBOOTS)) {
            return null;
        } else {
            return standing.getFacing();
        }
    }
}
