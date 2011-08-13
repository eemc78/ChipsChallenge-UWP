/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public class BombTo extends BlockReaction {

    public void react(Block moving, Block standing) {
        moving.destroy();
        standing.destroy();
        if(isChip(moving)) {
            die("Ooops! Don't touch the bombs!");
        } else {
             //TODO: Play explosion sound
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

}
