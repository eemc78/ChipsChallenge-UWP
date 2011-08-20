package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;

public class ThinWallSeTo extends NoSlipReaction {

    private ThinWallSeTo() {
    }
    private static ThinWallSeTo mInstance = null;

    public static synchronized ThinWallSeTo getInstance() {
        if (mInstance == null) {
            mInstance = new ThinWallSeTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        // No reaction;
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.getFacing() == Moves.RIGHT || moving.getFacing() == Moves.DOWN;
    }
}
