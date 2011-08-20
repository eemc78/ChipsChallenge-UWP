package chipschallenge.blockreactions;

import chipschallenge.BlockContainerFullException;
import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Game;
import chipschallenge.GameLevel;
import chipschallenge.Inventory;
import chipschallenge.Inventory.Boots;
import chipschallenge.Inventory.Key;
import chipschallenge.Move.Moves;
import chipschallenge.SoundPlayer;
import chipschallenge.SoundPlayer.sounds;

/**
 * Defines whether a block can move, and what the side effects are
 */
public abstract class BlockReaction {

    public final Block createBlock(Type t, Moves d) {
        return Game.getInstance().getBlockFactory().get(t, d);
    }

    public final Block createBlock(Type t) {
        return Game.getInstance().getBlockFactory().get(t);
    }

    public final void takeKey(Key k) {
        Game.getInstance().getInventory().takeKey(k);
        sound().playSound(sounds.TAKEITEM);
    }

    public final boolean hasKey(Key k) {
        return Game.getInstance().getInventory().hasKey(k);
    }

    public final boolean useKey(Key k) {
        boolean ret = Game.getInstance().getInventory().useKey(k);
        sound().playSound(sounds.DOOR);
        return ret;
    }

    public final void takeBoots(Boots b) {
        Game.getInstance().getInventory().takeBoots(b);
        sound().playSound(sounds.TAKEITEM);
    }

    public final boolean hasBoots(Boots b) {
        return Game.getInstance().getInventory().hasBoots(b);
    }

    public final void die(String msg) {
        Game.getInstance().die(msg, sounds.DIE);
    }

    public final Game game() {
        return Game.getInstance();
    }

    public final SoundPlayer sound() {
        return SoundPlayer.getInstance();
    }

    public final GameLevel level() {
        return Game.getInstance().getLevel();
    }

    public final Inventory inventory() {
        return Game.getInstance().getInventory();
    }

    public abstract void react(Block moving, Block standing) throws BlockContainerFullException;

    public abstract boolean canMove(Block moving, Block standing);

    public abstract boolean causesSlip(Block moving, Block standing);

}
