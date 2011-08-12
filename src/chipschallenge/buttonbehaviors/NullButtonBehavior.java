package chipschallenge.buttonbehaviors;

import chipschallenge.Block;
import chipschallenge.buttonbehaviors.ButtonBehavior;

/**
 *
 * @author patrik
 */
public class NullButtonBehavior implements ButtonBehavior {

    private NullButtonBehavior() {}
    private static NullButtonBehavior mInstance = null;
    public static synchronized NullButtonBehavior getInstance() {
        if(mInstance == null)
            mInstance = new NullButtonBehavior();
        return mInstance;
    }

    public void buttonDown(Block moving, Block button) {
        // Do nothing
    }

    public void buttonUp(Block listener, Block button) {
        // Do nothing
    }
    
}
