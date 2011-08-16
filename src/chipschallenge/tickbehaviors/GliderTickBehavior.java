package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;

/**
 *
 * @author patrik
 */
public class GliderTickBehavior implements BlockTickBehavior {

    private GliderTickBehavior() {
    }
    private static GliderTickBehavior mInstance = null;

    public static synchronized GliderTickBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new GliderTickBehavior();
        }
        return mInstance;
    }

    private boolean move(Block caller, Moves m) throws BlockContainerFullException {
        //caller.setFacing(m);
        return caller.move(m);
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        Moves m = caller.getFacing();
        switch (m) {
            case UP:
                if (move(caller, Moves.UP) || move(caller, Moves.LEFT) || move(caller, Moves.RIGHT) || move(caller, Moves.DOWN)) {
                    return;
                }
            case RIGHT:
                if (move(caller, Moves.RIGHT) || move(caller, Moves.UP) || move(caller, Moves.DOWN) || move(caller, Moves.LEFT)) {
                    return;
                }
            case LEFT:
                if (move(caller, Moves.LEFT) || move(caller, Moves.DOWN) || move(caller, Moves.UP) || move(caller, Moves.RIGHT)) {
                    return;
                }
            case DOWN:
                if (move(caller, Moves.DOWN) || move(caller, Moves.RIGHT) || move(caller, Moves.LEFT) || move(caller, Moves.UP)) {
                    return;
                }
        }

    }
}
