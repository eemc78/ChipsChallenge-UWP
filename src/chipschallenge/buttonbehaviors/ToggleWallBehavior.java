package chipschallenge.buttonbehaviors;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Game;

/**
 * If the button was green and the listener is a toggle wall. Toggle it.
 */
public class ToggleWallBehavior extends ButtonBehavior {

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
        if (button.isA(Type.GREENBUTTON)) {
            Game.getInstance().moveHappened(listener.getPoint());
            if (listener.isA(Block.Type.TOGGLEWALLOPEN)) {
                listener.replace(createBlock(Type.TOGGLEWALLCLOSED));
            } else if (listener.isA(Type.TOGGLEWALLCLOSED)) {
                listener.replace(createBlock(Type.TOGGLEWALLOPEN));
            }
        }
    }

    public void buttonUp(Block listener, Block button) {
        // Do nothing
    }
}
