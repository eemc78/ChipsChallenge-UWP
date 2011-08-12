/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

import chipschallenge.buttonbehaviors.ButtonBehavior;
import chipschallenge.tickbehaviors.BlockTickBehavior;

/**
 *
 * @author patrik
 */
public class TankBehavior implements BlockTickBehavior, ButtonBehavior {

    private boolean isMoving = false;

    public void tick(Block caller) throws BlockContainerFullException {
        if(isMoving)
            if(!caller.move(caller.getFacing()))
                isMoving = false;
    }

    public void buttonDown(Block listener, Block button) {
        listener.setFacing(Move.reverse(listener.getFacing()));
        isMoving = true;
    }

    public void buttonUp(Block listener, Block button) {
        // Do nothing
    }

}
