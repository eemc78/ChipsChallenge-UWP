package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Game;
import chipschallenge.Inventory.Key;

/**
 *
 * @author patrik
 */
public class GreenLockTo extends BlockReaction {

    private GreenLockTo() {}
    private static GreenLockTo mInstance = null;
    public static synchronized GreenLockTo getInstance() {
        if(mInstance == null)
            mInstance = new GreenLockTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        Game.getInstance().getInventory().useKey(Key.GREEN);
        standing.destroy();
    }

    public boolean canMove(Block moving, Block standing) {
        return isChip(moving) &&
               Game.getInstance().getInventory().hasKey(Key.GREEN);
    }

}
