package chipschallenge.buttonbehaviors;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainer;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Creatures;
import chipschallenge.Game;
import chipschallenge.GameLevel;
import chipschallenge.Move;
import java.awt.Point;
import java.util.Iterator;

/**
 * If the button was green and the listener is a toggle wall. Toggle it.
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
            Iterator<Block> it = bc.iterator();
            while (it.hasNext()) {
                Block b = it.next();
                if (b.isCreature() || b.isA(Type.BLOCK)) {
                    BlockContainer moveTo = gl.getBlockContainer(b, b.getFacing());
                    if (moveTo.canMoveTo(b)) {
                        try {
                            Block clone = g.getBlockFactory().get(b.getType(), b.getFacing());
                            Point p = b.getPoint();
                            //Move.updatePoint(p, b.getFacing());
                            if(clone.isCreature() && !(clone.isA(Type.TEETH) || clone.isA(Type.BLOB))) {
                                g.addBlockDelay(clone, p, 4);
                            } else {
                                gl.addBlock(p.x, p.y, clone);
                                gl.moveBlock(clone, clone.getFacing(), true);
                                if(clone.isCreature()) {
                                    Creatures.addCreature(clone);
                                }
                            }
                        } catch (BlockContainerFullException ex) {
                            // Ignore for now. TODO: Fix
                        }
                    }
                    break;
                }
            }
        }
    }

    public void buttonUp(Block listener, Block button) {
        // Do nothing
    }
}
