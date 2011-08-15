package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Inventory.Boots;

/**
 * Move to fire
 */
public class FireTo extends BlockReaction {

    private FireTo() {
    }
    private static FireTo mInstance = null;

    public static synchronized FireTo getInstance() {
        if (mInstance == null) {
            mInstance = new FireTo();
        }
        return mInstance;
    }

    // Kills all enemies except fireball
    public void react(Block moving, Block standing) {
        switch (moving.getType()) {
            case CHIP:
                if (!hasBoots(Boots.FIREBOOTS)) {
                    moving.destroy();
                    standing.replace(createBlock(Type.BURNEDCHIP));
                    die("Ooops! Don't step in the fire without fire boots!");
                }
                break;
            case BUG:
            case TEETH:
            case PINKBALL:
            case TANK:
            case BLOB:
                moving.destroy();
                break;
        }
    }

    // Everyone can move here except bugs or walkers
    public boolean canMove(Block moving, Block standing) {
        return !(moving.isA(Type.BUG) || moving.isA(Type.WALKER));
    }
}
