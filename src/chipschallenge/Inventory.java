package chipschallenge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Inventory {

    public static enum Key {

        BLUE, GREEN, RED, YELLOW
    }

    public static enum Boots {

        FIREBOOTS, FLIPPERS, ICESKATES, SUCTIONBOOTS
    }
    private Set<Boots> mBoots = new HashSet<Boots>();
    private Collection<Key> mKeys = new ArrayList<Key>();
    private Collection<InventoryListener> listeners = new ArrayList<InventoryListener>();

    public void clear() {
        mBoots.clear();
        mKeys.clear();
        update();
    }

    public Set<Boots> getBoots() {
        return mBoots;
    }

    public Collection<Key> getKeys() {
        return mKeys;
    }

    public void takeKey(Key type) {
        if (mKeys.add(type)) {
            update();
        }
    }

    public void takeBoots(Boots type) {
        if (mBoots.add(type)) {
            update();
        }
    }

    public boolean useKey(Key type) {
        if (type == Key.GREEN) {
            return mKeys.contains(type);
        }
        boolean ret;
        if (ret = mKeys.remove(type)) {
            update();
        }
        return ret;
    }

    public boolean hasKey(Key type) {
        return mKeys.contains(type);
    }

    public boolean hasBoots(Boots type) {
        return mBoots.contains(type);
    }

    public void clearKeys() {
        mKeys.clear();
        update();
    }

    public void clearBoots() {
        mBoots.clear();
        update();
    }

    public void addInventoryListener(InventoryListener l) {
        listeners.add(l);
    }

    public void removeInventoryListener(InventoryListener l) {
        listeners.remove(l);
    }

    public void update() {
        for (InventoryListener l : listeners) {
            l.inventoryChange(this);
        }
    }
}
