package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public class ThiefTo extends BlockReaction {

    private ThiefTo() {}
    private static ThiefTo mInstance = null;
    public static synchronized ThiefTo getInstance() {
        if(mInstance == null)
            mInstance = new ThiefTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        inventory().clearBoots();
        //TODO: Play sound
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }

}
