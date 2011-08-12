/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

import chipschallenge.Move.Moves;
import chipschallenge.buttonbehaviors.ButtonBehavior;
import chipschallenge.blockreactions.BlockReaction;
import chipschallenge.blockreactions.CanMoveBlockReaction;
import java.awt.Image;

/**
 *
 * @author patrik
 */
public class Block implements GameListener {
    private Type mType;
    private Move.Moves mFacing;
    private BlockTickBehavior mTickBehavior; 
    private BlockReaction mFrom = CanMoveBlockReaction.getInstance();
    private BlockReaction mTo = CanMoveBlockReaction.getInstance();
    private ButtonBehavior mButtonBehavior = new NullButtonBehavior();;

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

    public void move(Moves direction) {}

    public void buttonDown(Block button) {
        mButtonBehavior.buttonDown(this, button);
    }

    public void buttonUp(Block button) {
        mButtonBehavior.buttonUp(this, button);
    }

    public Image getImage() {
        return ImageFactory.get(mType, mFacing);
    }

    public void setFromReaction(BlockReaction from) {
        this.mFrom = from;
    }

    public void setToReaction(BlockReaction to) {
        this.mTo = to;
    }

    @Override
    public Block clone() throws CloneNotSupportedException {
        return (Block) super.clone();
    }
}
