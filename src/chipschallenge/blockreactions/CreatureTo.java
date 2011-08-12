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
public class CreatureTo implements BlockReaction {

    public void react(Block moving, Block standing) {
        Game.getInstance().die("Ooops! Look out for creatures!");
    }

    public boolean canMove(Block moving, Block standing) {
        return moving.getType() == Block.Type.CHIP;
    }

}
