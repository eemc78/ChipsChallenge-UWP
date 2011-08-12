package chipschallenge;

import java.awt.Image;
import java.util.Collection;
import java.util.Stack;

/**
 *
 * @author patrik
 */
public class BlockContainer {
    
    private Stack<Block> blocks = new Stack<Block>();

    public BlockContainer() {
    }

    public void push(Block b) throws BlockContainerFullException {
        if(blocks.size() >= 3) {
            throw new BlockContainerFullException();
        } else {
            blocks.push(b);
        }
    }

    public void remove(Block b) {
        blocks.remove(b);
    }

    public Collection<Block> getBlocks() {
        return blocks;
    }

    public Image getImage() {
        Image ret = null;
        // Do stuff
        return ret;
    }

}
