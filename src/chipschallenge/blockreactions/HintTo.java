package chipschallenge.blockreactions;

import chipschallenge.Block;

public class HintTo extends NoSlipReaction {

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
