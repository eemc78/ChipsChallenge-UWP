package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Inventory.Boots;

/**
 *
 * @author patrik
 */
public class BootsTo extends BlockReaction {

    private BootsTo() {}
    private static BootsTo mInstance = null;
    public static synchronized BootsTo getInstance() {
        if(mInstance == null)
            mInstance = new BootsTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        switch(standing.getType()) {
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

    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }

}