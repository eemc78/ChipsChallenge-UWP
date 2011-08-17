package chipschallenge;

import java.awt.Image;

/**
 *
 * @author patrik
 */
public class BlockContainer {

    private Block upper = null;
    private Block lower = null;
    private Block visitor = null;

    public BlockContainer() {
    }

    // For debugging
    @Override
    public String toString() {
        return "["+upper+","+lower+"]";
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

    public void setVisitor(Block b) {
        visitor = b;
    }

    public Block getVisitor() {
        return visitor;
    }

    public void add(Block b) throws BlockContainerFullException {
        if(visitor == null)
            visitor = b;
        else if(upper == null)
            upper = b;
        else if(lower == null)
            lower = b;
    }

    public void add(Block b, int layer) {
        switch(layer) {
            case 0:
                if(lower == null)
                    lower = b;
                break;
            case 1:
                if(upper == null)
                    upper = b;
                break;
            case 2:
                if(visitor == null)
                    visitor = b;
                break;
        }
    }

    public void remove(Block b) {
        if(visitor == b)
            visitor = null;
        else if(upper == b)
            upper = null;
        else if(lower == b)
            lower = null;
    }

    public Image getImage() {
        if(visitor != null)
            return visitor.getImage(false);
        if(upper != null)
            return upper.getImage(false);
        if(lower != null)
            return lower.getImage(false);
        else { 
            return BlockImageFactory.getInstance().get(Block.Type.FLOOR, Move.Moves.UP, false);
        }
    }

    public boolean canMoveFrom(Block b) {
        if(lower != null) 
            if(!lower.getFromReaction().canMove(b, lower)) 
                return false;
        if(upper != null)
            if(!upper.getFromReaction().canMove(b, upper))
                return false;
        return true;
    }

    public boolean canMoveTo(Block b) {
        if(lower != null)
            if(!lower.getToReaction().canMove(b, lower))
                return false;
        if(upper != null)
            if(!upper.getToReaction().canMove(b, upper))
                return false;
        if(visitor != null)
            if(!visitor.getToReaction().canMove(b, visitor))
                return false;
        return true;
    }

    public void moveFrom(Block b) throws BlockContainerFullException {
        if(upper != null)
            upper.getFromReaction().react(b, upper);
        if(lower != null)
            lower.getFromReaction().react(b, lower);       
    }

    public void moveTo(Block b) throws BlockContainerFullException {
        if(visitor != null)
            visitor.getToReaction().react(b, visitor);
        if(upper != null)
            upper.getToReaction().react(b, upper);
        if(lower != null)
            lower.getToReaction().react(b, lower);        
    }

    public void replaceBlock(Block a, Block b) {
        if(visitor != null && visitor == a)
            visitor = b;
        if(upper != null && upper == a)
            upper = b;
        if(lower != null && lower == a)
            lower = b;
        
        a.destroy();
    }

    public int getLayer(Block b) {
        if(lower == b)
            return 0;
        if(upper == b)
            return 1;
        if(visitor == b)
            return 2;
        return 0;
    }

    public void clear() {
        upper = null;
        lower = null;
        visitor = null;
    }


}
