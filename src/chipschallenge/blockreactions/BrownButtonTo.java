/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author patrik
 */
public class BrownButtonTo implements BlockReaction {

    private Set<Block> listeners = new HashSet<Block>();

    public void react(Block moving, Block standing) {
        for(Block b : listeners) {
            b.buttonDown(standing);
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
