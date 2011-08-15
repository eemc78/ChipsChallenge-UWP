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
public class ThinWallSeFrom extends BlockReaction {

    private ThinWallSeFrom() {}
    private static ThinWallSeFrom mInstance = null;
    public static synchronized ThinWallSeFrom getInstance() {
        if(mInstance == null)
            mInstance = new ThinWallSeFrom();
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        // No reaction;
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.getFacing() == Moves.UP || moving.getFacing() == Moves.LEFT;
    }

}
