package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.Move.Moves;
import java.awt.Point;

public class TeethTickBehavior implements BlockTickBehavior {

    private TeethTickBehavior() {
    }
    private static TeethTickBehavior mInstance = null;

    public static synchronized TeethTickBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new TeethTickBehavior();
        }
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
        Moves m = caller.getFacing();
        if (!inUse(caller)) {
            return;
        }
        Point chip = findChip();
        Point me = findMe(caller);
        int dx = chip.x - me.x;
        int dy = chip.y - me.y;
        int pdx = Math.abs(dx);
        int pdy = Math.abs(dy);
        Moves xDirection = xDirection = dx > 0 ? Moves.RIGHT : Moves.LEFT;
        Moves yDirection = yDirection = dy > 0 ? Moves.DOWN : Moves.UP;
        if (pdx > pdy) {
            if (caller.canMove(xDirection)) {
                caller.move(xDirection);
            } else if (pdy > 0 && caller.canMove(yDirection)) {
                caller.move(yDirection);
            }
        } else {
            if (caller.canMove(yDirection)) {
                caller.move(yDirection);
            } else if (pdx > 0 && caller.canMove(xDirection)) {
                caller.move(xDirection);
            }
        }

    }
}
