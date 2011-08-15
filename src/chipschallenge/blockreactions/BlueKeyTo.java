package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Key;

/**
 *
 * @author patrik
 */
public class BlueKeyTo extends BlockReaction {

    private BlueKeyTo() {}
    private static BlueKeyTo mInstance = null;
    public static synchronized BlueKeyTo getInstance() {
        if(mInstance == null)
            mInstance = new BlueKeyTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if(moving.isChip()) {
            takeKey(Key.BLUE);
            standing.destroy();
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

}
