package chipschallenge;

import chipschallenge.Move.Moves;
import chipschallenge.SoundPlayer.sounds;
import chipschallenge.gui.GUI;
import chipschallenge.gui.HintListener;
import chipschallenge.gui.TimeListener;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends KeyAdapter {

    public static final int TIMER_TICK = 100;
    public static final int SPEED_FRAC = 2;
    private CopyOnWriteArrayList<Block> movingBlocks = new CopyOnWriteArrayList<Block>();
    private static Game mGame = null;
    private Inventory mInventory = new Inventory();
    private GameLevel mLevel = null;
    //private Map<Block, Moves> forcedMoves = new HashMap<Block, Moves>();
    private BlockFactory mBlockFactory = null;
    private LevelFactory mLevelFactory = null;
    private int mLevelNumber = 0;
    private int enemyTick = 0;
    private Timer tickTimer = null;
    private Timer secondsTimer = null;
    private long mTickCount = 0;
    private long mLastTickDrawn = 0;
    private boolean levelComplete;
    private boolean isStarted = false;
    private Collection<Point> movesToCheck = new ArrayList<Point>();
    private volatile boolean dead = false;
    private Collection<ChipListener> chipListeners = new ArrayList<ChipListener>();
    private Collection<TimeListener> timeListeners = new CopyOnWriteArrayList<TimeListener>();
    private Collection<NextLevelListener> nextLevelListeners = new ArrayList<NextLevelListener>();
    private static Collection<HintListener> hintListeners = new CopyOnWriteArrayList<HintListener>();
    private Map<Long, List<Block>> addBlockAtTick = new HashMap<Long, List<Block>>();
    private Map<Block, Point> addBlocks = new HashMap<Block, Point>();
    private final SlipList slipList = new SlipList();
    private BlockMove chipForced = null;
    private Block chip = null;

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
        chipForced = null;
        chip = null;
        Buttons.clear();
        Creatures.clear();
        Teleports.clear();
        slipList.clear();
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
        chip = mLevel.getChip();
        for (NextLevelListener l : nextLevelListeners) {
            l.nextLevel(mLevel);
        }
        GUI.getInstance().repaint();
        MusicPlayer.getInstance().playNextSong();
        isStarted = false;
        //start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!isStarted) {
            start();
        }
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
        isStarted = true;
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

    public void addToSlipList(Block b, Moves m) {
        if (b.isChip()) {
            chipForced = new BlockMove(b, m);
        } else {
            slipList.add(b, m);
        }
    }

    public void removeFromSlipList(Block b) {
        slipList.remove(b);
    }

    private void addClonesQueued() {
        List<Block> blocksToAdd = addBlockAtTick.get(mTickCount);
        if (blocksToAdd != null) {
            for (Block b : blocksToAdd) {
                Point p = addBlocks.get(b);
                try {
                    mLevel.addBlock(p.x, p.y, b, 2);
                    if (b.isCreature()) {
                        Creatures.addCreature(b);
                    }
                } catch (BlockContainerFullException ex) {
                    // Perhaps save cloning for later
                }
                addBlocks.remove(b);
            }
            addBlockAtTick.remove(mTickCount);
        }
    }


    private void forceCreatures() throws BlockContainerFullException {
        for (int i = 0; i < slipList.size(); i++) {
            BlockMove bm = slipList.get(i);
            if (bm.block.isCreature() || bm.block.isBlock()) {
                bm.block.setForced(true);
                forceMove(bm.block, bm.move);
            }
        }
    }

    private void forceMove(Block b, Moves m) throws BlockContainerFullException {
        if (!mLevel.moveBlock(b, m, !b.isOnTrap(), false)) {
            if (!b.isOnTrap()) {
                //System.exit(-1);
                //Bounce
                //removeFromSlipList(b);
                b.setFacing(Move.reverse(m));
                b.setForced(false);
                mLevel.moveBlock(b, null, true, true);
            }
        }
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

    public BlockMove getChipForced() {
        return chipForced;
    }

    public void forceChip() throws BlockContainerFullException {
        if (chipForced != null) {
            BlockMove cm = (BlockMove) chipForced.clone();
            chipForced = null;
            forceMove(cm.block, cm.move);
            mLevel.getChip().setForced(true);
        }
    }

    // Main "loop"
    public void tick() throws BlockContainerFullException {
        mTickCount++;
        Creatures.tick();
        chip.tick();                  
        forceChip();
        forceCreatures();
        checkRepaint();
        addClonesQueued();
        if (dead) {
            restart();
        }
        if (levelComplete) {
            levelComplete();
        }
        //System.exit(-1);
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

    public void moveOccured(Point from) {
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

    public void setChip(Block b) {
        chip = b;
    }
}
