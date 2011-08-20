package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Boots;

/**
 * Move to boots
 */
public class BootsTo extends NoSlipReaction {

    private BootsTo() {
    }
    private static BootsTo mInstance = null;

    public static synchronized BootsTo getInstance() {
        if (mInstance == null) {
            mInstance = new BootsTo();
        }
        return mInstance;
    }

    // Pick up boots
    public void react(Block moving, Block standing) {
        switch (standing.getType()) {
            case FLIPPERS:
                takeBoots(Boots.FLIPPERS);
                break;
            case FIREBOOTS:
                takeBoots(Boots.FIREBOOTS);
                break;
            case ICESKATES:
                takeBoots(Boots.ICESKATES);
                break;
            case SUCTIONBOOTS:
                takeBoots(Boots.SUCTIONBOOTS);
                break;
        }
        standing.destroy();
    }

    // Chip and blocks can move here
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() || moving.isBlock();
    }
}
