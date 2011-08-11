/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

import java.awt.Image;

/**
 *
 * @author patrik
 */
public abstract class Block implements GameListener {
    private Type mType;
    private Move.Moves mFacing;
    private BlockTickBehavior mTickBehavior; 
    private ButtonBehavior mBlueButtonBehavior = new NullButtonBehavior();
    private ButtonBehavior mGreenButtonBehavior = new NullButtonBehavior();
    private ButtonBehavior mRedButtonBehavior = new NullButtonBehavior();
    private BlockReaction mFrom = new CanMoveBlockReaction();
    private BlockReaction mTo = new CanMoveBlockReaction();


    public static enum Type {
        BLOB, BLOCK, BLUEBUTTON, BLUEKEY, BLUELOCK, BLUEWALL, BOMB,
        BROWNBUTTON, BUG, BURNEDCHIP, CHIP, CLONEBLOCK, CLONEMACHINE,
        COMPUTERCHIP, DIRT, DROWNEDCHIP, EXIT, FAKEEXIT, FIRE, FIREBOOTS,
        FIREBALL, FLIPPERS, FLOOR, FORCEFLOOR, RANDOMFORCEFLOOR, GLIDER, GRAVEL, GREENBUTTON,
        GREENKEY, GREENLOCK, HIDDENWALL, HINT, ICE, ICEBLOCK, ICESKATES,
        INVISIBLEWALL, LOCK, PARAMECIUM, PINKBALL, RECESSEDWALL, REDBUTTON,
        REDKEY, REDLOCK, SOCKET, SUCTIONBOOTS, SWIMMINGCHIP, TANK, TEETH,
        TELEPORT, THIEF, THINWALL, TOGGLEWALL, TRAP, WALKER, WALL, WATER,
        YELLOWKEY, YELLOWLOCK
    }
    
    public Block(Block.Type type, Move.Moves facing, BlockTickBehavior tickBehavior) {
        mType = type;
        mFacing = facing;
        mTickBehavior = tickBehavior;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type type) {
        mType = type;
    }

    public Move.Moves getFacing() {
        return mFacing;
    }

    @Override
    public void tick() {
        mTickBehavior.tick(this);
    }

    public void BlueButtonHit() {
        mBlueButtonBehavior.hit(this);
    }

    public void GreenButtonHit() {
        mGreenButtonBehavior.hit(this);
    }

    public void RedButtonHit() {
        mRedButtonBehavior.hit(this);
    }

    public Image getImage() {
        return ImageFactory.get(mType, mFacing);
    }

    @Override
    public Block clone() throws CloneNotSupportedException {
        return (Block) super.clone();
    }
}
