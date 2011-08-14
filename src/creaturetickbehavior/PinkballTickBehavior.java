/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package creaturetickbehavior;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move;
import chipschallenge.Move.Moves;
import chipschallenge.tickbehaviors.BlockTickBehavior;

/**
 *
 * @author patrik
 */
public class PinkballTickBehavior implements BlockTickBehavior {

    private PinkballTickBehavior() {}
    private static PinkballTickBehavior mInstance = null;
    public static synchronized PinkballTickBehavior getInstance() {
        if(mInstance == null)
            mInstance = new PinkballTickBehavior();
        return mInstance;
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        Moves m = caller.getFacing();
        if(!caller.move(m)) {
            m = Move.reverse(m);
            caller.setFacing(m);
            caller.move(m);
        }
    }
}
