package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;

/**
 * Standard behavior for when things can move
 */
public class CanMoveBlockReaction extends BlockReaction {

    private CanMoveBlockReaction() {
    }
    private static CanMoveBlockReaction mInstance = null;

    public static synchronized CanMoveBlockReaction getInstance() {
        if (mInstance == null) {
            mInstance = new CanMoveBlockReaction();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        if (moving.getType() == Type.SWIMMINGCHIP) {
            moving.setType(Type.CHIP);
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
