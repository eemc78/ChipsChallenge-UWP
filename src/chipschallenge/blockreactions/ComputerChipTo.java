
package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;


public class ComputerChipTo extends BlockReaction {

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        Game.getInstance().takeChip();
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }

}
