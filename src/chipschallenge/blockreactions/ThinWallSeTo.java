package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainer;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.GameLevel;
import chipschallenge.Move.Moves;

/**
 *
 * @author patrik
 */
public class ThinWallSeTo extends BlockReaction {

    private ThinWallSeTo() {}
    private static ThinWallSeTo mInstance = null;
    public static synchronized ThinWallSeTo getInstance() {
        if(mInstance == null)
            mInstance = new ThinWallSeTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        // No reaction;
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.getFacing() == Moves.RIGHT || moving.getFacing() == Moves.DOWN;
    }

}
