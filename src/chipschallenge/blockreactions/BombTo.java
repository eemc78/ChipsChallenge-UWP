/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Game;

/**
 *
 * @author patrik
 */
public class BombTo implements BlockReaction {

    public void react(Block moving, Block standing) {
        if(moving.getType() == Block.Type.CHIP) {
            Game.getInstance().die("Ooops! Don't touch the bombs!");
        } else {
            moving.destroy();
            standing.destroy();
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

}
