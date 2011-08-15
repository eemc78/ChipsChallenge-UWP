package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Game;

/**
 *
 * @author patrik
 */
public class ChipTo extends BlockReaction {

    private ChipTo() {
    }
    private static ChipTo mInstance = null;

    public static synchronized ChipTo getInstance() {
        if (mInstance == null) {
            mInstance = new ChipTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if (!moving.isChip()) {
            die("Ooops! Look out for creatures!");
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
