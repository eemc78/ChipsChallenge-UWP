package chipschallenge.trapreleasebehaviors;

import chipschallenge.Block;
import chipschallenge.Creatures;

public class ControllerTrapReleaseBehavior implements TrapReleaseBehavior {

    private ControllerTrapReleaseBehavior() {
    }
    private static ControllerTrapReleaseBehavior mInstance = null;

    public static synchronized ControllerTrapReleaseBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new ControllerTrapReleaseBehavior();
        }
        return mInstance;
    }

    public void releaseFromTrap(Block trapped) {
        Block controller = Creatures.getController(trapped);
        if (controller != null) {
            trapped.setFacing(controller.getFacing());
            trapped.setTrapped(false);
        }
    }
}
