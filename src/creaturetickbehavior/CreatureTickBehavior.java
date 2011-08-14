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

    public abstract void tick(Block caller) throws BlockContainerFullException;
    
}
