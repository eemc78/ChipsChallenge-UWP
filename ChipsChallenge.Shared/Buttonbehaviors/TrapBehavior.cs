package chipschallenge.buttonbehaviors;

import chipschallenge.Block;
import chipschallenge.blockreactions.CanMove;
import chipschallenge.blockreactions.CannotMove;

public class TrapBehavior implements ButtonBehavior {

    private TrapBehavior() {
    }
    private static TrapBehavior mInstance = null;

    public static synchronized TrapBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new TrapBehavior();
        }
        return mInstance;
    }

    public void buttonDown(Block listener, Block button) {
        if (button.getType() == Block.Type.BROWNBUTTON) {
            listener.setFromReaction(CanMove.getInstance());
            Block trapped = listener.getBlockContainer().getUpper();
            if (trapped != null) {
                trapped.releaseFromTrap();
            }
        }
    }

    public void buttonUp(Block listener, Block button) {
        if (button.getType() == Block.Type.BROWNBUTTON) {
            listener.setFromReaction(CannotMove.getInstance());
        }
    }
}
