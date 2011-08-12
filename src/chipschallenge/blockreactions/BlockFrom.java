/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainer;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.GameLevel;

/**
 *
 * @author patrik
 */
public class BlockFrom implements BlockReaction {

    // When moving onto a block, the block moves in the same direction
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        standing.move(moving.getFacing());
    }

    // Chip can move a block if the block in turn can move in the same direction
    public boolean canMove(Block moving, Block standing) {
        if(moving.getType() == Block.Type.CHIP) {
            GameLevel level = Game.getInstance().getLevel();
            BlockContainer before = level.getBlockContainer(standing);
            BlockContainer after = level.getBlockContainer(moving, moving.getFacing());
            return before.canMoveFrom(standing) && after.canMoveTo(standing);
        }
        return false;
    }

}
