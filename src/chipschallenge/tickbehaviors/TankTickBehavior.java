/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.tickbehaviors.BlockTickBehavior;

/**
 *
 * @author patrik
 */
public class TankTickBehavior implements BlockTickBehavior {

    private boolean isMoving = false;

    public void tick(Block caller) throws BlockContainerFullException {
        if(isMoving)
            if(!caller.move(caller.getFacing()))
                isMoving = false;
    }

}
