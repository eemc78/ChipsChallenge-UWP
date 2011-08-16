package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

/**
 * Move to flippers
 */
public class FlippersTo extends BlockReaction {

    private FlippersTo() {
    }
    private static FlippersTo mInstance = null;

    public static synchronized FlippersTo getInstance() {
        if (mInstance == null) {
            mInstance = new FlippersTo();
        }
        return mInstance;
    }

    // Chip can pick up flippers
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (moving.isChip()) {
            takeBoots(Boots.FLIPPERS);
            standing.replace(createBlock(Type.FLOOR));
            //TODO: Play take-item sound
        }
    }

    // Only chip and blocks
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() || moving.isBlock();
    }
}
