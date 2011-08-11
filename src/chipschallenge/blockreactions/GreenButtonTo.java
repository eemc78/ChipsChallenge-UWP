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
public class GreenButtonTo implements BlockReaction {

    private static Set<Block> listeners = new HashSet<Block>();

    public void react(Block moving, Block standing) {
        for(Block b : listeners) {
            b.buttonHit(standing);
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

    public static void addGreenButtonsListener(Block b) {
        listeners.add(b);
    }

    public static void removeGreenButtonsListener(Block b) {
        listeners.remove(b);
    }

}
