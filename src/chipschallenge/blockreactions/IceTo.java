package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

public class IceTo extends BlockReaction {

    private IceTo() {
    }
    private static IceTo mInstance = null;

    public static synchronized IceTo getInstance() {
        if (mInstance == null) {
            mInstance = new IceTo();
        }
        return mInstance;
    }

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        // Nothing
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return true;
    }

    @Override
    public boolean causesSlip(Block moving, Block standing) {
        return !(moving.isChip() && hasBoots(Boots.ICESKATES));
    }
}
