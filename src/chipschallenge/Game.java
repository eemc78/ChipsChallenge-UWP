package chipschallenge;

import chipschallenge.gamestates.GameState;
import chipschallenge.Move.Moves;
import chipschallenge.gamestates.NullGameState;
import chipschallenge.gui.GUI;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    private Map<Block, Moves> forcedMoves = new HashMap<Block, Moves>();
    private GameState mGameState = NullGameState.getInstance();
    private BlockFactory mBlockFactory;

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
            } catch (BlockContainerFullException ex) {
                System.out.println("Block container is full!");
                System.out.println(ex.getMessage());
                System.exit(-1);
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

    public void tick() throws BlockContainerFullException {
        for(GameListener l : listeners) {
            l.tick();
        }
    }

    public void die(String msg) {
        //TODO: Play "Bummer"
        GUI.getInstance().msgDialog(msg);
    }

    public GameLevel getLevel() {
        return mLevel;
    }

    public void keyPressed(KeyEvent ke) {
        mGameState.keyPressed(this, ke);
    }

    public Inventory getInventory() {
        return mInventory;
    }

    public BlockFactory getBlockFactory() {
        return mBlockFactory;
    }

    public void setBlockFactory(BlockFactory bf) {
        mBlockFactory = bf;
    }

    public void setLevel(GameLevel gl) {
        mLevel = gl;
    }

    public void setGameState(GameState state) {
        mGameState = state;
    }

}
