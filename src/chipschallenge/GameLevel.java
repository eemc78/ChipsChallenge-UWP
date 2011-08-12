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
        return mBoard[x][y];
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

    public void moveBlock(Block b, Moves direction) {
        Point from = blocks.get(b);
        Point to;
        switch(direction) {
            case UP:
                to = new Point(from.x, from.y-1);
                break;
            case DOWN:
                to = new Point(from.x, from.y+1);
                break;
            case LEFT:
                to = new Point(from.x-1, from.y);
                break;
            case RIGHT:
                to = new Point(from.x+1, from.y);
                break;
        }
    }
}
