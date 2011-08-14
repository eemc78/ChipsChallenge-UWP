package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;

/**
 *
 * @author patrik
 */
public class DirtTo extends BlockReaction {

    private DirtTo() {}
    private static DirtTo mInstance = null;
    public static synchronized DirtTo getInstance() {
        if(mInstance == null)
            mInstance = new DirtTo();
        return mInstance;
    }

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        standing.replace(createBlock(Type.FLOOR));
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return isChip(moving);
    }

}
