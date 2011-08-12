package chipschallenge.blockreactions;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Block;

/**
 *
 * @author patrik
 */
public interface BlockReaction {
    public void react(Block moving, Block standing) throws BlockContainerFullException;
    public boolean canMove(Block moving, Block standing);
}
