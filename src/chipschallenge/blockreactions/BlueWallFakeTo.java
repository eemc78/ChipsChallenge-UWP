package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;

/**
 *
 * @author patrik
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

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        standing.replace(createBlock(Type.FLOOR));
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
