package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move;
import chipschallenge.Move.Moves;

/**
 * Move to real blue wall
 */
public class BlueWallRealTo extends BlockReaction {

    private BlueWallRealTo() {
    }
    private static BlueWallRealTo mInstance = null;

    public static synchronized BlueWallRealTo getInstance() {
        if (mInstance == null) {
            mInstance = new BlueWallRealTo();
        }
        return mInstance;
    }

    // Converstion to real wall
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        Moves facing = moving.getFacing();
        moving.move(Move.reverse(facing));
        moving.setFacing(facing);
        standing.replace(createBlock(Type.WALL));
    }

    // Only chip can move here
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
