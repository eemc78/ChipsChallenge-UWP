package chipschallenge.blockreactions;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Game;
import chipschallenge.Inventory.Boots;
import chipschallenge.Inventory.Key;
import chipschallenge.Move.Moves;

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

    public final Block createBlock(Type t, Moves d) {
        return Game.getInstance().getBlockFactory().get(t, d);
    }

    public final Block createBlock(Type t) {
        return Game.getInstance().getBlockFactory().get(t);
    }

    public final void takeKey(Key k) {
        Game.getInstance().getInventory().takeKey(k);
    }

    public final boolean hasKey(Key k) {
        return Game.getInstance().getInventory().hasKey(k);
    }

    public final boolean useKey(Key k) {
        return Game.getInstance().getInventory().useKey(k);
    }

    public final void takeBoots(Boots b) {
        Game.getInstance().getInventory().takeBoots(b);
    }

    public final boolean hasBoots(Boots b) {
        return Game.getInstance().getInventory().hasBoots(b);
    }

    public final void die(String msg) {
        Game.getInstance().die(msg);
    }

    public abstract void react(Block moving, Block standing) throws BlockContainerFullException;
    public abstract boolean canMove(Block moving, Block standing);
}
