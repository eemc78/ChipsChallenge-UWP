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
    
    public boolean canMoveFrom(Block b) {
        for(Block bl : blocks)
            if(!bl.getFromReaction().canMove(b, bl))
                return false;
        return true;
    }

    public boolean canMoveTo(Block b) {
        for(Block bl : blocks)
            if(!bl.getToReaction().canMove(b, bl))
                return false;
        return true;
    }

    public void moveFrom(Block b) {
        for(Block bl : blocks)
            bl.getFromReaction().react(b, bl);
    }

    public void moveTo(Block b) {
        for(Block bl : blocks)
            bl.getToReaction().react(b, bl);
    }

}
