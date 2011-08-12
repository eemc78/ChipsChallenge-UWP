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
    
    private BlockContainer[][] mBoard;
    private Map<Block, Point> blocks = new HashMap<Block, Point>();
    private int mNumChipsRequired;

    public GameLevel(int width, int height) {
        mBoard = new BlockContainer[width][height];
    }

    public BlockContainer getBlockContainer(int x, int y) {
        if(x < 0 || x > getWidth() || y < 0 || y > getHeight())
            return null;
        return mBoard[x][y];
    }

    public BlockContainer getBlockContainer(Block b) {
        Point p = blocks.get(b);
        if(p == null)
            return null;
        return getBlockContainer(p.x, p.y);
    }

    public BlockContainer getBlockContainer(Block b, Moves direction) {
        Point p = blocks.get(b);
        if(p == null)
            return null;
        Move.updatePoint(p, direction);
        return getBlockContainer(p.x, p.y);
    }

    public int getNumChipsRequired() {
        return mNumChipsRequired;
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
        if(to.x < 0 || to.x > getWidth() || to.y < 0 || to.y > getHeight()) {
            return false;
        }
        if(mBoard[from.x][from.y].canMoveFrom(b) && mBoard[to.x][to.y].canMoveTo(b)) {
            mBoard[from.x][from.y].moveFrom(b);
            mBoard[from.x][from.y].remove(b);
            mBoard[to.x][to.y].push(b);
            blocks.put(b, to);
            mBoard[to.x][to.y].moveTo(b);
            return true;
        } else {
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
