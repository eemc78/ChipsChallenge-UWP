package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Inventory.Key;

public class GreenKeyTo extends NoSlipReaction {

    private GreenKeyTo() {
    }
    private static GreenKeyTo mInstance = null;

    public static synchronized GreenKeyTo getInstance() {
        if (mInstance == null) {
            mInstance = new GreenKeyTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if (moving.isChip()) {
            takeKey(Key.GREEN);
            standing.replace(createBlock(Type.FLOOR));
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
