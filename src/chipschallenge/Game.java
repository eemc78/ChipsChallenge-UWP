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
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author patrik
 */
public class Game {

    private static final long MOVE_MS = 250;
    private static final long SPEED_FRAC = 3;
    private static final long TIMER_TICK = MOVE_MS/SPEED_FRAC;
    private Set<GameListener> listeners = new HashSet<GameListener>();
    private static Game mGame = null;
    private Inventory mInventory = new Inventory();
    private GameLevel mLevel = null;
    private Map<Block, Moves> forcedMoves = new HashMap<Block, Moves>();
    private GameState mGameState = NullGameState.getInstance();
    private BlockFactory mBlockFactory = null;
    private LevelFactory mLevelFactory = null;
    private int mLevelNumber = 0;

    private Game(){}

    public static synchronized Game getInstance() {
        if(mGame == null)
            mGame = new Game();
        return mGame;
    }

    public void nextLevel() {
        mLevel = mLevelFactory.getLevel(++mLevelNumber);
        start();
    }

    public void start() {
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                try {
                    tick();
                } catch (BlockContainerFullException ex) {
                    System.out.println("Block container is full!");
                    System.out.println(ex.getMessage());
                    System.exit(-1);
                } 
            }
        };
        t.schedule(tt, 0, TIMER_TICK);
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

    public void setLevelFactory(LevelFactory lf) {
        this.mLevelFactory = lf;
    }

}
