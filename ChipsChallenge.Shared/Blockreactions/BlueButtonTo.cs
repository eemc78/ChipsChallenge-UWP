package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Buttons;
import chipschallenge.SoundPlayer.sounds;

/**
 * Moving to a Blue Button
 */
public class BlueButtonTo extends NoSlipReaction {

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
        sound().playSound(sounds.BUTTON);
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
