/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;

/**
 *
 * @author patrik
 */
public class FlippersTo extends BlockReaction {

    private FlippersTo() {}
    private static FlippersTo mInstance = null;
    public static synchronized FlippersTo getInstance() {
        if(mInstance == null)
            mInstance = new FlippersTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        takeBoots(Boots.FLIPPERS);
        standing.destroy();
        //TODO: Play take-item sound
    }

    public boolean canMove(Block moving, Block standing) {
        return isChip(moving);
    }

}
