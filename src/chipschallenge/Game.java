package chipschallenge;

import chipschallenge.gamestates.GameState;
import chipschallenge.Move.Moves;
import chipschallenge.gamestates.NullGameState;
import chipschallenge.gui.GUI;
import chipschallenge.gui.MoveListener;
import creaturetickbehavior.CreatureTickBehavior;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
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

    public static final int MOVE_MS = 250;
    public static final int SPEED_FRAC = 3;
    public static final int TIMER_TICK = MOVE_MS/SPEED_FRAC;
    private Set<GameListener> gameListeners = new HashSet<GameListener>();
    private Set<Block> creatures = new HashSet<Block>();
    private Set<Block> blobs = new HashSet<Block>();
    private Set<MoveListener> movelisteners = new HashSet<MoveListener>();
    private static Game mGame = null;
    private Inventory mInventory = new Inventory();
    private GameLevel mLevel = null;
    private Map<Block, Moves> forcedMoves = new HashMap<Block, Moves>();
    private GameState mGameState = NullGameState.getInstance();
    private BlockFactory mBlockFactory = null;
    private LevelFactory mLevelFactory = null;
    private int mLevelNumber = 0;
    private int enemyTick = 0;
    private Timer tickTimer = null;
    private static int mCreatureTicks = 0;
    private static boolean blobMove = false;

    private Game(){}

    public static synchronized Game getInstance() {
        if(mGame == null)
            mGame = new Game();
        return mGame;
    }

    public void clearStuff() {
        mInventory.clear();
        creatures.clear();
        blobs.clear();
        gameListeners.clear();
        if(tickTimer != null)
            tickTimer.cancel();
    }


    public void nextLevel() {
        clearStuff();
        mLevel = mLevelFactory.getLevel(++mLevelNumber);
        start();
    }

    public void nextLevel(int n) {
        clearStuff();
        mLevelNumber = n;
        mLevel = mLevelFactory.getLevel(mLevelNumber);
        start();
    }

    public void restart() {
        nextLevel(mLevelNumber);
    }

    public void levelComplete() {
        GUI.getInstance().scoreDialog(mLevel);
        nextLevel();
    }

    public void start() {
        if(tickTimer != null)
            tickTimer.cancel();
        tickTimer = new Timer();
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
        tickTimer.schedule(tt, 0, TIMER_TICK);
    }

    public void addMoveListener(MoveListener l) {
        movelisteners.add(l);
    }

    public void removeMoveListener(MoveListener l) {
        movelisteners.remove(l);
    }
    
    public void addGameListener(GameListener l) {
        gameListeners.add(l);
    }

    public void removeGameListener(GameListener l) {
        gameListeners.remove(l);
    }

    public void addCreature(Block b) {
        creatures.add(b);
    }

    public void removeCreature(Block b) {
        creatures.remove(b);
    }

    public void addBlob(Block b) {
        blobs.add(b);
    }

    public void removeBlob(Block b) {
        blobs.remove(b);
    }

    public void tick() throws BlockContainerFullException {
        //TODO: Remove the need of making a copy
        Collection<GameListener> listenersCpy= new ArrayList<GameListener>(gameListeners);
        for(GameListener l : listenersCpy) {
            l.tick();
        }
        mCreatureTicks = (mCreatureTicks + 1) % Game.SPEED_FRAC;
        if(mCreatureTicks == 0) {
            Collection<Block> creaturesCpy= new ArrayList<Block>(creatures);
            for(Block l : creaturesCpy) {
                l.tick();
            }
            if(blobMove) {
                Collection<Block> blobsCpy= new ArrayList<Block>(blobs);
                for(Block l : blobsCpy) {
                    l.tick();
                }
            }
            blobMove = !blobMove;
        }
    }

    public void die(String msg) {
        //listeners.clear();
        //tickTimer.cancel();
        //TODO: Play "Bummer"
        GUI.getInstance().msgDialog(msg);
        restart();
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

    public void moveHappened(Point from, Point to) {
        for(MoveListener l : movelisteners) {
            l.moveHappened(from, to);
        }
    }

}
