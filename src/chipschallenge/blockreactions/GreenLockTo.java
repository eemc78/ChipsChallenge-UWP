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
public class GreenLockTo implements BlockReaction {

    public void react(Block moving, Block standing) {
        Game.getInstance().getInventory().useKey(Key.GREEN);
        standing.destroy();
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.getType() == Block.Type.CHIP &&
               Game.getInstance().getInventory().hasKey(Key.GREEN);
    }

}