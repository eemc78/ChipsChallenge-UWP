package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

public class IceskatesTo extends BlockReaction {

    private IceskatesTo() {
    }
    private static IceskatesTo mInstance = null;

    public static synchronized IceskatesTo getInstance() {
        if (mInstance == null) {
            mInstance = new IceskatesTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (moving.isChip()) {
            takeBoots(Boots.ICESKATES);
            standing.replace(createBlock(Type.FLOOR));
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() || moving.isBlock();
    }
}
