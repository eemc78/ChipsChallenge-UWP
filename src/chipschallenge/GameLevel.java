package chipschallenge;

import chipschallenge.Move.Moves;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author patrik
 */
public class GameLevel implements ChipListener {

    private volatile BlockContainer[][] mBoard;
    private volatile Map<Block, Point> blocks = new HashMap<Block, Point>();
    private volatile Block chip;
    private int numChipsNeeded;
    private int numSeconds;
    private int levelNumber;
    private int deaths = 0;
    private String mapTitle = "Untitled";
    private String hint = "No hint";

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public GameLevel(int width, int height) {
        mBoard = new BlockContainer[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mBoard[i][j] = new BlockContainer();
            }
        }
    }

    public GameLevel(int width, int height, int numChipsNeeded, int numSeconds, int levelNumber) {
        this(width, height);
        this.numChipsNeeded = numChipsNeeded;
        this.numSeconds = numSeconds;
        this.levelNumber = levelNumber;       
    }

    public void addBlock(int x, int y, Block b, int layer) throws BlockContainerFullException {
        BlockContainer bc = getBlockContainer(x, y);
        if (bc != null) {
            if(b.isChip()) {
                chip = b;
            }
            if(b.isA(Block.Type.TELEPORT)) {
                Teleports.addTeleport(x, y);
            }
            switch(layer) {
                case 0:
                    bc.setLower(b);
                    break;
                case 1:
                    bc.setUpper(b);
                    break;
                default:
                    bc.add(b);
                    break;
            }
            blocks.put(b, new Point(x, y));
        }
    }

    public Point getPoint(Block b) {
        Point p = (Point) blocks.get(b).clone();
        return p;
    }

    public BlockContainer getBlockContainer(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            return null;
        }
        return mBoard[x][y];
    }

    public BlockContainer getBlockContainer(Block b) {
        Point p = getPoint(b);
        if (p == null) {
            return null;
        }
        return getBlockContainer(p.x, p.y);
    }

    public BlockContainer getBlockContainer(Block b, Moves direction) {
        Point p = (Point) blocks.get(b).clone();
        if (p == null) {
            return null;
        }
        Move.updatePoint(p, direction);
        return getBlockContainer(p.x, p.y);
    }

    public int getWidth() {
        return mBoard.length;
    }

    public int getHeight() {
        return mBoard[0].length;
    }

    // Should be used for true teleportation ONLY
    public boolean teleport(Block b, Point to) throws BlockContainerFullException {
        Point from = blocks.get(b);
        if(mBoard[to.x][to.y].canMoveTo(b)) {
            mBoard[from.x][from.y].remove(b);
            blocks.put(b, to);
            mBoard[to.x][to.y].add(b);
            return true;
        }
        return false;
    }

    public boolean moveBlock(Block b, Moves direction, boolean ignoreFrom) throws BlockContainerFullException {
        Moves facingBefore = b.getFacing();
        Point from = blocks.get(b);
        Point to = (Point) from.clone();
        Move.updatePoint(to, direction);
        // Redraw even if move is impossible, because facing might have changed
        Game.getInstance().moveHappened(from);
        Game.getInstance().moveHappened(to);
        if (ignoreFrom || (!b.isOnIce() || b.isChipWithIceSkates()) && !b.isOnCloner()) {
            b.setFacing(direction);
        }
        if (to.x < 0 || to.x >= getWidth() || to.y < 0 || to.y >= getHeight()) {
            return false;
        }
        if (ignoreFrom || mBoard[from.x][from.y].canMoveFrom(b)) {
            // Do not change facing if sliding            
            if (mBoard[to.x][to.y].canMoveTo(b)) {
               
                //From reactions
                mBoard[from.x][from.y].moveFrom(b);
                              
                //Actual movement
                mBoard[from.x][from.y].remove(b);
                blocks.put(b, to);
                mBoard[to.x][to.y].push(b);

                //To reactions
                mBoard[to.x][to.y].moveTo(b);
                                                                                    
                return true;
            } else {
                return false;
            }
        } else {
            if(!b.isChip()) {
                b.setFacing(facingBefore);
            }
            return false;
        }

    }

    public void removeBlock(Block b) {
        getBlockContainer(b).remove(b);
        if (b.getType() != Block.Type.CHIP) {
            blocks.remove(b);
        }
    }

    public void replaceBlock(Block a, Block b) {
        Point p = (Point) blocks.get(a).clone();
        getBlockContainer(p.x, p.y).replaceBlock(a, b);
        blocks.put(b, p);
    }

    public Point findChip() {
        return blocks.get(chip);
    }

    public boolean contains(Block b) {
        return blocks.containsKey(b);
    }

    public void die() {
        deaths++;
    }

    public int getNumDeaths() {
        return deaths;
    }

    public String getScore() {
        StringBuilder sb = new StringBuilder();
        switch (deaths) {
            case 0:
                sb.append("Yowser! First try!");
                break;
            case 1:
            case 2:
                sb.append("Go Bit Buster!");
                break;
            case 3:
            case 4:
                sb.append("Finnished! Good Work!");
                break;
            default:
                sb.append("At last! You did it!");
                break;
        }
        sb.append("\n\n");
        double timeBonus = numSeconds * 10;
        sb.append("Time Bonus: " + timeBonus);
        sb.append("\n\n");
        double levelBonus = Math.floor(levelNumber * 500 * Math.pow(0.8, deaths));
        levelBonus = Math.max(500, levelBonus);
        sb.append("Level Bonus: " + levelBonus);
        sb.append("\n\n");
        double levelScore = timeBonus + levelBonus;
        sb.append("Level Score: " + levelScore);
        //TODO: Store totalscore and best time, and output message.
        return sb.toString();
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getNumChipsNeeded() {
        return numChipsNeeded;
    }

    public int getNumSeconds() {
        return numSeconds;
    }

    public void setChipsLeft(int chipsLeft) {
        numChipsNeeded = chipsLeft;
    }

    public void chipTaken() {
        numChipsNeeded = numChipsNeeded > 0 ? numChipsNeeded-1 : 0;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void setNumChipsNeeded(int numChipsNeeded) {
        this.numChipsNeeded = numChipsNeeded;
    }

    public void setNumSeconds(int numSeconds) {
        this.numSeconds = numSeconds;
    }

    public void setMapTitle(String title) {
        mapTitle = title;
    }

    public String getMapTitle() {
        return mapTitle;
    }
}
