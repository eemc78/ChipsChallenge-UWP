package chipschallenge;

import chipschallenge.clonebehaviors.NullCloneBehavior;
import chipschallenge.clonebehaviors.CloneBehavior;
import chipschallenge.tickbehaviors.BlockTickBehavior;
import chipschallenge.buttonbehaviors.NullButtonBehavior;
import chipschallenge.Move.Moves;
import chipschallenge.buttonbehaviors.ButtonBehavior;
import chipschallenge.blockreactions.BlockReaction;
import chipschallenge.blockreactions.canMoveNoSlip;
import chipschallenge.tickbehaviors.NullTickBehavior;
import chipschallenge.trapreleasebehaviors.DefaultTrapReleaseBehavior;
import chipschallenge.trapreleasebehaviors.TrapReleaseBehavior;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author patrik
 */
public class Block {

    private Type mType;
    private Move.Moves mFacing;
    private BlockTickBehavior mTickBehavior = NullTickBehavior.getInstance();
    private BlockReaction mFrom = canMoveNoSlip.getInstance();
    private BlockReaction mTo = canMoveNoSlip.getInstance();
    private ButtonBehavior mButtonBehavior = NullButtonBehavior.getInstance();
    private CloneBehavior mCloneBehavior = NullCloneBehavior.getInstance();
    private TrapReleaseBehavior mTrapReleaseBehavior = DefaultTrapReleaseBehavior.getInstance();
    private boolean forced = false;
    private boolean trapped = false;

    public static enum Type {

        BLOB, BLOCK, BLUEBUTTON, BLUEKEY, BLUELOCK, BLUEWALLREAL, BLUEWALLFAKE, BOMB,
        BROWNBUTTON, BUG, BURNEDCHIP, CHIP, CLONEBLOCK, CLONEMACHINE,
        COMPUTERCHIP, DIRT, DROWNEDCHIP, EXIT, FAKEEXIT, FIRE, FIREBOOTS,
        FIREBALL, FLIPPERS, FLOOR, FORCEFLOOR, RANDOMFORCEFLOOR, GLIDER, GRAVEL, GREENBUTTON,
        GREENKEY, GREENLOCK, HIDDENWALL, HINT, ICE, ICECORNER, ICEBLOCK, ICESKATES,
        INVISIBLEWALL, LOCK, PARAMECIUM, PINKBALL, RECESSEDWALL, REDBUTTON,
        REDKEY, REDLOCK, SOCKET, SUCTIONBOOTS, SWIMMINGCHIP, TANK, TEETH,
        TELEPORT, THIEF, THINWALL, THINWALLSE, TOGGLEWALLCLOSED, TOGGLEWALLOPEN, TRAP, WALKER, WALL, WATER,
        YELLOWKEY, YELLOWLOCK
    }

    public Block() {
    }

    public Block(Type t, Moves m) {
        mType = t;
        mFacing = m;
    }

    public Block(Type t,
            Moves m,
            BlockTickBehavior btb,
            BlockReaction from,
            BlockReaction to,
            ButtonBehavior bb,
            CloneBehavior cb,
            TrapReleaseBehavior trb) {
        mType = t;
        mFacing = m;
        mTickBehavior = btb;
        mFrom = from;
        mTo = to;
        mButtonBehavior = bb;
        mCloneBehavior = cb;
        mTrapReleaseBehavior = trb;
    }

    public static Block create(Type t, Moves d) {
        return Game.getInstance().getBlockFactory().get(t, d);
    }

    public static Block create(Type t) {
        return Game.getInstance().getBlockFactory().get(t);
    }

    public Type getType() {
        return mType;
    }

    public boolean isA(Type t) {
        return mType == t;
    }

    public boolean isChip() {
        return mType == Type.CHIP || mType == Type.SWIMMINGCHIP;
    }

    public boolean isChipWithIceSkates() {
        return isChip() && Game.getInstance().getInventory().hasBoots(Inventory.Boots.ICESKATES);
    }

    public boolean isBlock() {
        return mType == Type.BLOCK;
    }

    public boolean isIce() {
        return mType == Type.ICE || mType == Type.ICECORNER;
    }

    public boolean isForceFloor() {
        return mType == Type.FORCEFLOOR || mType == Type.RANDOMFORCEFLOOR;
    }

    public boolean isOnIce() {
        //if (!isChip() && !isBlock() && !isCreature())
        //return false;
        //return Game.getInstance().getLevel().getBlockContainer(this).getLower().isIce();
        BlockContainer bc = Game.getInstance().getLevel().getBlockContainer(this);
        return bc.find(Type.ICE) != null || bc.find(Type.ICECORNER) != null;
    }

    public boolean isOnForceFloor() {
        //if (!isChip() && !isBlock() && !isCreature())
        //return false;
        return Game.getInstance().getLevel().getBlockContainer(this).getLower().isForceFloor();
    }

    public boolean isOn(Type type) {
        return Game.getInstance().getLevel().getBlockContainer(this).find(type) != null;
    }

    public boolean isOnTrap() {
        return Game.getInstance().getLevel().getBlockContainer(this).find(Type.TRAP) != null;
    }

    public boolean isTrapped() {
        return trapped;
    }

    public void setTrapped(boolean t) {
        trapped = t;
    }

    public boolean isOnCloner() {
        return Game.getInstance().getLevel().getBlockContainer(this).getLower().isA(Type.CLONEMACHINE);
    }

    public boolean isCreature() {
        return getType() == Block.Type.BLOB
                || getType() == Block.Type.BUG
                || getType() == Block.Type.FIREBALL
                || getType() == Block.Type.GLIDER
                || getType() == Block.Type.PARAMECIUM
                || getType() == Block.Type.PINKBALL
                || getType() == Block.Type.TANK
                || getType() == Block.Type.TEETH
                || getType() == Block.Type.WALKER;
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

    public boolean canMoveFrom() {
        return Game.getInstance().getLevel().getBlockContainer(this).canMoveFrom(this);
    }

    public boolean canMoveTo(Moves direction) {
        return Game.getInstance().getLevel().getBlockContainer(this, direction).canMoveTo(this);
    }

    public boolean canMove(Moves direction) {
        return canMoveFrom() && canMoveTo(direction);
    }

    public boolean move(Moves direction, boolean ignoreFrom, boolean ignoreTo) throws BlockContainerFullException {
        //setFacing(direction);
        return Game.getInstance().getLevel().moveBlock(this, direction, ignoreFrom, ignoreTo);
    }

    public boolean move(Moves direction) throws BlockContainerFullException {
        return move(direction, false, false);
    }

    public void destroy() {
        clearReactions();
        Creatures.removeCreature(this);
        Game.getInstance().removeFromSlipList(this);
        //Game.getInstance().removeMovingBlock(this);
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
        return BlockImageFactory.getInstance().get(mType, mFacing, overlay);
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

    public BlockContainer getBlockContainer() {
        return Game.getInstance().getLevel().getBlockContainer(this);
    }

    public void replace(Block b) {
        Game.getInstance().getLevel().replaceBlock(this, b);
    }

    @Override
    public Block clone() throws CloneNotSupportedException {
        return mCloneBehavior.cloneIt(this);
    }

    public void setForced(boolean forced) {
        this.forced = forced;
    }

    public boolean isForced() {
        return forced;
    }

    public void releaseFromTrap() {
        mTrapReleaseBehavior.releaseFromTrap(this);
    }
}
