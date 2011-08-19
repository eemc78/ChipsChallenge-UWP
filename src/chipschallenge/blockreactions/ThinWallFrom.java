package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;

public class ThinWallFrom extends BlockReaction {

    private ThinWallFrom() {
    }
    private static ThinWallFrom mInstance = null;

    public static synchronized ThinWallFrom getInstance() {
        if (mInstance == null) {
            mInstance = new ThinWallFrom();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        // No reaction;
    }

    public boolean canMove(Block moving, Block standing) {
        switch (moving.getFacing()) {
            case UP:
                return standing.getFacing() != Moves.UP;
            case DOWN:
                return standing.getFacing() != Moves.DOWN;
            case LEFT:
                return standing.getFacing() != Moves.LEFT;
            case RIGHT:
                return standing.getFacing() != Moves.RIGHT;
        }
        return false;
    }
}
