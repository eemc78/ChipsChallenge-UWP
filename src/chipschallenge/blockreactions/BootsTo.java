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
                Game.getInstance().getInventory().takeBoots(Boots.FLIPPERS);
                break;
            case FIREBOOTS:
                Game.getInstance().getInventory().takeBoots(Boots.FIREBOOTS);
                break;
            case ICESKATES:
                Game.getInstance().getInventory().takeBoots(Boots.ICESKATES);
                break;
            case SUCTIONBOOTS:
                Game.getInstance().getInventory().takeBoots(Boots.SUCTIONBOOTS);
                break;
        }
        standing.destroy();
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.getType() == Block.Type.CHIP;
    }

}