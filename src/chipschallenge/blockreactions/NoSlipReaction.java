
package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;


public abstract class NoSlipReaction extends BlockReaction {

    @Override
    public abstract void react(Block moving, Block standing) throws BlockContainerFullException;

    @Override
    public abstract boolean canMove(Block moving, Block standing);

    @Override
    public final boolean causesSlip(Block moving, Block standing) {
        return false;
    }

}
