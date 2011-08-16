package chipschallenge.buttonbehaviors;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public interface ButtonBehavior {
    public abstract void buttonDown(Block listener, Block button);
    public abstract void buttonUp(Block listener, Block button);
}
