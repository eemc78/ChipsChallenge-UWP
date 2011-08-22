package chipschallenge;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Buttons {

    private static Collection<Block> greenButtonsListeners = new CopyOnWriteArrayList<Block>();
    private static Collection<Block> blueButtonsListeners = new CopyOnWriteArrayList<Block>();
    private static Collection<Block> hintButtonsListeners = new CopyOnWriteArrayList<Block>();
    private static Map<Block, Collection<Block>> redButtonListeners = new HashMap<Block, Collection<Block>>();
    private static Map<Block, Collection<Block>> brownButtonListeners = new HashMap<Block, Collection<Block>>();
    private static boolean greenButton = false;
    private static boolean blueButton = false;

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

    public static void addHintButtonListener(Block b) {
        hintButtonsListeners.add(b);
    }

    public static void removeGreenButtonsListener(Block listener) {
        greenButtonsListeners.remove(listener);
    }

    public static void removeBlueButtonsListener(Block listener) {
        blueButtonsListeners.remove(listener);
    }

    public static void removeHintButtonListener(Block b) {
        hintButtonsListeners.remove(b);
    }

    public static void addRedButtonListener(Block button, Block listener) {
        Collection<Block> listeners = redButtonListeners.get(button);
        if (listeners == null) {
            listeners = new CopyOnWriteArrayList<Block>();
            redButtonListeners.put(button, listeners);
        }
        listeners.add(listener);
    }

    public static void addBrownButtonListener(Block button, Block listener) {
        Collection<Block> listeners = brownButtonListeners.get(button);
        if (listeners == null) {
            listeners = new CopyOnWriteArrayList<Block>();
            brownButtonListeners.put(button, listeners);
        }
        listeners.add(listener);
    }

    public static void greenButtonDown(Block button) {
        greenButton = !greenButton;
        /*
        for (Block b : greenButtonsListeners) {
            b.buttonDown(button);
        }
         */
    }

    public static void greenButtonUp(Block button) {
        /*
        for (Block b : greenButtonsListeners) {
            b.buttonUp(button);
        }
         */
    }

    public static void blueButtonDown(Block button) {
        blueButton = !blueButton;
        /*
        for (Block b : blueButtonsListeners) {
            b.buttonDown(button);
        }
         */
    }

    public static void blueButtonUp(Block button) {
        /*
        for (Block b : blueButtonsListeners) {
            b.buttonUp(button);
        }
         */
    }

    public static void updateGreenandBlueButtons() {
        if(greenButton) {
            for (Block b : greenButtonsListeners) {
                b.buttonDown(null);
            }
        }
        if(blueButton) {
            for (Block b : blueButtonsListeners) {
                b.buttonDown(null);
            }
        }
        greenButton = false;
        blueButton = false;
    }

    public static void redButtonDown(Block button) {
        Collection<Block> listeners = redButtonListeners.get(button);
        if (listeners != null) {
            for (Block b : listeners) {
                b.buttonDown(button);
            }
        }
    }

    public static void redButtonUp(Block button) {
        Collection<Block> listeners = redButtonListeners.get(button);
        if (listeners != null) {
            for (Block b : listeners) {
                b.buttonUp(button);
            }
        }
    }

    public static void brownButtonDown(Block button) {
        Collection<Block> listeners = brownButtonListeners.get(button);
        if (listeners != null) {
            for (Block b : listeners) {
                b.buttonDown(button);
            }
        }
    }

    public static void brownButtonUp(Block button) {
        Collection<Block> listeners = brownButtonListeners.get(button);
        if (listeners != null) {
            for (Block b : listeners) {
                b.buttonUp(button);
            }
        }
    }
}
