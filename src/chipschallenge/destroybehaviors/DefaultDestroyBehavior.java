
package chipschallenge.destroybehaviors;

import chipschallenge.Block;
import chipschallenge.Creatures;
import chipschallenge.Game;
import chipschallenge.buttonbehaviors.NullButtonBehavior;
import chipschallenge.tickbehaviors.NullTickBehavior;


public class DefaultDestroyBehavior implements DestroyBehavior {
    private DefaultDestroyBehavior() {
    }
    private static DefaultDestroyBehavior mInstance = null;

    public static synchronized DefaultDestroyBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new DefaultDestroyBehavior();
        }
        return mInstance;
    }

   public void destroy(Block b) {
        clearReactions(b);
        boolean creature = b.isCreature();
        if (creature) {
            Creatures.removeCreature(b);
        }
        if (creature || b.isBlock()) {
            Game.getInstance().removeFromSlipList(b);
        }
        //Game.getInstance().removeMovingBlock(this);
        Game.getInstance().getLevel().removeBlock(b);
    }

    private void clearReactions(Block b) {
        b.setBlockTickBehavior(NullTickBehavior.getInstance());
        b.setButtonBehavior(NullButtonBehavior.getInstance());
    }

}
