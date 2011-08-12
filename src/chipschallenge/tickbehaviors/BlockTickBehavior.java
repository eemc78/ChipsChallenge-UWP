package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;

/**
 *
 * @author patrik
 */
public interface BlockTickBehavior {

    public void tick(Block caller) throws BlockContainerFullException;

}
