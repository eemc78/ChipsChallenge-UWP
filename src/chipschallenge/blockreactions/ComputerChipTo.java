
package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;


public class ComputerChipTo extends BlockReaction {

    private ComputerChipTo() {}
    private static ComputerChipTo mInstance = null;

    public static synchronized ComputerChipTo getInstance() {
        if (mInstance == null) {
            mInstance = new ComputerChipTo();
        }
        return mInstance;
    }

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        game().takeChip();
        standing.replace(createBlock(Type.FLOOR));
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }

}
