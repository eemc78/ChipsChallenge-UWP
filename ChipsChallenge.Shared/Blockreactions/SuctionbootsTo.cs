package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

public class SuctionbootsTo extends NoSlipReaction {

    private SuctionbootsTo() {
    }
    private static SuctionbootsTo mInstance = null;

    public static synchronized SuctionbootsTo getInstance() {
        if (mInstance == null) {
            mInstance = new SuctionbootsTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (moving.isChip()) {
            takeBoots(Boots.SUCTIONBOOTS);
            standing.replace(createBlock(Type.FLOOR));
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() || moving.isBlock();
    }
}
