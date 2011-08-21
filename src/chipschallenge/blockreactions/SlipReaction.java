
package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;


public abstract class SlipReaction extends BlockReaction {

    @Override
    public abstract void react(Block moving, Block standing) throws BlockContainerFullException;

    @Override
    public abstract boolean canMove(Block moving, Block standing);

    @Override
    public final Moves causesSlip(Block moving, Block standing) {
        return moving.getFacing();
    }

}
