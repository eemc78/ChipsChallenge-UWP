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
public class YellowKeyTo extends BlockReaction {

    private YellowKeyTo() {}
    private static YellowKeyTo mInstance = null;
    public static synchronized YellowKeyTo getInstance() {
        if(mInstance == null)
            mInstance = new YellowKeyTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if(isChip(moving)) {
            Game.getInstance().getInventory().takeKey(Key.YELLOW);
            standing.destroy();
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

}
