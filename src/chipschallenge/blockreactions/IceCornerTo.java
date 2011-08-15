package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Inventory.Boots;
import chipschallenge.Move.Moves;

/**
 *
 * @author patrik
 */
public class IceCornerTo extends BlockReaction {

    private IceCornerTo() {
    }
    private static IceCornerTo mInstance = null;

    public static synchronized IceCornerTo getInstance() {
        if (mInstance == null) {
            mInstance = new IceCornerTo();
        }
        return mInstance;
    }

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (!(moving.isChip() && hasBoots(Boots.ICESKATES))) {
            Moves m = standing.getFacing();
            Moves force = null;
            switch (moving.getFacing()) {
                case UP:
                    force = m == Moves.UP ? Moves.RIGHT : Moves.LEFT;
                    break;
                case DOWN:
                    force = m == Moves.DOWN ? Moves.LEFT : Moves.RIGHT;
                    break;
                case LEFT:
                    force = m == Moves.UP ? Moves.DOWN : Moves.UP;
                    break;
                case RIGHT:
                    force = m == Moves.LEFT ? Moves.DOWN : Moves.UP;
                    break;
            }
            game().addForcedMove(moving, force);
        }
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        Moves m = standing.getFacing();
        switch (moving.getFacing()) {
            case UP:
                return m == Moves.UP || m == Moves.LEFT;
            case DOWN:
                return m == Moves.DOWN || m == Moves.RIGHT;
            case LEFT:
                return m == Moves.UP || m == Moves.RIGHT;
            case RIGHT:
                return m == Moves.LEFT || m == Moves.DOWN;
        }
        return false;
    }
}
