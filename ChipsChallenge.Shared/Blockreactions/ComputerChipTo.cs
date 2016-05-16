package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;
import chipschallenge.SoundPlayer.sounds;

public class ComputerChipTo extends NoSlipReaction {

    private ComputerChipTo() {
    }
    private static ComputerChipTo mInstance = null;

    public static synchronized ComputerChipTo getInstance() {
        if (mInstance == null) {
            mInstance = new ComputerChipTo();
        }
        return mInstance;
    }

    @Override
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        if (moving.isChip()) {
            game().takeChip();
            standing.replace(createBlock(Type.FLOOR));
            sound().playSound(sounds.TAKECHIP);
        }
    }

    @Override
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
