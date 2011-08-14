package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Key;

/**
 *
 * @author patrik
 */
public class BlueLockTo extends BlockReaction {

    private BlueLockTo() {}
    private static BlueLockTo mInstance = null;
    public static synchronized BlueLockTo getInstance() {
        if(mInstance == null)
            mInstance = new BlueLockTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        useKey(Key.BLUE);
        standing.destroy();
    }

    public boolean canMove(Block moving, Block standing) {
        return isChip(moving) &&
               hasKey(Key.BLUE);
    }

}
