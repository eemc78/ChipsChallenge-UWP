package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public class HintTo extends BlockReaction {

    private HintTo() {
    }
    private static HintTo mInstance = null;

    public static synchronized HintTo getInstance() {
        if (mInstance == null) {
            mInstance = new HintTo();
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
