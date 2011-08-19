package chipschallenge;

import chipschallenge.Move.Moves;
import chipschallenge.SoundPlayer.sounds;
import chipschallenge.gui.GUI;
import chipschallenge.gui.HintListener;
import chipschallenge.gui.TimeListener;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {

    public static final int TIMER_TICK = 100;
    public static final int SPEED_FRAC = 2;
    private CopyOnWriteArrayList<Block> movingBlocks = new CopyOnWriteArrayList<Block>();
    private static Game mGame = null;
    private Inventory mInventory = new Inventory();
    private GameLevel mLevel = null;
    private Map<Block, Moves> forcedMoves = new HashMap<Block, Moves>();
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
    private volatile boolean dead = false;
    private Collection<ChipListener> chipListeners = new ArrayList<ChipListener>();
    private Collection<TimeListener> timeListeners = new CopyOnWriteArrayList<TimeListener>();
    private Collection<NextLevelListener> nextLevelListeners = new ArrayList<NextLevelListener>();
    private static Collection<HintListener> hintListeners = new CopyOnWriteArrayList<HintListener>();
    private Map<Long, List<Block>> addBlockAtTick = new HashMap<Long, List<Block>>();
    private Map<Block, Point> addBlocks = new HashMap<Block, Point>();

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
        movingBlocks.clear();
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
        for (NextLevelListener l : nextLevelListeners) {
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
        try {
            firstTick();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tickTimer.schedule(tt, TIMER_TICK, TIMER_TICK);
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
                    if (counter >= 0 && counter <= 10) {
                        SoundPlayer.getInstance().playSound(sounds.TICK);
                    }
                    if (counter == 0) {
                        die("Ooops! Out of time!", sounds.TIMEOVER);
                    }
                }
            };
            secondsTimer.schedule(counter, 1000, 1000);
        }
    }

    public void addMovingBlock(Block l) {
        movingBlocks.add(l);
    }

    public void removeMovingBlock(Block l) {
        movingBlocks.remove(l);
    }

    public void addForcedMove(Block b, Moves m) {
        forcedMoves.put(b, m);
    }

    public void removeForcedMove(Block b) {
        forcedMoves.remove(b);
    }

    public void firstTick() throws BlockContainerFullException {
        mTickCount++;
        for (Block b : Creatures.getCreatures()) {
            mLevel.getBlockContainer(b).moveTo(b);
        }
    }

    private void addClonesQueued() throws BlockContainerFullException {
        List<Block> blocksToAdd = addBlockAtTick.get(mTickCount);
        if (blocksToAdd != null) {
            for (Block b : blocksToAdd) {
                Point p = addBlocks.get(b);
                Point dp = (Point) p.clone();
                Move.updatePoint(dp, b.getFacing());
                if (mLevel.getBlockContainer(dp.x, dp.y).canMoveTo(b)) {
                    mLevel.addBlock(p.x, p.y, b, 2);
                    mLevel.moveBlock(b, b.getFacing(), true);
                    if (b.isCreature()) {
                        Creatures.addCreature(b);
                    }                              
                }
                addBlocks.remove(b);
            }
            blocksToAdd.clear();
        }
    }
    
    private void performForced(Map<Block, Moves> forced) throws BlockContainerFullException {
        for (Block b : forced.keySet()) {
            if (b.isChip() || b.isCreature() || b.isBlock()) {
                b.setForced(true);
                Moves m = forced.get(b);
                if (!mLevel.moveBlock(b, m, !b.isOnTrap())) {
                    // Bounce
                    b.setFacing(Move.reverse(b.getFacing()));
                    mLevel.getBlockContainer(b).moveTo(b);
                }
            }
        }
    }

    // TODO: Consider just doing one loop, and copy whatever isn't a trapped block
    private Map<Block, Moves> makeForcedMovesNow() {
        Map<Block, Moves> forcedMovesNow = new HashMap<Block, Moves>(forcedMoves);
        forcedMoves.clear();
        Set<Block> setCopy = new HashSet<Block>(forcedMovesNow.keySet());
        for(Block b : setCopy) {
            if(b.isBlock() && b.isTrapped()) {
                Moves m = forcedMovesNow.get(b);
                forcedMoves.put(b, m);
                forcedMovesNow.remove(b);
            }
        }
        return forcedMovesNow;
    }

    public void checkRepaint() {
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
    }

    public void tick() throws BlockContainerFullException {        
        mTickCount++;
        addClonesQueued();
        Map<Block, Moves> forcedMovesNow = makeForcedMovesNow();
        performForced(forcedMovesNow);
        for (Block b : movingBlocks) 
            b.tick();
        
        Creatures.tick();
        checkRepaint();
        
        
        if (dead) 
            restart();
        if (levelComplete)
            levelComplete();
    }

    public void die(String msg, sounds s) {
        dead = true;
        if (secondsTimer != null) {
            secondsTimer.cancel();
        }
        SoundPlayer.getInstance().playSound(s);
        GUI.getInstance().msgDialog(msg);

    }

    public GameLevel getLevel() {
        return mLevel;
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
        for (HintListener l : hintListeners) {
            l.showHint(mLevel.getHint());
        }
    }

    public void hideHint() {
        for (HintListener l : hintListeners) {
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

    public void addBlockDelay(Block b, Point p, int ticks) {
        long addWhen = mTickCount + ticks;
        List<Block> blocks = addBlockAtTick.get(addWhen);
        if (blocks == null) {
            blocks = new ArrayList<Block>();
            addBlockAtTick.put(addWhen, blocks);
        }
        blocks.add(b);
        addBlocks.put(b, p);
    }

    public void takeChip() {
        mLevel.chipTaken();
        for (ChipListener l : chipListeners) {
            l.chipTaken();
        }
    }
}
