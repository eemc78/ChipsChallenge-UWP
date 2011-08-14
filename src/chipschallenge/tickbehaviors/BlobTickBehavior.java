/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;
import chipschallenge.Utils;
import chipschallenge.tickbehaviors.BlockTickBehavior;

/**
 *
 * @author patrik
 */
public class BlobTickBehavior implements BlockTickBehavior {

    private BlobTickBehavior() {}
    private static BlobTickBehavior mInstance = null;
    public static synchronized BlobTickBehavior getInstance() {
        if(mInstance == null)
            mInstance = new BlobTickBehavior();
        return mInstance;
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        int dir = Utils.r.nextInt(4);
        outer: for(int i = 0; i < 2; i++) {
            switch (dir) {
                case 0:
                    caller.setFacing(Moves.UP);
                    if(caller.move(Moves.UP))
                        break outer;
                case 1:
                    caller.setFacing(Moves.DOWN);
                    if(caller.move(Moves.DOWN))
                        break outer;
                case 2:
                    caller.setFacing(Moves.LEFT);
                    if(caller.move(Moves.LEFT))
                        break outer;
                case 3:
                    caller.setFacing(Moves.RIGHT);
                    if(caller.move(Moves.RIGHT))
                        break outer;
                    else
                        dir = 0;
            }
        }
    }

}
