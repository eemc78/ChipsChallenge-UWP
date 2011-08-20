package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

public class IceFrom extends NoSlipReaction {

    private IceFrom() {
    }
    private static IceFrom mInstance = null;

    public static synchronized IceFrom getInstance() {
        if (mInstance == null) {
            mInstance = new IceFrom();
        }
        return mInstance;
    }

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        // No reaction
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return !moving.isForced() || moving.isChip() && hasBoots(Boots.ICESKATES);
    }
}
