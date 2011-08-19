package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move;
import chipschallenge.Move.Moves;

public class PinkballTickBehavior implements BlockTickBehavior {

    private PinkballTickBehavior() {
    }
    private static PinkballTickBehavior mInstance = null;

    public static synchronized PinkballTickBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new PinkballTickBehavior();
        }
        return mInstance;
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        if(caller.isOnCloner())
            return;
        Moves m = caller.getFacing();
        if (!caller.move(m)) {
            m = Move.reverse(m);
            caller.setFacing(m);
            caller.move(m);
        }
    }
}
