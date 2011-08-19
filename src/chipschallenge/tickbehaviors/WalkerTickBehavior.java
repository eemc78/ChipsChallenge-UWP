package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;
import chipschallenge.Utils;

public class WalkerTickBehavior implements BlockTickBehavior {

    private WalkerTickBehavior() {
    }
    private static WalkerTickBehavior mInstance = null;

    public static synchronized WalkerTickBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new WalkerTickBehavior();
        }
        return mInstance;
    }

    private boolean move(Block caller, Moves m) throws BlockContainerFullException {
        caller.setFacing(m);
        return caller.move(m);
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        Moves m = caller.getFacing();
        if (!caller.move(m)) {
            int dir = Utils.r.nextInt(4);
            outer:
            for (int i = 0; i < 2; i++) {
                switch (dir) {
                    case 0:
                        if (move(caller, Moves.UP)) {
                            break outer;
                        } else
                        caller.setFacing(m);
                    case 1:
                        if (move(caller, Moves.DOWN)) {
                            break outer;
                        } else
                        caller.setFacing(m);
                    case 2:
                        if (move(caller, Moves.LEFT)) {
                            break outer;
                        } else
                        caller.setFacing(m);
                    case 3:
                        if (move(caller, Moves.RIGHT)) {
                            break outer;
                        } else {
                            dir = 0;
                            caller.setFacing(m);
                        }
                }
            }
        }
    }
}
