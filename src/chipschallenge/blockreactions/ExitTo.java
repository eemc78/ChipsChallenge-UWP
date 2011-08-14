/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;

/**
 *
 * @author patrik
 */
public class ExitTo extends BlockReaction {

    private ExitTo() {}
    private static ExitTo mInstance = null;
    public static synchronized ExitTo getInstance() {
        if(mInstance == null)
            mInstance = new ExitTo();
        return mInstance;
    }

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if(isChip(moving)) {
            game().levelComplete();
        }
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return isChip(moving) || isBlock(moving);
    }
}
