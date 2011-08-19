package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

/**
 * Move to fire boots
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

    // Chip can pick fireboots
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (moving.isChip()) {
            takeBoots(Boots.FIREBOOTS);
            standing.replace(createBlock(Type.FLOOR));
        }
    }

    // Only chip and blocks
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() || moving.isBlock();
    }
}
