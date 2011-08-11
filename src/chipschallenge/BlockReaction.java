/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

/**
 *
 * @author patrik
 */
interface BlockReaction {
    public void react(Block moving, Block standing);
    public boolean canMove(Block moving, Block standing);
}
