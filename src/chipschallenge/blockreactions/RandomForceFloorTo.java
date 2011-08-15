package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;
import chipschallenge.Move;

/**
 *
 * @author patrik
 */
public class RandomForceFloorTo extends BlockReaction {

    private RandomForceFloorTo() {
    }
    private static RandomForceFloorTo mInstance = null;

    public static synchronized RandomForceFloorTo getInstance() {
        if (mInstance == null) {
            mInstance = new RandomForceFloorTo();
        }
        return mInstance;
    }

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (!(moving.isChip() && hasBoots(Boots.SUCTIONBOOTS))) {
            game().addForcedMove(moving, Move.getRandom());
        }
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
