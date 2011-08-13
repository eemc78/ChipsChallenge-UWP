/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package creaturetickbehavior;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.Move.Moves;
import java.awt.Point;

/**
 *
 * @author patrik
 */
public class TeethTickBehavior extends CreatureTickBehavior {

    private Block owner;

    public TeethTickBehavior(Block b) {
        owner = b;
    }

    public Point findChip() {
        return Game.getInstance().getLevel().findChip();
    }

    public Point findMe() {
        return Game.getInstance().getLevel().getPoint(owner);
    }

    public boolean inUse() {
        return Game.getInstance().getLevel().contains(owner);
    }

    @Override
    public void creatureTick() throws BlockContainerFullException {
        if(!inUse())
            return;
        Point chip = findChip();
        Point me = findMe();
        int dx = chip.x - me.x;
        int dy = chip.y - me.y;
        Moves xDirection = xDirection = dx > 0 ? Moves.RIGHT : Moves.LEFT;
        Moves yDirection = yDirection = dy > 0 ? Moves.DOWN : Moves.UP;
        if(Math.abs(dx) > Math.abs(dy)) {
            if(!owner.move(xDirection))
                owner.move(yDirection);
        } else if(Math.abs(dy) > Math.abs(dx)) {
            if(!owner.move(yDirection))
                owner.move(xDirection);
        } else {
            switch(owner.getFacing()) {
                case UP:
                    if(dy > 0) {
                        if(!owner.move(xDirection))
                            owner.move(yDirection);
                    } else {
                        if(!owner.move(yDirection))
                            owner.move(xDirection);
                    }
                    break;
                case DOWN:
                    if(dy < 0) {
                        if(!owner.move(xDirection))
                            owner.move(yDirection);
                    } else {
                        if(!owner.move(yDirection))
                            owner.move(xDirection);
                    }
                    break;
                case LEFT:
                    if(dx > 0) {
                        if(!owner.move(yDirection))
                            owner.move(xDirection);
                    } else {
                        if(!owner.move(xDirection))
                            owner.move(yDirection);
                    }
                    break;
                case RIGHT:
                    if(dx < 0) {
                        if(!owner.move(yDirection))
                            owner.move(xDirection);
                    } else {
                        if(!owner.move(xDirection))
                            owner.move(yDirection);
                    }
                    break;
            }
        }
    }

}
