
package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;


public class SocketTo extends BlockReaction {

    private SocketTo() {}
    private static SocketTo mInstance = null;

    public static synchronized SocketTo getInstance() {
        if (mInstance == null) {
            mInstance = new SocketTo();
        }
        return mInstance;
    }

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        standing.destroy();
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() && Game.getInstance().getLevel().getNumChipsNeeded() <= 0;
    }

}
