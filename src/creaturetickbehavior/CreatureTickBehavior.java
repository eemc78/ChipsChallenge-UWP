/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package creaturetickbehavior;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.tickbehaviors.BlockTickBehavior;

/**
 *
 * @author patrik
 */
public abstract class CreatureTickBehavior implements BlockTickBehavior {

    private int mTicksSinceLast= 0;

    public final void tick(Block caller) throws BlockContainerFullException {
        mTicksSinceLast = (mTicksSinceLast + 1) % Game.SPEED_FRAC;
        if(mTicksSinceLast == 0) {
            creatureTick();
        }
    }

    public abstract void creatureTick() throws BlockContainerFullException;
    
}
