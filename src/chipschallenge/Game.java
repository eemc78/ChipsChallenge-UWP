package chipschallenge;

import chipschallenge.gamestates.GameState;
import chipschallenge.Move.Moves;
import chipschallenge.gamestates.NullGameState;
import chipschallenge.gui.GUI;
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
    public static final int TIMER_TICK = MOVE_MS / SPEED_FRAC;
    private Set<GameListener> gameListeners = new HashSet<GameListener>();
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
    private long mTickCount = 0;
    private long mLastTickDrawn = 0;
    private boolean levelComplete;
    private Collection<Point> movesToCheck = new ArrayList<Point>();
    private boolean dead = false;

    private Game() {
    }

    public static synchronized Game getInstance() {
        if (mGame == null) {
            mGame = new Game();
        }
        return mGame;
    }

    public void clearStuff() {
        mTickCount = 0;
        Buttons.clear();
        Creatures.clear();
        forcedMoves.clear();
        mInventory.clear();
        gameListeners.clear();
        if (tickTimer != null) {
            tickTimer.cancel();
        }
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
        dead = false;
        nextLevel(mLevelNumber);
    }

    public void setLevelComplete() {
        levelComplete = true;
    }

    private void levelComplete() {
        levelComplete = false;
        GUI.getInstance().scoreDialog(mLevel);
        nextLevel();
    }

    public void start() {
        GUI.getInstance().repaint();
        if (tickTimer != null) {
            tickTimer.cancel();
        }
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

    public void addGameListener(GameListener l) {
        gameListeners.add(l);
    }

    public void removeGameListener(GameListener l) {
        gameListeners.remove(l);
    }

    public void addForcedMove(Block b, Moves m) {
        forcedMoves.put(b, m);
    }

    public void removeForcedMove(Block b) {
        forcedMoves.remove(b);
    }

    public void tick() throws BlockContainerFullException {
        mTickCount++;
        Map<Block, Moves> forcedMovesNow = new HashMap<Block, Moves>(forcedMoves);
        forcedMoves.clear();
        for (Block b : forcedMovesNow.keySet()) {
            Moves m = forcedMovesNow.get(b);
            if (!mLevel.moveBlock(b, m, true)) {
                // Bounce
                b.setFacing(Move.reverse(b.getFacing()));
                mLevel.getBlockContainer(b).moveTo(b);
            }
        }
        //TODO: Remove the need of making a copy
        Collection<GameListener> listenersCpy = new ArrayList<GameListener>(gameListeners);
        for (GameListener l : listenersCpy) {
            l.tick();
        }
        Creatures.tick();
        // Check if repaint is necessary
        // TODO: If the moves are many, perhaps repaint right away
        if (mLastTickDrawn != mTickCount) {
            for (Point move : movesToCheck) {
                if (GUI.getInstance().repaintIfNecessary(move)) {
                    break;
                }
            }
        }
        movesToCheck.clear();
        if(dead)
            restart();
        if(levelComplete)
            levelComplete();
    }

    public void die(String msg) {
        //listeners.clear();
        //tickTimer.cancel();
        //TODO: Play "Bummer"
        GUI.getInstance().msgDialog(msg);
        dead = true;
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

    public void moveHappened(Point from) {
        movesToCheck.add(from);
    }

    public void showHint() {
        // TODO
    }

    public void hideHint() {
        // TODO
    }
}
