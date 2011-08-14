/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package creaturetickbehavior;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.Move.Moves;
import chipschallenge.tickbehaviors.BlockTickBehavior;
import java.awt.Point;

/**
 *
 * @author patrik
 */
public class TeethTickBehavior implements BlockTickBehavior {


    private TeethTickBehavior() {}
    private static TeethTickBehavior mInstance = null;
    public static synchronized TeethTickBehavior getInstance() {
        if(mInstance == null)
            mInstance = new TeethTickBehavior();
        return mInstance;
    }

    public Point findChip() {
        return Game.getInstance().getLevel().findChip();
    }

    public Point findMe(Block caller) {
        return Game.getInstance().getLevel().getPoint(caller);
    }

    public boolean inUse(Block caller) {
        return Game.getInstance().getLevel().contains(caller);
    }

    @Override
    public void tick(Block caller) throws BlockContainerFullException {
        if(!inUse(caller))
            return;
        Point chip = findChip();
        Point me = findMe(caller);
        int dx = chip.x - me.x;
        int dy = chip.y - me.y;
        Moves xDirection = xDirection = dx > 0 ? Moves.RIGHT : Moves.LEFT;
        Moves yDirection = yDirection = dy > 0 ? Moves.DOWN : Moves.UP;
        if(Math.abs(dx) > Math.abs(dy)) {
            if(!caller.move(xDirection))
                caller.move(yDirection);
        } else if(Math.abs(dy) > Math.abs(dx)) {
            if(!caller.move(yDirection))
                caller.move(xDirection);
        } else {
            switch(caller.getFacing()) {
                case UP:
                    if(dy > 0) {
                        if(!caller.move(xDirection))
                            caller.move(yDirection);
                    } else {
                        if(!caller.move(yDirection))
                            caller.move(xDirection);
                    }
                    break;
                case DOWN:
                    if(dy < 0) {
                        if(!caller.move(xDirection))
                            caller.move(yDirection);
                    } else {
                        if(!caller.move(yDirection))
                            caller.move(xDirection);
                    }
                    break;
                case LEFT:
                    if(dx > 0) {
                        if(!caller.move(yDirection))
                            caller.move(xDirection);
                    } else {
                        if(!caller.move(xDirection))
                            caller.move(yDirection);
                    }
                    break;
                case RIGHT:
                    if(dx < 0) {
                        if(!caller.move(yDirection))
                            caller.move(xDirection);
                    } else {
                        if(!caller.move(xDirection))
                            caller.move(yDirection);
                    }
                    break;
            }
        }
    }

}
