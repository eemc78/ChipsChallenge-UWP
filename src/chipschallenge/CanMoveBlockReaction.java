/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

/**
 *
 * @author patrik
 */
public class CanMoveBlockReaction implements BlockReaction {

    public void react(Block moving, Block standing) {
        // No reaction
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

}
