/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Game;

/**
 *
 * @author patrik
 */
public class ThiefTo implements BlockReaction {

    public void react(Block moving, Block standing) {
        Game.getInstance().getInventory().clearBoots();
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.getType() == Block.Type.CHIP;
    }

}
