package chipschallenge.trapreleasebehaviors;

import chipschallenge.Block;

public class DefaultTrapReleaseBehavior implements TrapReleaseBehavior {

    private DefaultTrapReleaseBehavior() {
    }
    private static DefaultTrapReleaseBehavior mInstance = null;

    public static synchronized DefaultTrapReleaseBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new DefaultTrapReleaseBehavior();
        }
        return mInstance;
    }

    public void releaseFromTrap(Block trapped) {
        trapped.setTrapped(false);
    }
}
