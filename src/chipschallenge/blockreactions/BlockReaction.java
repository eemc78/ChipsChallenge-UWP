package chipschallenge.blockreactions;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public abstract class BlockReaction {
    
    public final boolean isChip(Block b) {
        return b.getType() == Block.Type.CHIP || b.getType() == Block.Type.SWIMMINGCHIP;
    }

    public final boolean isCreature(Block b) {
        return b.getType() == Block.Type.BLOB ||
               b.getType() == Block.Type.BUG ||
               b.getType() == Block.Type.FIREBALL ||
               b.getType() == Block.Type.GLIDER ||
               b.getType() == Block.Type.PARAMECIUM ||
               b.getType() == Block.Type.PINKBALL ||
               b.getType() == Block.Type.TANK ||
               b.getType() == Block.Type.TEETH ||
               b.getType() == Block.Type.WALKER;
    }

    public abstract void react(Block moving, Block standing) throws BlockContainerFullException;
    public abstract boolean canMove(Block moving, Block standing);
}
