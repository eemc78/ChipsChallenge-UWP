package chipschallenge;

import chipschallenge.Move.Moves;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author patrik
 */
public class Game implements Runnable {

    private static final long TIME_BETWEEN_TICKS = 500;
    private Set<GameListener> listeners = new HashSet<GameListener>();
    private static Game mGame = null;
    private Inventory mInventory = new Inventory();
    private GameLevel mLevel = null;
    private Game(){}

    public static synchronized Game getInstance() {
        if(mGame == null)
            mGame = new Game();
        return mGame;
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(TIME_BETWEEN_TICKS);
                tick();
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
    
    public void addGameListener(GameListener l) {
        listeners.add(l);
    }

    public void removeGameListener(GameListener l) {
        listeners.remove(l);
    }

    public void tick() {
        for(GameListener l : listeners) {
            l.tick();
        }
    }

    public void die(String msg) {

    }

}
