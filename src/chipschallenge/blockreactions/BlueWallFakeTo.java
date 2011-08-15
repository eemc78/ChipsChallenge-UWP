package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;

/**
 * Move to Blue fake wall
 */
public class BlueWallFakeTo extends BlockReaction {

    private BlueWallFakeTo() {
    }
    private static BlueWallFakeTo mInstance = null;

    public static synchronized BlueWallFakeTo getInstance() {
        if (mInstance == null) {
            mInstance = new BlueWallFakeTo();
        }
        return mInstance;
    }

    // Wall is converted to floor
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        standing.replace(createBlock(Type.FLOOR));
    }

    // Only chip can move to it
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
