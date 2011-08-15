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
public class BlockTo extends BlockReaction {

    private BlockTo() {
    }
    private static BlockTo mInstance = null;

    public static synchronized BlockTo getInstance() {
        if (mInstance == null) {
            mInstance = new BlockTo();
        }
        return mInstance;
    }

    // When moving onto a block, the block moves in the same direction
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (moving.isChip()) {
            standing.move(moving.getFacing());
        }
    }

    // Chip can move a block if the block in turn can move in the same direction
    public boolean canMove(Block moving, Block standing) {
        if (moving.isChip()) {
            GameLevel level = Game.getInstance().getLevel();
            BlockContainer before = level.getBlockContainer(standing);
            BlockContainer after = level.getBlockContainer(standing, moving.getFacing());
            if (after == null) { // Block being pushed at the edge
                return false;
            }
            return before.canMoveFrom(standing) && after.canMoveTo(standing);
        }
        return false;
    }
}
