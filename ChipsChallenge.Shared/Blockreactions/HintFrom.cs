package chipschallenge.blockreactions;

import chipschallenge.Block;

public class HintFrom extends NoSlipReaction {

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
        game().hideHint();
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
