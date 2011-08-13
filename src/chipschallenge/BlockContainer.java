package chipschallenge;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author patrik
 */
public class BlockContainer {
    
    private final LinkedList<Block> blocks = new LinkedList<Block>();

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
        Image ret = blocks.peek().getImage(false);
        // Do stuff
        return ret;
    }
    
    public boolean canMoveFrom(Block b) {
        for(Block bl : blocks)
            if(!bl.getFromReaction().canMove(b, bl)) {
                System.out.println(b.getType() + " can't move FROM " + bl.getType());
                return false;
            } else {
                System.out.println(b.getType() + " CAN move FROM " + bl.getType());
            }
        return true;
    }

    public boolean canMoveTo(Block b) {
        for(Block bl : blocks)
            if(!bl.getToReaction().canMove(b, bl)) {
                System.out.println(b.getType() + " can't move TO " + bl.getType());
                return false;
            } else {
                System.out.println(b.getType() + " CAN move TO " + bl.getType());
            }
        return true;
    }

    public void moveFrom(Block b) throws BlockContainerFullException {
            //TODO: Better synchronization solution than copying.
            Collection<Block> blocksCpy = new ArrayList<Block>(blocks);
            for(Block bl : blocksCpy)
                bl.getFromReaction().react(b, bl);
    }

    public void moveTo(Block b) throws BlockContainerFullException {
            //TODO: Better synchronization solution than copying.
            Collection<Block> blocksCpy = new ArrayList<Block>(blocks);
            for(Block bl : blocksCpy)
                bl.getToReaction().react(b, bl);
    }

    void replaceBlock(Block a, Block b) {
        System.out.println("KUK");
        int index = blocks.indexOf(a);       
        blocks.add(index, b);
        a.destroy();
    }

}
