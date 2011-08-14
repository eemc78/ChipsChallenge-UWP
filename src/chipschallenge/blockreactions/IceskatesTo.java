package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

/**
 *
 * @author patrik
 */
public class IceskatesTo extends BlockReaction {

    private IceskatesTo() {}
    private static IceskatesTo mInstance = null;
    public static synchronized IceskatesTo getInstance() {
        if(mInstance == null)
            mInstance = new IceskatesTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if(isChip(moving)) {
            takeBoots(Boots.ICESKATES);
            standing.destroy();
            //TODO: Play take-item sound
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return isChip(moving) || isBlock(moving);
    }

}
