package chipschallenge.blockreactions;

import chipschallenge.Block;

public class TrapTo extends NoSlipReaction {

    private TrapTo() {
    }
    private static TrapTo mInstance = null;

    public static synchronized TrapTo getInstance() {
        if (mInstance == null) {
            mInstance = new TrapTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if(moving.isBlock() && moving.isForced()) {
            game().addForcedMove(moving, moving.getFacing());
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
