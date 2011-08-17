package chipschallenge;

import java.awt.Image;

/**
 *
 * @author patrik
 */
public class BlockContainer {

    private Block upper = null;
    private Block lower = null;

    public BlockContainer() {
    }

    // For debugging
    @Override
    public String toString() {
        return "["+upper+","+lower+"]";
    }

    public void add(Block b) throws BlockContainerFullException {
        if(lower == null)
            lower = b;
        else if(upper == null)
            upper = b;
        else
            throw new BlockContainerFullException();
    }

    public void remove(Block b) {
        if(upper == b)
            upper = null;
        if(lower == b)
            lower = null;
    }

    public void setUpper(Block b) {
        upper = b;
    }

    public Block getUpper() {
        return upper;
    }

    public void setLower(Block b) {
        lower = b;
    }

    public Block getLower() {
        return lower;
    }

    public Image getImage() {
        if(upper != null)
            return upper.getImage(false);
        if(lower != null)
            return lower.getImage(false);
        else
            return BlockImageFactory.getInstance().get(Block.Type.FLOOR, Move.Moves.UP, false);
    }

    public boolean canMoveFrom(Block b) {
        if(lower != null) {
            if(lower.getFromReaction().canMove(b, lower)) {               
                return false;
            }
        }
        return true;
    }

    public boolean canMoveTo(Block b) {
        if(lower != null)
            if(lower.getToReaction().canMove(b, lower))
                return false;
        if(upper != null)
            if(upper.getToReaction().canMove(b, upper))
                return false;
        return true;
    }

    public void moveFrom(Block b) throws BlockContainerFullException {
        if(lower != null)
            lower.getFromReaction().react(b, lower);
    }

    public void moveTo(Block b) throws BlockContainerFullException {
        if(lower != null)
            lower.getToReaction().react(b, lower);
        if(upper != null)
            upper.getToReaction().react(b, upper);
    }

    public void replaceBlock(Block a, Block b) {
        if(upper != null && upper == a)
            upper = b;
        if(lower != null && lower == a)
            lower = b;
        a.destroy();
    }

    public void clear() {
        upper = null;
        lower = null;
    }

}
