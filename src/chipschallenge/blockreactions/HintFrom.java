package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Boots;

/**
 *
 * @author patrik
 */
public class HintFrom extends BlockReaction {

    private HintFrom() {
    }
    private static HintFrom mInstance = null;

    public static synchronized HintFrom getInstance() {
        if (mInstance == null) {
            mInstance = new HintFrom();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        game().showHint();
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
