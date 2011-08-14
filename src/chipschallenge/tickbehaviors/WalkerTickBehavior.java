/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move;
import chipschallenge.Move.Moves;
import chipschallenge.Utils;

/**
 *
 * @author patrik
 */
public class WalkerTickBehavior implements BlockTickBehavior {

    private WalkerTickBehavior() {}
    private static WalkerTickBehavior mInstance = null;
    public static synchronized WalkerTickBehavior getInstance() {
        if(mInstance == null)
            mInstance = new WalkerTickBehavior();
        return mInstance;
    }

    private boolean move(Block caller, Moves m) throws BlockContainerFullException {
        caller.setFacing(m);
        return caller.move(m);
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        Moves m = caller.getFacing();
        if(!caller.move(m)) {
            int dir = Utils.getRandom().nextInt(4);
            outer : for(int i = 0; i < 2; i++) {
                switch(dir) {
                    case 0:
                        if(move(caller, Moves.UP))
                            break outer;
                    case 1:
                        if(move(caller, Moves.DOWN))
                            break outer;
                    case 2:
                        if(move(caller, Moves.LEFT))
                            break outer;
                    case 3:
                        if(move(caller, Moves.RIGHT))
                            break outer;
                }
            }
        }
    }
}
