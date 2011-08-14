package chipschallenge;

import java.awt.Image;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author patrik
 */
public class BlockContainer {
    
    private final CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();

    public BlockContainer() {
    }

    // For debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for(Block b : blocks) {
            if(first) {
                first = false;
                sb.append(b.getType());
            } else {
                sb.append(","+b.getType());
            }
        }
        sb.append("]");
        return sb.toString();
    }
  
    public void add(Block b) throws BlockContainerFullException {
        if(blocks.size() >= 4) {
            throw new BlockContainerFullException();
        } else {
            blocks.add(0,b);
        }
    }

    public void remove(Block b) {
        blocks.remove(b);
    }

    public Collection<Block> getBlocks() {
        return blocks;
    }

    public Image getImage() {
        Image ret = blocks.get(0).getImage(false);
        // Do stuff
        return ret;
    }
    
    public boolean canMoveFrom(Block b) {
        for(Block bl : blocks)
            if(!bl.getFromReaction().canMove(b, bl)) {
                return false;
            }
        return true;
    }

    public boolean canMoveTo(Block b) {
        for(Block bl : blocks)
            if(!bl.getToReaction().canMove(b, bl)) {
                return false;
            } 
        return true;
    }

    public void moveFrom(Block b) throws BlockContainerFullException {
            for(Block bl : blocks)
                bl.getFromReaction().react(b, bl);
    }

    public void moveTo(Block b) throws BlockContainerFullException {
            for(Block bl : blocks)
                bl.getToReaction().react(b, bl);
    }

    void replaceBlock(Block a, Block b) {
        int index = blocks.indexOf(a);       
        blocks.add(index, b);
        a.destroy();
    }

}
