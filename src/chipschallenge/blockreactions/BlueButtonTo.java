package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Buttons;

/**
 * Moving to a Blue Button
 */
public class BlueButtonTo extends BlockReaction {

    private BlueButtonTo() {
    }
    private static BlueButtonTo mInstance = null;

    public static synchronized BlueButtonTo getInstance() {
        if (mInstance == null) {
            mInstance = new BlueButtonTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        Buttons.blueButtonDown(standing);
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
