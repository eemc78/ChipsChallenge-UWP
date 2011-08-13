/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Key;

/**
 *
 * @author patrik
 */
public class BlueLockTo extends BlockReaction {

    public void react(Block moving, Block standing) {
        useKey(Key.BLUE);
        standing.destroy();
    }

    public boolean canMove(Block moving, Block standing) {
        return isChip(moving) &&
               hasKey(Key.RED);
    }

}
