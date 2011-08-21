/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.clonebehaviors;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public interface CloneBehavior {
    public Block cloneIt(Block original) throws CloneNotSupportedException;
}
