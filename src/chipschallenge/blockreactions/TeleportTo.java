package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.BlockContainer;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move;
import chipschallenge.SoundPlayer.sounds;
import chipschallenge.Teleports;
import java.awt.Point;

/**
 * Moving to a Block
 */
public class TeleportTo extends BlockReaction {

    private TeleportTo() {}
    private static TeleportTo mInstance = null;

    public static synchronized TeleportTo getInstance() {
        if (mInstance == null) {
            mInstance = new TeleportTo();
        }
        return mInstance;
    }


    public void react(Block moving, Block standing) throws BlockContainerFullException {
        Point origin = level().getPoint(standing);
        Point currentStart = origin;
        BlockContainer goal = null;
        Point remote = null;
        Point moveTo = null;
        do {
            remote = Teleports.next(currentStart);
            moveTo = (Point) remote.clone();
            Move.updatePoint(moveTo, moving.getFacing());
            goal = level().getBlockContainer(moveTo.x, moveTo.y);
            currentStart = remote;
        } while(!(goal.canMoveTo(moving) && currentStart != origin));
        if(remote == origin) {
            // Totally blocked
        } else {
            level().teleport(moving, remote);
            if(moving.isChip())
                sound().playSound(sounds.TELEPORT);
        }
        game().addForcedMove(moving, moving.getFacing());
    }

    public boolean canMove(Block moving, Block standing) {
        return true;
    }
}
