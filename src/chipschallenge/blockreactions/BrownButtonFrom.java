package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Buttons;

/**
 *
 * @author patrik
 */
public class BrownButtonFrom extends BlockReaction {

    private BrownButtonFrom() {}
    private static BrownButtonFrom mInstance = null;
    public static synchronized BrownButtonFrom getInstance() {
        if(mInstance == null)
            mInstance = new BrownButtonFrom();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        Buttons.brownButtonUp(standing);
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

}
