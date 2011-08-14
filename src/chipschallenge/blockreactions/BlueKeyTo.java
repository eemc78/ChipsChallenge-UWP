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
public class BlueKeyTo extends BlockReaction {

    private BlueKeyTo() {}
    private static BlueKeyTo mInstance = null;
    public static synchronized BlueKeyTo getInstance() {
        if(mInstance == null)
            mInstance = new BlueKeyTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if(isChip(moving)) {
            takeKey(Key.BLUE);
            standing.destroy();
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

}
