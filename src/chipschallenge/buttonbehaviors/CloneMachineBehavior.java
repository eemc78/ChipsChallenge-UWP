package chipschallenge.buttonbehaviors;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainer;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Creatures;
import chipschallenge.Game;
import chipschallenge.GameLevel;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * If the button was red, clone what's on top of the listener
 */
public class CloneMachineBehavior implements ButtonBehavior {

    private CloneMachineBehavior() {
    }
    private static CloneMachineBehavior mInstance = null;

    public static synchronized CloneMachineBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new CloneMachineBehavior();
        }
        return mInstance;
    }

    public void buttonDown(Block listener, Block button) {
        if (button.isA(Type.REDBUTTON)) {
            Game g = Game.getInstance();
            GameLevel gl = g.getLevel();
            BlockContainer bc = gl.getBlockContainer(listener);
            // Take the first creature and clone it
            Block b = bc.getUpper();

                /*
                if (b.isCreature() || b.isA(Type.BLOCK)) {
                BlockContainer moveTo = gl.getBlockContainer(b, b.getFacing());
                if (moveTo.canMoveTo(b)) {
                try {
                Block clone = g.getBlockFactory().get(b.getType(), b.getFacing());
                Point p = b.getPoint();
                //Move.updatePoint(p, b.getFacing());
                if (clone.isCreature() && !(clone.isA(Type.TEETH) || clone.isA(Type.BLOB) || clone.isA(Type.FIREBALL))) {
                g.addBlockDelay(clone, p, 3);
                } else {
                gl.addBlock(p.x, p.y, clone, 2);
                gl.moveBlock(clone, clone.getFacing(), true, false);
                if (clone.isCreature()) {
                Creatures.addCreature(clone);
                }
                }
                } catch (BlockContainerFullException ex) {
                // Ignore for now. TODO: Fix
                }
                }
                }
                 */
            try {
                b.clone();
            } catch (CloneNotSupportedException ex) {
                System.out.println("Couldn't clone " + b);
                // Ignore
            }
        }
    }

    public void buttonUp(Block listener, Block button) {
        // Do nothing
    }
}
