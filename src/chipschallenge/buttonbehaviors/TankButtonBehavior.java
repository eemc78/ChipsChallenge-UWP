/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import chipschallenge.Block;
import chipschallenge.Move;
import chipschallenge.buttonbehaviors.ButtonBehavior;

/**
 *
 * @author patrik
 */
public class TankButtonBehavior implements ButtonBehavior {

    private boolean isMoving = false;

    public void buttonDown(Block listener, Block button) {
        listener.setFacing(Move.reverse(listener.getFacing()));
        isMoving = true;
    }

    public void buttonUp(Block listener, Block button) {
        // Do nothing
    }

}
