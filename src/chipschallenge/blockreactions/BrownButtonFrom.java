package chipschallenge.blockreactions;

import chipschallenge.Block;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author patrik
 */
public class BrownButtonFrom implements BlockReaction {

    private Set<Block> listeners = new HashSet<Block>();

    public void react(Block moving, Block standing) {
        for(Block b : listeners) {
            b.buttonUp(standing);
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

    public void addBrownButtonListener(Block b) {
        listeners.add(b);
    }

    public void removeBrownButtonListener(Block b) {
        listeners.remove(b);
    }

}
