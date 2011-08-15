package chipschallenge;

import chipschallenge.tickbehaviors.BlockTickBehavior;
import chipschallenge.buttonbehaviors.NullButtonBehavior;
import chipschallenge.Move.Moves;
import chipschallenge.buttonbehaviors.ButtonBehavior;
import chipschallenge.blockreactions.BlockReaction;
import chipschallenge.blockreactions.CanMoveBlockReaction;
import chipschallenge.tickbehaviors.NullTickBehavior;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author patrik
 */
public class Block implements GameListener {
    private Type mType;
    private Move.Moves mFacing;
    private BlockTickBehavior mTickBehavior = NullTickBehavior.getInstance();
    private BlockReaction mFrom = CanMoveBlockReaction.getInstance();
    private BlockReaction mTo = CanMoveBlockReaction.getInstance();
    private ButtonBehavior mButtonBehavior = NullButtonBehavior.getInstance();;

    public static enum Type {
        BLOB, BLOCK, BLUEBUTTON, BLUEKEY, BLUELOCK, BLUEWALL, BOMB,
        BROWNBUTTON, BUG, BURNEDCHIP, CHIP, CLONEBLOCK, CLONEMACHINE,
        COMPUTERCHIP, DIRT, DROWNEDCHIP, EXIT, FAKEEXIT, FIRE, FIREBOOTS,
        FIREBALL, FLIPPERS, FLOOR, FORCEFLOOR, RANDOMFORCEFLOOR, GLIDER, GRAVEL, GREENBUTTON,
        GREENKEY, GREENLOCK, HIDDENWALL, HINT, ICE, ICEBLOCK, ICESKATES,
        INVISIBLEWALL, LOCK, PARAMECIUM, PINKBALL, RECESSEDWALL, REDBUTTON,
        REDKEY, REDLOCK, SOCKET, SUCTIONBOOTS, SWIMMINGCHIP, TANK, TEETH,
        TELEPORT, THIEF, THINWALL, TOGGLEWALLCLOSED, TOGGLEWALLOPEN, TRAP, WALKER, WALL, WATER,
        YELLOWKEY, YELLOWLOCK
    }

    public Block() {}

    public Block(Type t, Moves m) {
        mType = t;
        mFacing = m;
    }

    public Block(Type t, Moves m, BlockTickBehavior btb, BlockReaction from, BlockReaction to, ButtonBehavior bb) {
        mType = t;
        mFacing = m;
        mTickBehavior = btb;
        mFrom = from;
        mTo = to;
        mButtonBehavior = bb;
    }


    public Type getType() {
        return mType;
    }

    public boolean isA(Type t) {
        return mType == t;
    }

   private boolean isChip() {
        return mType == Type.CHIP || mType == Type.SWIMMINGCHIP;
    }

    public boolean isCreature() {
        return getType() == Block.Type.BLOB ||
               getType() == Block.Type.BUG ||
               getType() == Block.Type.FIREBALL ||
               getType() == Block.Type.GLIDER ||
               getType() == Block.Type.PARAMECIUM ||
               getType() == Block.Type.PINKBALL ||
               getType() == Block.Type.TANK ||
               getType() == Block.Type.TEETH ||
               getType() == Block.Type.WALKER;
    }

    @Override
    public String toString() {
        return getType().toString();
    }

    public void setType(Type type) {
        mType = type;
    }

    public Move.Moves getFacing() {
        return mFacing;
    }

    public void setFacing(Moves m) {
        mFacing = m;
    }

    public void tick() throws BlockContainerFullException {
        mTickBehavior.tick(this);
    }

    public boolean move(Moves direction) throws BlockContainerFullException {
        //setFacing(direction);
        return Game.getInstance().getLevel().moveBlock(this, direction);
    }

    public void destroy() {
        clearReactions();
        Game.getInstance().removeGameListener(this);
        Game.getInstance().getLevel().removeBlock(this);
    }

    public void clearReactions() {
        mTickBehavior = NullTickBehavior.getInstance();
        mButtonBehavior = NullButtonBehavior.getInstance();
    }

    public void buttonDown(Block button) {
        mButtonBehavior.buttonDown(this, button);
    }

    public void buttonUp(Block button) {
        mButtonBehavior.buttonUp(this, button);
    }

    public Image getImage(boolean overlay) {
        return ImageFactory.getInstance().get(mType, mFacing, overlay);
    }

    public void setFromReaction(BlockReaction from) {
        this.mFrom = from;
    }

    public void setToReaction(BlockReaction to) {
        this.mTo = to;
    }

    public void setBlockTickBehavior(BlockTickBehavior btb) {
        this.mTickBehavior = btb;
    }

    public BlockReaction getFromReaction() {
        return mFrom;
    }

    public BlockReaction getToReaction() {
        return mTo;
    }

    public Point getPoint() {
        return Game.getInstance().getLevel().getPoint(this);
    }

    public void replace(Block b) {
        Game.getInstance().getLevel().replaceBlock(this, b);
    }

    @Override
    public Block clone() throws CloneNotSupportedException {
        if(isCreature() || isChip())
            throw new CloneNotSupportedException();
        return (Block) super.clone();
    }
    
}
