package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move;
import chipschallenge.Move.Moves;
import chipschallenge.SoundPlayer.sounds;

/**
 * Move to real blue wall
 */
public class BlueWallRealTo extends NoSlipReaction {

    private BlueWallRealTo() {
    }
    private static BlueWallRealTo mInstance = null;

    public static synchronized BlueWallRealTo getInstance() {
        if (mInstance == null) {
            mInstance = new BlueWallRealTo();
        }
        return mInstance;
    }

    // Converstion to real wall
    public void react(Block moving, Block standing) throws BlockContainerFullException {
        Moves facing = moving.getFacing();
        moving.move(Move.reverse(facing));
        moving.setFacing(facing);
        standing.replace(createBlock(Type.WALL));
        sound().playSound(sounds.CHIPHUM);
    }

    // Only chip can move here
    public boolean canMove(Block moving, Block standing) {
        return moving.isChip();
    }
}
