package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Game;

/**
 *
 * @author patrik
 */
public class ChipTo implements BlockReaction {


    public void react(Block moving, Block standing) {
        Game.getInstance().die("Ooops! Look out for creatures!");
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }

}
