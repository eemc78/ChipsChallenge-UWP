package chipschallenge.destroybehaviors;

import chipschallenge.Block;

/**
 * Determines how a block will remove itself from the playfield
 */
public interface DestroyBehavior {

    public void destroy(Block b);
}
