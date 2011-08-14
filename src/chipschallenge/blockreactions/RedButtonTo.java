package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Buttons;

/**
 *
 * @author patrik
 */
public class RedButtonTo extends BlockReaction {

    private RedButtonTo() {}
    private static RedButtonTo mInstance = null;
    public static synchronized RedButtonTo getInstance() {
        if(mInstance == null)
            mInstance = new RedButtonTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        Buttons.redButtonDown(standing);
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }



}
