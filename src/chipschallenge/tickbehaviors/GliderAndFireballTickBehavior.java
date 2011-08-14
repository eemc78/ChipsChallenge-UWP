package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;

/**
 *
 * @author patrik
 */
public class GliderAndFireballTickBehavior implements BlockTickBehavior {

    private GliderAndFireballTickBehavior() {}
    private static GliderAndFireballTickBehavior mInstance = null;
    public static synchronized GliderAndFireballTickBehavior getInstance() {
        if(mInstance == null)
            mInstance = new GliderAndFireballTickBehavior();
        return mInstance;
    }

    private boolean move(Block caller, Moves m) throws BlockContainerFullException {
        caller.setFacing(m);
        return caller.move(m);
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        Moves m = caller.getFacing();
            switch(m) {
                case UP:
                    if(move(caller,Moves.UP) || move(caller,Moves.RIGHT) || move(caller,Moves.LEFT) || move(caller,Moves.DOWN))
                        return;
                case RIGHT:
                    if(move(caller,Moves.RIGHT) || move(caller,Moves.DOWN) || move(caller,Moves.UP) || move(caller,Moves.LEFT))
                        return;
                case LEFT:
                    if(move(caller,Moves.LEFT) || move(caller,Moves.UP) || move(caller,Moves.DOWN) || move(caller,Moves.RIGHT))
                        return;
                case DOWN:
                    if(move(caller,Moves.DOWN) || move(caller,Moves.LEFT) || move(caller,Moves.RIGHT) || move(caller,Moves.UP))
                        return;
            }
        
    }

}
