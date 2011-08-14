/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package creaturetickbehavior;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;
import chipschallenge.tickbehaviors.BlockTickBehavior;

/**
 *
 * @author patrik
 */
public class BugTickBehavior implements BlockTickBehavior {

    private BugTickBehavior() {}
    private static BugTickBehavior mInstance = null;
    public static synchronized BugTickBehavior getInstance() {
        if(mInstance == null)
            mInstance = new BugTickBehavior();
        return mInstance;
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        Moves m = caller.getFacing();
        outer: for(int i = 0; i < 2; i++) {
            switch(m) {
                case UP:
                    caller.setFacing(Moves.LEFT);
                    if(caller.move(Moves.LEFT))                    
                        break outer;
                case RIGHT:
                    caller.setFacing(Moves.UP);
                    if(caller.move(Moves.UP))                      
                        break outer;
                case DOWN:
                    caller.setFacing(Moves.RIGHT);
                    if(caller.move(Moves.RIGHT))                       
                        break outer;
                case LEFT:
                    caller.setFacing(Moves.DOWN);
                    if(caller.move(Moves.DOWN))                      
                        break outer;
                    else
                        m = Moves.UP;
            }
        }
    }

}
