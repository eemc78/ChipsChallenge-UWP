/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public class CanMoveBlockReaction implements BlockReaction {
    
    private CanMoveBlockReaction() {}
    private static CanMoveBlockReaction mInstance = null;
    public static synchronized CanMoveBlockReaction getInstance() {
        if(mInstance == null)
            mInstance = new CanMoveBlockReaction();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        // No reaction
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

}
