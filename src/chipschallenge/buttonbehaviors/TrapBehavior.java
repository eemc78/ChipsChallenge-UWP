package chipschallenge.buttonbehaviors;

import chipschallenge.Block;
import chipschallenge.blockreactions.CanMoveBlockReaction;
import chipschallenge.blockreactions.CannotMoveBlockReaction;

/**
 *
 * @author patrik
 */
public class TrapBehavior extends ButtonBehavior {

    private TrapBehavior() {}
    private static TrapBehavior mInstance = null;
    public static synchronized TrapBehavior getInstance() {
        if(mInstance == null)
            mInstance = new TrapBehavior();
        return mInstance;
    }

    public void buttonDown(Block listener, Block button) {
        System.out.println("BUTTON DOWN!");
        if(button.getType() == Block.Type.BROWNBUTTON) {
            if(listener.getType() == Block.Type.TRAP) {
                listener.setFromReaction(CanMoveBlockReaction.getInstance());
            }
        }
    }

    public void buttonUp(Block listener, Block button) {
        System.out.println("BUTTON UP!");
        if(button.getType() == Block.Type.BROWNBUTTON) {
            if(listener.getType() == Block.Type.TRAP) {
                listener.setFromReaction(CannotMoveBlockReaction.getInstance());
            }
        }
    }
    
}
