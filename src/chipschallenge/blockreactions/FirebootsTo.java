package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

/**
 *
 * @author patrik
 */
public class FirebootsTo extends BlockReaction {

    private FirebootsTo() {
    }
    private static FirebootsTo mInstance = null;

    public static synchronized FirebootsTo getInstance() {
        if (mInstance == null) {
            mInstance = new FirebootsTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (moving.isChip()) {
            takeBoots(Boots.FIREBOOTS);
            standing.destroy();
            //TODO: Play take-item sound
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() || moving.isBlock();
    }
}
