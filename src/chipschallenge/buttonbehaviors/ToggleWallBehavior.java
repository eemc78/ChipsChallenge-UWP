package chipschallenge.buttonbehaviors;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Game;

/**
 * If the button was green and the listener is a toggle wall. Toggle it.
 */
public class ToggleWallBehavior implements ButtonBehavior {

    private ToggleWallBehavior() {
    }
    private static ToggleWallBehavior mInstance = null;

    public static synchronized ToggleWallBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new ToggleWallBehavior();
        }
        return mInstance;
    }

    public void buttonDown(Block listener, Block button) {
        Game.getInstance().moveOccured(listener.getPoint());
        if (listener.isA(Block.Type.TOGGLEWALLOPEN)) {
            listener.replace(Block.create(Type.TOGGLEWALLCLOSED));
        } else if (listener.isA(Type.TOGGLEWALLCLOSED)) {
            listener.replace(Block.create(Type.TOGGLEWALLOPEN));
        }
    }

    public void buttonUp(Block listener, Block button) {
        // Do nothing
    }
}
