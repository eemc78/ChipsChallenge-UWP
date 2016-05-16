package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;

public interface BlockTickBehavior {

    public void tick(Block caller) throws BlockContainerFullException;
}
