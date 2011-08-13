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
    private int mChipsLeft;
    private int mTimeLeft;

    public GameLevel(int width, int height) {
        mBoard = new BlockContainer[width][height];
        for(int i = 0; i < width; i++)
            for(int j = 0; j < height; j++)
                mBoard[i][j] = new BlockContainer();
    }
    
    public void addBlock(int x, int y, Block b) throws BlockContainerFullException {
        BlockContainer bc = getBlockContainer(x, y);
        if(bc != null) {
            bc.push(b);
            blocks.put(b, new Point(x,y));
        }
    }

    public BlockContainer getBlockContainer(int x, int y) {
        if(x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
            return null;
        return mBoard[x][y];
    }

    public BlockContainer getBlockContainer(Block b) {
        Point p = (Point) blocks.get(b).clone();
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
        System.out.println(b.getType() + " " + direction);
        Point from = blocks.get(b);
        Point to = new Point(from.x, from.y);//(Point) from.clone();
        Move.updatePoint(to, direction);
        if(to.x < 0 || to.x >= getWidth() || to.y < 0 || to.y >= getHeight()) {
            System.out.println("Trying to move outside");
            return false;
        }

        //boolean canMoveFrom = mBoard[from.x][from.y].canMoveFrom(b);
        //boolean canMoveTo = mBoard[to.x][to.y].canMoveTo(b);
        //if(canMoveFrom && canMoveTo) {
        if(mBoard[from.x][from.y].canMoveFrom(b) && mBoard[to.x][to.y].canMoveTo(b)) {
            mBoard[from.x][from.y].moveFrom(b);
            mBoard[from.x][from.y].remove(b);
            mBoard[to.x][to.y].moveTo(b);
            mBoard[to.x][to.y].push(b);
            blocks.put(b, to);
            System.out.println("POINT AFTER SUCCESS:" + blocks.get(b));
            return true;
        } else {
            System.out.println("POINT AFTER FAILURE:" + blocks.get(b));
            System.out.println("Cannot move for other reasons");
            return false;
        }
    }

    public void removeBlock(Block b) {
        getBlockContainer(b).remove(b);
        blocks.remove(b);
    }

    void replaceBlock(Block a, Block b) {
        BlockContainer bc = getBlockContainer(a);
        bc.getBlocks().remove(a);
        a.clearReactions();
        bc.getBlocks().add(b);
        Point p = blocks.get(a);
        blocks.remove(a);
        blocks.put(b, p);
    }
}
