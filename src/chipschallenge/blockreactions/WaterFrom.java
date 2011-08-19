package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;

public class WaterFrom extends BlockReaction {

    private WaterFrom() {
    }
    private static WaterFrom mInstance = null;

    public static synchronized WaterFrom getInstance() {
        if (mInstance == null) {
            mInstance = new WaterFrom();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if (moving.isChip()) {
            moving.setType(Type.CHIP);
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
