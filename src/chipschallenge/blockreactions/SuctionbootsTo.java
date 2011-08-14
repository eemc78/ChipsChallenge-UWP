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
public class SuctionbootsTo extends BlockReaction {

    private SuctionbootsTo() {}
    private static SuctionbootsTo mInstance = null;
    public static synchronized SuctionbootsTo getInstance() {
        if(mInstance == null)
            mInstance = new SuctionbootsTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if(isChip(moving)) {
            takeBoots(Boots.SUCTIONBOOTS);
            standing.destroy();
            //TODO: Play take-item sound
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return isChip(moving) || isBlock(moving);
    }

}
