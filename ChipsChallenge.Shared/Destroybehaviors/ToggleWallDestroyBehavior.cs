package chipschallenge.destroybehaviors;

import chipschallenge.Block;
import chipschallenge.Buttons;

public class ToggleWallDestroyBehavior implements DestroyBehavior {

    private ToggleWallDestroyBehavior() {
    }
    private static ToggleWallDestroyBehavior mInstance = null;

    public static synchronized ToggleWallDestroyBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new ToggleWallDestroyBehavior();
        }
        return mInstance;
    }

    public void destroy(Block b) {
        Buttons.removeGreenButtonsListener(b);
    }
}
