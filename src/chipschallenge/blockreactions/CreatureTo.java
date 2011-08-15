package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public class CreatureTo extends BlockReaction {

    private CreatureTo() {
    }
    private static CreatureTo mInstance = null;

    public static synchronized CreatureTo getInstance() {
        if (mInstance == null) {
            mInstance = new CreatureTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if (moving != standing) {
            die("Ooops! Look out for creatures!");
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
