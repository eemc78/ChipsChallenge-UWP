/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.buttonbehaviors;

import chipschallenge.Block;
import chipschallenge.blockreactions.CanMoveBlockReaction;

/**
 *
 * @author patrik
 */
public class TrapBehavior implements ButtonBehavior {
    public void hit(Block listener, Block button) {
        if(button.getType() == Block.Type.BROWNBUTTON) {
            if(listener.getType() == Block.Type.TRAP) {
                listener.setFromReaction(CanMoveBlockReaction.getInstance());
            }
        }
    }
}
