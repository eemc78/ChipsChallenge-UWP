package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Buttons;

/**
 *
 * @author patrik
 */
public class GreenButtonTo extends BlockReaction {

    private GreenButtonTo() {
    }
    private static GreenButtonTo mInstance = null;

    public static synchronized GreenButtonTo getInstance() {
        if (mInstance == null) {
            mInstance = new GreenButtonTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        Buttons.greenButtonDown(standing);
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
