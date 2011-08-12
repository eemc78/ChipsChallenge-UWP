package chipschallenge.buttonbehaviors;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public interface ButtonBehavior {
    public void buttonDown(Block listener, Block button);
    public void buttonUp(Block listener, Block button);
}
