package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Key;

/**
 *
 * @author patrik
 */
public class YellowKeyTo extends BlockReaction {

    private YellowKeyTo() {
    }
    private static YellowKeyTo mInstance = null;

    public static synchronized YellowKeyTo getInstance() {
        if (mInstance == null) {
            mInstance = new YellowKeyTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if (moving.isChip()) {
            takeKey(Key.YELLOW);
            standing.replace(createBlock(Type.FLOOR));
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
