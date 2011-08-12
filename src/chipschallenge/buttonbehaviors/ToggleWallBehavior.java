package chipschallenge.buttonbehaviors;

import chipschallenge.Block;

/**
 * If the button was green and the listener is a toggle wall. Toggle it.
 */
public class ToggleWallBehavior implements ButtonBehavior {

    public void buttonDown(Block listener, Block button) {
        if(button.getType() == Block.Type.GREENBUTTON) {
            if(listener.getType() == Block.Type.TOGGLEWALLOPEN) {
                listener.setType(Block.Type.TOGGLEWALLCLOSED);
            } else if(listener.getType() == Block.Type.TOGGLEWALLCLOSED) {
                listener.setType(Block.Type.TOGGLEWALLOPEN);
            }
        }
    }

    public void buttonUp(Block listener, Block button) {
        // Do nothing
    }
    
}
