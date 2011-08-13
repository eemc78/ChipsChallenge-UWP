/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;

/**
 *
 * @author patrik
 */
public class DirtTo extends BlockReaction {

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        standing.replace(createBlock(Type.FLOOR));
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return isChip(moving);
    }

}
