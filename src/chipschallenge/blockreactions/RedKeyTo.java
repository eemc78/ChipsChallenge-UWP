package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Game;
import chipschallenge.Inventory.Key;

/**
 *
 * @author patrik
 */
public class RedKeyTo extends BlockReaction {

    private RedKeyTo() {
    }
    private static RedKeyTo mInstance = null;

    public static synchronized RedKeyTo getInstance() {
        if (mInstance == null) {
            mInstance = new RedKeyTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if (moving.isChip()) {
            takeKey(Key.RED);
            standing.replace(createBlock(Type.FLOOR));
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
