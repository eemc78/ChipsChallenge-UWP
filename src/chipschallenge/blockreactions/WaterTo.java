package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Game;
import chipschallenge.Inventory.Boots;

/**
 *
 * @author patrik
 */
public class WaterTo extends BlockReaction {

    private WaterTo() {
    }
    private static WaterTo mInstance = null;

    public static synchronized WaterTo getInstance() {
        if (mInstance == null) {
            mInstance = new WaterTo();
        }
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        Game g = Game.getInstance();
        switch (moving.getType()) {
            case CHIP:
                if (hasBoots(Boots.FLIPPERS)) {
                    moving.setType(Type.SWIMMINGCHIP);
                } else {
                    moving.destroy();
                    standing.replace(createBlock(Type.DROWNEDCHIP));
                    g.die("Ooops! Chip can't swim without flippers!");
                }
                break;
            case BLOCK:
                moving.destroy();
                standing.replace(createBlock(Type.DIRT));
                break;
            case BUG:
            case TEETH:
            case FIREBALL:
            case PINKBALL:
            case WALKER:
            case TANK:
            case BLOB:
                moving.destroy();
                break;
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
