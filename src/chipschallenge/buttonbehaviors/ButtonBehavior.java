package chipschallenge.buttonbehaviors;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Game;
import chipschallenge.Move.Moves;
/**
 *
 * @author patrik
 */
public abstract class ButtonBehavior {

    //TODO: Refactor to make BlockReaction use same methods
    public final Block createBlock(Type t, Moves d) {
        return Game.getInstance().getBlockFactory().get(t, d);
    }

    public final Block createBlock(Type t) {
        return Game.getInstance().getBlockFactory().get(t);
    }

    public abstract void buttonDown(Block listener, Block button);
    public abstract void buttonUp(Block listener, Block button);
}
