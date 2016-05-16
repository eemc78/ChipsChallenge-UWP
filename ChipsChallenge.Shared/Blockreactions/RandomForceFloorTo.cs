package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;
import chipschallenge.Move;
import chipschallenge.Move.Moves;

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
        // Nothing
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }

    @Override
    public Moves causesSlip(Block moving, Block standing) {
        if ((moving.isChip() && hasBoots(Boots.SUCTIONBOOTS))) {
            return null;
        } else {
            return Move.getRandom();
        }
    }
}
