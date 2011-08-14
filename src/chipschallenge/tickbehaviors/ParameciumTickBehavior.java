package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;

/**
 *
 * @author patrik
 */
public class ParameciumTickBehavior implements BlockTickBehavior {

    private ParameciumTickBehavior() {}
    private static ParameciumTickBehavior mInstance = null;
    public static synchronized ParameciumTickBehavior getInstance() {
        if(mInstance == null)
            mInstance = new ParameciumTickBehavior();
        return mInstance;
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        Moves m = caller.getFacing();
        outer: for(int i = 0; i < 2; i++) {
            switch(m) {
                case UP:
                    caller.setFacing(Moves.RIGHT);
                    if(caller.move(Moves.RIGHT)) {
                        break outer;
                    }
                case LEFT:
                    caller.setFacing(Moves.UP);
                    if(caller.move(Moves.UP)) {                        
                        break outer;
                    }
                case DOWN:
                    caller.setFacing(Moves.LEFT);
                    if(caller.move(Moves.LEFT)) {
                        break outer;
                    }
                case RIGHT:
                    caller.setFacing(Moves.DOWN);
                    if(caller.move(Moves.DOWN)) {                       
                        break outer;
                    } else {
                        m = Moves.UP;
                    }
            }
        }
    }

}
