/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;

/**
 *
 * @author patrik
 */
public class FlippersTo extends BlockReaction {

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean canMove(Block moving, Block standing) {
        return isChip(moving);
    }

}
