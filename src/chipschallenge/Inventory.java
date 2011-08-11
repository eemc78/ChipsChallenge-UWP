/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author patrik
 */
public class Inventory {
    
    public static enum Key {
        BLUE, GREEN, RED, YELLOW
    }
    public static enum Boots {
        FIREBOOTS, FLIPPERS, ICESKATES, SUCTIONBOOTS
    }

    private Set<Boots> mBoots = new HashSet<Boots>();
    private Collection<Key> mKeys = new ArrayList<Key>();

    public void takeKey(Key type) {
        mKeys.add(type);
    }

    public void takeBoots(Boots type) {
        mBoots.add(type);
    }

    public boolean useKey(Key type) {
        if(type == Key.GREEN)
            return mKeys.contains(type);
        return mKeys.remove(type);
    }

    public void clearKeys() {
        mKeys.clear();
    }

    public void clearBoots() {
        mBoots.clear();
    }

    public void clearAll() {
        clearKeys();
        clearBoots();
    }
}
