package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.SoundPlayer.sounds;

public class SocketTo extends NoSlipReaction {

    private SocketTo() {
    }
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
        sound().playSound(sounds.SOCKET);
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip() && level().getNumChipsNeeded() <= 0;
    }
}
