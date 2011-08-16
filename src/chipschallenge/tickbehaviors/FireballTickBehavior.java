package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;

/**
 *
 * @author patrik
 */
public class FireballTickBehavior implements BlockTickBehavior {

    private FireballTickBehavior() {
    }
    private static FireballTickBehavior mInstance = null;

    public static synchronized FireballTickBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new FireballTickBehavior();
        }
        return mInstance;
    }

    private boolean move(Block caller, Moves m) throws BlockContainerFullException {
        Moves before = caller.getFacing();
        boolean ret = caller.move(m);
        if(!ret)
            caller.setFacing(before);
        return ret;
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        Moves m = caller.getFacing();
        switch (m) {
            case UP:
                if (move(caller, Moves.UP) || move(caller, Moves.RIGHT) || move(caller, Moves.LEFT) || move(caller, Moves.DOWN)) {
                    return;
                }
            case RIGHT:
                if (move(caller, Moves.RIGHT) || move(caller, Moves.DOWN) || move(caller, Moves.UP) || move(caller, Moves.LEFT)) {
                    return;
                }
            case LEFT:
                if (move(caller, Moves.LEFT) || move(caller, Moves.UP) || move(caller, Moves.DOWN) || move(caller, Moves.RIGHT)) {
                    return;
                }
            case DOWN:
                if (move(caller, Moves.DOWN) || move(caller, Moves.LEFT) || move(caller, Moves.RIGHT) || move(caller, Moves.UP)) {
                    return;
                }
        }

    }
}
