/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.clonebehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.Move;
import chipschallenge.Move.Moves;
import java.awt.Point;

/**
 *
 * @author patrik
 */
public abstract class CloneBehavior {

    protected final Block cloneTo(Block original, Moves direction) throws CloneNotSupportedException {
        Game g = Game.getInstance();
        Block clone = g.getBlockFactory().get(original.getType(), direction);
        Point p = (Point)original.getPoint().clone();
        Move.updatePoint(p, direction);
        try {
            g.getLevel().addBlock(p.x, p.y, clone, 2);
        } catch (BlockContainerFullException ex) {
            throw new CloneNotSupportedException(ex.getMessage());
        }
        return clone;
    }

    public abstract Block cloneIt(Block original) throws CloneNotSupportedException;
}
