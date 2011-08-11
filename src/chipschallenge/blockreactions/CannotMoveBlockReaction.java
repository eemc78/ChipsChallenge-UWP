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
public class CannotMoveBlockReaction implements BlockReaction {
    
    private CannotMoveBlockReaction() {}
    private static CannotMoveBlockReaction mInstance = null;
    public static synchronized CannotMoveBlockReaction getInstance() {
        if(mInstance == null)
            mInstance = new CannotMoveBlockReaction();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        // No reaction
    }

    public boolean canMove(Block moving, Block standing) {
        return false;
    }

}
