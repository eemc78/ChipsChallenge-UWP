package chipschallenge;

import chipschallenge.Move.Moves;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patrik
 */
public class GameLevel {
    
    private volatile BlockContainer[][] mBoard;
    private volatile Map<Block, Point> blocks = new HashMap<Block, Point>();
    private volatile Block chip;
    private int mChipsLeft;
    private int mTimeLeft;
    private int mNumDeaths = 0;
    private int mNumber;

    public GameLevel(int width, int height) {
        mBoard = new BlockContainer[width][height];
        for(int i = 0; i < width; i++)
            for(int j = 0; j < height; j++)
                mBoard[i][j] = new BlockContainer();
    }

    public void addBlock(int x, int y, Block b) throws BlockContainerFullException {
        BlockContainer bc = getBlockContainer(x, y);
        if(bc != null) {
            if(b.getType() == Block.Type.CHIP)
                chip = b;
            bc.add(b);
            blocks.put(b, new Point(x,y));
        }
    }

    public Point getPoint(Block b) {
        Point p = (Point) blocks.get(b).clone();
        return p;
    }

    public BlockContainer getBlockContainer(int x, int y) {
        if(x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
            return null;
        return mBoard[x][y];
    }

    public BlockContainer getBlockContainer(Block b) {
        Point p = getPoint(b);
        if(p == null)
            return null;
        return getBlockContainer(p.x, p.y);
    }

    public BlockContainer getBlockContainer(Block b, Moves direction) {
        Point p = (Point) blocks.get(b).clone();
        if(p == null)
            return null;
        Move.updatePoint(p, direction);
        return getBlockContainer(p.x, p.y);
    }

    public int getNumChipsRequired() {
        return mChipsLeft;
    }

    public int getWidth() {
        return mBoard.length;
    }

    public int getHeight() {
        return mBoard[0].length;
    }

    public boolean moveBlock(Block b, Moves direction) throws BlockContainerFullException {        
        Point from = blocks.get(b);
        Point to = (Point) from.clone();
        Move.updatePoint(to, direction);
        if(to.x < 0 || to.x >= getWidth() || to.y < 0 || to.y >= getHeight()) {
            return false;
        }

        if(mBoard[from.x][from.y].canMoveFrom(b) && mBoard[to.x][to.y].canMoveTo(b)) {
            //From reactions
            mBoard[from.x][from.y].moveFrom(b);

            //Actual movement
            mBoard[from.x][from.y].remove(b);
            blocks.put(b, to);
            mBoard[to.x][to.y].add(b);

            //To reactions
            mBoard[to.x][to.y].moveTo(b);            
            Game.getInstance().moveHappened(from, to);
            return true;
        } else {
            return false;
        }
    }

    public void removeBlock(Block b) {
        getBlockContainer(b).remove(b);
        if(b.getType() != Block.Type.CHIP)
            blocks.remove(b);
    }

    public void replaceBlock(Block a, Block b) {
        Point p = (Point)blocks.get(a).clone();
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
        mNumDeaths++;
    }

    public int getNumDeaths() {
        return mNumDeaths;
    }

    public int getTimeLeft() {
        return mTimeLeft;
    }

    public int getNumber() {
        return mNumber;
    }

    public String getScore() {
        StringBuilder sb = new StringBuilder();
        switch(mNumDeaths) {
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
        double timeBonus = mTimeLeft*10;
        sb.append("Time Bonus: " + timeBonus);
        sb.append("\n\n");
        double levelBonus = Math.floor(mNumber * 500 * Math.pow(0.8, mNumDeaths));
        levelBonus = Math.max(500, levelBonus);
        sb.append("Level Bonus: " + levelBonus);
        sb.append("\n\n");
        double levelScore = timeBonus+levelBonus;
        sb.append("Level Score: " + levelScore);
        //TODO: Store totalscore and best time, and output message.
        return sb.toString();
    }

}
