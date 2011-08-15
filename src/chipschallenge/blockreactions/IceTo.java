package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

/**
 *
 * @author patrik
 */
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
        if (!(moving.isChip() && hasBoots(Boots.ICESKATES))) {
            game().addForcedMove(moving, moving.getFacing());
        }
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
