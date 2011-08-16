package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;

/**
 *
 * @author patrik
 */
public class BugTickBehavior implements BlockTickBehavior {

    private BugTickBehavior() {
    }
    private static BugTickBehavior mInstance = null;

    public static synchronized BugTickBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new BugTickBehavior();
        }
        return mInstance;
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        Moves m = caller.getFacing();
        outer:
        for (int i = 0; i < 2; i++) {
            switch (m) {
                case UP:
                    if (caller.move(Moves.LEFT)) {
                        break outer;
                    } else
                        caller.setFacing(m);
                case RIGHT:
                    if (caller.move(Moves.UP)) {
                        break outer;
                    } else
                        caller.setFacing(m);
                case DOWN:
                    if (caller.move(Moves.RIGHT)) {
                        break outer;
                    } else
                        caller.setFacing(m);
                case LEFT:
                    if (caller.move(Moves.DOWN)) {
                        break outer;
                    } else {
                        caller.setFacing(m);
                        m = Moves.UP;
                    }
            }
        }
    }
}
