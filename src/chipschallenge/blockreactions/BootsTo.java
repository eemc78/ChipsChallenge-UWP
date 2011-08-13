/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Game;
import chipschallenge.Inventory.Boots;

/**
 *
 * @author patrik
 */
public class BootsTo extends BlockReaction {

    public void react(Block moving, Block standing) {
        switch(standing.getType()) {
            case FLIPPERS:
                takeBoots(Boots.FLIPPERS);
                break;
            case FIREBOOTS:
                takeBoots(Boots.FIREBOOTS);
                break;
            case ICESKATES:
                takeBoots(Boots.ICESKATES);
                break;
            case SUCTIONBOOTS:
                takeBoots(Boots.SUCTIONBOOTS);
                break;
        }
        standing.destroy();
    }

    public boolean canMove(Block moving, Block standing) {
        return isChip(moving);
    }

}