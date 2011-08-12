/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Game;
import chipschallenge.Inventory.Key;

/**
 *
 * @author patrik
 */
public class BlueKeyTo implements BlockReaction {

    public void react(Block moving, Block standing) {
        if(moving.getType() == Block.Type.CHIP) {
            Game.getInstance().getInventory().takeKey(Key.BLUE);
            standing.destroy();
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

}
