package chipschallenge.blockreactions;

import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public interface BlockReaction {
    public void react(Block moving, Block standing);
    public boolean canMove(Block moving, Block standing);
}
