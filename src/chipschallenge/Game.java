package chipschallenge;

import chipschallenge.gamestates.GameState;
import chipschallenge.Move.Moves;
import chipschallenge.SoundPlayer.sounds;
import chipschallenge.gamestates.NullGameState;
import chipschallenge.gui.GUI;
import chipschallenge.gui.HintListener;
import chipschallenge.gui.TimeListener;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author patrik
 */
public class Game {

    public static final int MOVE_MS = 250;
    public static final int SPEED_FRAC = 3;
    public static final int TIMER_TICK = MOVE_MS / SPEED_FRAC;
    private CopyOnWriteArrayList<GameListener> gameListeners = new CopyOnWriteArrayList<GameListener>();
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
    private Timer secondsTimer = null;
    private long mTickCount = 0;
    private long mLastTickDrawn = 0;
    private boolean levelComplete;
    private Collection<Point> movesToCheck = new ArrayList<Point>();
    private boolean dead = false;
    private Collection<ChipListener> chipListeners = new ArrayList<ChipListener>();
    private Collection<TimeListener> timeListeners = new CopyOnWriteArrayList<TimeListener>();
    private Collection<NextLevelListener> nextLevelListeners = new ArrayList<NextLevelListener>();
    private static Collection<HintListener> hintListeners = new CopyOnWriteArrayList<HintListener>();

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
        Teleports.clear();
        forcedMoves.clear();
        mInventory.clear();
        gameListeners.clear();
        if (tickTimer != null) {
            tickTimer.cancel();
        }
        if (secondsTimer != null) {
            secondsTimer.cancel();
        }
    }

    public void nextLevel() {
        mLevelNumber++;
        nextLevel(mLevelNumber);
    }

    public void nextLevel(int n) {
        clearStuff();
        mLevelNumber = n;
        mLevel = mLevelFactory.getLevel(mLevelNumber);
        for(NextLevelListener l : nextLevelListeners) {
            l.nextLevel(mLevel);
        }
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
        MusicPlayer.getInstance().playNextSong();
        if (tickTimer != null) {
            tickTimer.cancel();
        }
        tickTimer = new Timer();
        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                try {
                    tick();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        tickTimer.schedule(tt, 0, TIMER_TICK);
        final int seconds = mLevel.getNumSeconds();
        if (seconds > 0) {
            secondsTimer = new Timer();
            TimerTask counter = new TimerTask() {

                int counter = seconds;

                @Override
                public void run() {
                    counter--;
                    for (TimeListener l : timeListeners) {
                        l.timeLeft(counter);
                    }
                    if(counter >= 0 && counter <= 10) {
                        SoundPlayer.getInstance().playSound(sounds.TICK);
                    }
                    if (counter == 0) {
                        SoundPlayer.getInstance().playSound(sounds.TIMEOVER);
                        GUI.getInstance().msgDialog("Ooops! Out of time!");
                        dead = true;
                        secondsTimer.cancel();
                    }
                }
            };
            secondsTimer.schedule(counter, 1000, 1000);
        }
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
        for (GameListener l : gameListeners) {
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
        SoundPlayer.getInstance().playSound(sounds.DIE);
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

    public void addHintListener(HintListener l) {
        hintListeners.add(l);
    }

    public void removeHintListener(HintListener l) {
        hintListeners.remove(l);
    }

    public void showHint() {
        for(HintListener l: hintListeners) {
            l.showHint(mLevel.getHint());
        }
    }

    public void hideHint() {
        for(HintListener l: hintListeners) {
            l.hideHint();
        }
    }

    public void addChipListener(ChipListener l) {
        chipListeners.add(l);
    }

    public void removeChipListener(ChipListener l) {
        chipListeners.remove(l);
    }

    public void addNextLevelListener(NextLevelListener l) {
        nextLevelListeners.add(l);
    }

    public void removeNextLevelListener(NextLevelListener l) {
        nextLevelListeners.remove(l);
    }

    public void addTimeListener(TimeListener l) {
        timeListeners.add(l);
    }

    public void removeTimeListener(TimeListener l) {
        timeListeners.remove(l);
    }

    public void takeChip() {
        mLevel.chipTaken();
        for(ChipListener l : chipListeners) {
            l.chipTaken();
        }
    }

}
