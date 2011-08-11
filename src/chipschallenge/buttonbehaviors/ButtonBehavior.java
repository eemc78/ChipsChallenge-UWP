/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.buttonbehaviors;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public interface ButtonBehavior {
    public void buttonDown(Block listener, Block button);
    public void buttonUp(Block listener, Block button);
}
