package chipschallenge;

import java.awt.Image;

/**
 *
 * @author patrik
 */
public class BlockContainer {

    private Block upper = null;
    private Block lower = null;
    private Block visitorLow = null;
    private Block visitorHigh = null;

    public BlockContainer() {}

    public Block getLower() {
        return lower;
    }

    public void setLower(Block lower) {
        this.lower = lower;
    }

    public Block getUpper() {
        return upper;
    }

    public void setUpper(Block upper) {
        this.upper = upper;
    }

    public Block getVisitorHigh() {
        return visitorHigh;
    }

    public void setVisitorHigh(Block visitorHigh) {
        this.visitorHigh = visitorHigh;
    }

    public Block getVisitorLow() {
        return visitorLow;
    }

    public void setVisitorLow(Block visitorLow) {
        this.visitorLow = visitorLow;
    }

    // For debugging
    @Override
    public String toString() {
        return "["+upper+","+lower+","+visitorLow+","+visitorHigh+"]";
    }

    public Block find(Block.Type type) {
        if(lower != null && lower.isA(type))
            return lower;
        if(upper != null && upper.isA(type))
            return upper;
        if(visitorLow != null && visitorLow.isA(type))
            return visitorLow;
        if(visitorHigh != null && visitorHigh.isA(type))
            return visitorHigh;
        return null;
    }

    public void add(Block b) throws BlockContainerFullException {
        if(upper == null)
            upper = b;
        else if(lower == null)
            lower = b;
        else if(visitorLow == null)
            visitorLow = b;
        else if(visitorHigh == null)
            visitorHigh = b;
    }

    public void remove(Block b) {
        if(visitorHigh == b)
            visitorHigh = null;
        else if(visitorLow == b)
            visitorLow = null;
        else if(upper == b)
            upper = null;
        else if(lower == b)
            lower = null;
    }

    public boolean standsOnMoreThanFloor(Block b) {
        if((b.isChip() || b.isCreature()) &&
          !(b.isA(Block.Type.SWIMMINGCHIP) || b.isA(Block.Type.BURNEDCHIP))) {
            if(b == visitorHigh) {
                if(visitorLow != null && !visitorLow.isA(Block.Type.FLOOR))
                    return true;
                if(upper != null && !upper.isA(Block.Type.FLOOR))
                    return true;
                if(lower != null && !lower.isA(Block.Type.FLOOR))
                    return true;
            } else if(b == visitorLow) {
                if(upper != null && !upper.isA(Block.Type.FLOOR))
                    return true;
                if(lower != null && !lower.isA(Block.Type.FLOOR))
                    return true;
            } else if(b == upper) {
                if(lower != null && !lower.isA(Block.Type.FLOOR))
                    return true;
            } 
        }
        return false;
    }

    public Image getImage() {
        return getImage(false, 3);
    }
    
    public Image getImage(boolean overlay, int layer) {
        BlockImageFactory bif = BlockImageFactory.getInstance();
        switch(layer) {
            case 3:
                if(visitorHigh == null)
                    return getImage(overlay, 2);
                if(standsOnMoreThanFloor(visitorHigh)) {
                    Image over = visitorHigh.getImage(true);
                    return bif.getOverlayed(over, getImage(true, 2));
                } else {
                    return visitorHigh.getImage(false);
                }
            case 2:
                if(visitorLow == null)
                    return getImage(overlay, 1);
                if(standsOnMoreThanFloor(visitorLow)) {
                    Image over = visitorLow.getImage(true);
                    return bif.getOverlayed(over, getImage(true, 1));
                } else {
                    return visitorLow.getImage(false);
                }
            case 1:
                if(upper == null)
                    return getImage(overlay, 0);
                if(standsOnMoreThanFloor(upper)) {
                    Image over = upper.getImage(true);
                    return bif.getOverlayed(over, getImage(true, 0));
                } else {
                    return upper.getImage(false);
                }
            case 0:
                if(lower != null) {
                    if(overlay)
                        return null;
                    else
                        return lower.getImage(false);
                }
        }
        return bif.get(Block.Type.FLOOR, Move.Moves.UP, false);
    }

    public boolean canMoveFrom(Block b) {
        if(lower != null) 
            if(!lower.getFromReaction().canMove(b, lower)) 
                return false;
        if(upper != null)
            if(!upper.getFromReaction().canMove(b, upper))
                return false;
        if(visitorLow != null)
            if(!visitorLow.getFromReaction().canMove(b, visitorLow))
                return false;
        if(visitorHigh != null)
            if(!visitorHigh.getFromReaction().canMove(b, visitorHigh))
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
        if(visitorLow != null)
            if(!visitorLow.getToReaction().canMove(b, visitorLow))
                return false;
        if(visitorHigh != null)
            if(!visitorHigh.getToReaction().canMove(b, visitorHigh))
                return false;
        return true;
    }

    public void moveFrom(Block b) throws BlockContainerFullException {
        if(visitorHigh != null)
            visitorHigh.getFromReaction().react(b, visitorHigh);
        if(visitorLow != null)
            visitorLow.getFromReaction().react(b, visitorLow);
        if(upper != null)
            upper.getFromReaction().react(b, upper);
        if(lower != null)
            lower.getFromReaction().react(b, lower);       
    }

    public void moveTo(Block b) throws BlockContainerFullException {
        if(visitorHigh != null)
            visitorHigh.getToReaction().react(b, visitorHigh);
        if(visitorLow != null)
            visitorLow.getToReaction().react(b, visitorLow);
        if(upper != null)
            upper.getToReaction().react(b, upper);
        if(lower != null)
            lower.getToReaction().react(b, lower);        
    }

    public void replaceBlock(Block a, Block b) {
        if(visitorHigh != null && visitorHigh == a)
            visitorHigh = b;
        if(visitorLow != null && visitorLow == a)
            visitorLow = b;
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
        if(visitorLow == b)
            return 2;
        if(visitorHigh == b)
            return 3;
        return 0;
    }

    public void clear() {
        upper = null;
        lower = null;
        visitorLow = null;
        visitorHigh = null;
    }


}
