package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;

/**
 *
 * @author patrik
 */
public class ThinWallTo extends BlockReaction {

    private ThinWallTo() {
    }
    private static ThinWallTo mInstance = null;

    public static synchronized ThinWallTo getInstance() {
        if (mInstance == null) {
            mInstance = new ThinWallTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        // No reaction;
    }

    public boolean canMove(Block moving, Block standing) {
        switch (moving.getFacing()) {
            case UP:
                return standing.getFacing() != Moves.DOWN;
            case DOWN:
                return standing.getFacing() != Moves.UP;
            case LEFT:
                return standing.getFacing() != Moves.RIGHT;
            case RIGHT:
                return standing.getFacing() != Moves.LEFT;
        }
        return false;
    }
}
