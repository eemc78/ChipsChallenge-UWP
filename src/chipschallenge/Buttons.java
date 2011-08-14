/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author patrik
 */
public class Buttons {
    private static Collection<Block> greenButtonsListeners = new CopyOnWriteArrayList<Block>();
    private static Collection<Block> blueButtonsListeners = new CopyOnWriteArrayList<Block>();
    private static Map<Block,Collection<Block>> redButtonListeners = new HashMap<Block,Collection<Block>>();
    private static Map<Block,Collection<Block>> brownButtonListeners = new HashMap<Block,Collection<Block>>();

    public static void clear() {
        greenButtonsListeners.clear();
        blueButtonsListeners.clear();
        redButtonListeners.clear();
        brownButtonListeners.clear();
    }

    public static void addGreenButtonsListener(Block listener) {
        greenButtonsListeners.add(listener);
    }

    public static void addBlueButtonsListener(Block listener) {
        blueButtonsListeners.add(listener);
    }

    public static void addRedButtonListener(Block button, Block listener) {
        Collection<Block> listeners = redButtonListeners.get(button);
        if(listeners == null) {
            listeners = new CopyOnWriteArrayList<Block>();
            redButtonListeners.put(button, listeners);
        }
        listeners.add(listener);
    }

    public static void addBrownButtonListener(Block button, Block listener) {
        Collection<Block> listeners = brownButtonListeners.get(button);
        if(listeners == null) {
            listeners = new CopyOnWriteArrayList<Block>();
            brownButtonListeners.put(button, listeners);
        }
        listeners.add(listener);
    }

    public static void greenButtonDown(Block button) {
        for(Block b : greenButtonsListeners) {
            b.buttonDown(button);
        }
    }

    public static void greenButtonUp(Block button) {
        for(Block b : greenButtonsListeners) {
            b.buttonUp(button);
        }
    }

    public static void blueButtonDown(Block button) {
        for(Block b : blueButtonsListeners) {
            b.buttonDown(button);
        }
    }

    public static void blueButtonUp(Block button) {
        for(Block b : blueButtonsListeners) {
            b.buttonUp(button);
        }
    }

    public static void redButtonDown(Block button) {
        Collection<Block> listeners = redButtonListeners.get(button);
        if(listeners != null) {
            for(Block b : listeners) {
                b.buttonDown(button);
            }
        }
    }

    public static void redButtonUp(Block button) {
        Collection<Block> listeners = redButtonListeners.get(button);
        if(listeners != null) {
            for(Block b : listeners) {
                b.buttonUp(button);
            }
        }
    }

    public static void brownButtonDown(Block button) {
        Collection<Block> listeners = brownButtonListeners.get(button);
        if(listeners != null) {
            for(Block b : listeners) {
                b.buttonDown(button);
            }
        }
    }

    public static void brownButtonUp(Block button) {
        Collection<Block> listeners = brownButtonListeners.get(button);
        if(listeners != null) {
            for(Block b : listeners) {
                b.buttonUp(button);
            }
        }
    }

}
