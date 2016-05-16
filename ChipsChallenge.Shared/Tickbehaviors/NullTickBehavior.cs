package chipschallenge.tickbehaviors;

import chipschallenge.Block;

public class NullTickBehavior implements BlockTickBehavior {

    private NullTickBehavior() {
    }
    private static NullTickBehavior mInstance = null;

    public static synchronized NullTickBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new NullTickBehavior();
        }
        return mInstance;
    }

    public void tick(Block caller) {
        // Ignore
    }
}
