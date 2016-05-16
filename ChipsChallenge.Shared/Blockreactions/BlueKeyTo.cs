package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Inventory.Key;

/**
 * Moving to blue key
 */
public class BlueKeyTo extends NoSlipReaction {

    private BlueKeyTo() {
    }
    private static BlueKeyTo mInstance = null;

    public static synchronized BlueKeyTo getInstance() {
        if (mInstance == null) {
            mInstance = new BlueKeyTo();
        }
        return mInstance;
    }

    // Take key
    public void react(Block moving, Block standing) {
        if (moving.isChip()) {
            takeKey(Key.BLUE);
            standing.replace(createBlock(Type.FLOOR));
        }
    }

    // Everyone can move here
    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
