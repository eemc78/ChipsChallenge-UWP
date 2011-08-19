package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;

public class ThinWallSeFrom extends BlockReaction {

    private ThinWallSeFrom() {
    }
    private static ThinWallSeFrom mInstance = null;

    public static synchronized ThinWallSeFrom getInstance() {
        if (mInstance == null) {
            mInstance = new ThinWallSeFrom();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        // No reaction;
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.getFacing() == Moves.UP || moving.getFacing() == Moves.LEFT;
    }
}
