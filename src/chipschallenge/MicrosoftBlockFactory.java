/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

import chipschallenge.Block.Type;
import chipschallenge.Move.Moves;
import chipschallenge.blockreactions.*;
import chipschallenge.buttonbehaviors.ButtonBehavior;
import chipschallenge.buttonbehaviors.NullButtonBehavior;
import chipschallenge.tickbehaviors.BlockTickBehavior;
import chipschallenge.tickbehaviors.ChipTickBehavior;
import chipschallenge.tickbehaviors.NullTickBehavior;
import creaturetickbehavior.TeethTickBehavior;

/**
 *
 * @author patrik
 */
public class MicrosoftBlockFactory extends BlockFactory {

    private MicrosoftBlockFactory(){}
    private static MicrosoftBlockFactory mInstance = null;
    public static synchronized MicrosoftBlockFactory getInstance() {
        if(mInstance == null)
            mInstance = new MicrosoftBlockFactory();
        return mInstance;
    }

    public Block createBlock(Type type, Moves facing) {
        Block ret = null;
        BlockTickBehavior nullTick = NullTickBehavior.getInstance();
        ButtonBehavior nullButton = NullButtonBehavior.getInstance();
        BlockReaction canMove = CanMoveBlockReaction.getInstance();
        BlockReaction CannotMove = CannotMoveBlockReaction.getInstance();
        switch(type) {
            case BLOB:
                break;
            case BLOCK:
                ret = new Block(type, facing, nullTick, canMove, BlockTo.getInstance(), nullButton);
                break;
            case BLUEBUTTON:
                break;
            case BLUEKEY:
                break;
            case BLUELOCK:
                break;
            case BLUEWALL:
                break;
            case BOMB:
                break;
            case BROWNBUTTON:
                break;
            case BUG:
                break;
            case BURNEDCHIP:
                break;
            case CHIP:
                ret = new Block(type, facing, ChipTickBehavior.getInstance(), canMove, ChipTo.getInstance(), nullButton);
                Game.getInstance().addGameListener(ret);
                break;
            case CLONEBLOCK:
                break;
            case CLONEMACHINE:
                break;
            case COMPUTERCHIP:
                break;
            case DIRT:
                ret = new Block(type, facing, nullTick, canMove, DirtTo.getInstance(), nullButton);
                break;
            case DROWNEDCHIP:
                break;
            case EXIT:
                ret = new Block(type, facing, nullTick, canMove, ExitTo.getInstance(), nullButton);
                break;
            case FAKEEXIT:
                break;
            case FIRE:
                break;
            case FIREBOOTS:
                break;
            case FIREBALL:
                break;
            case FLIPPERS:
                ret = new Block(type, facing, nullTick, canMove, FlippersTo.getInstance(), nullButton);
                break;
            case FLOOR:
                ret = new Block();
                ret.setType(Type.FLOOR);
                break;
            case FORCEFLOOR:
                break;
            case RANDOMFORCEFLOOR:
                break;
            case GLIDER:
                break;
            case GRAVEL:
                break;
            case GREENBUTTON:
                break;
            case GREENKEY:
                break;
            case GREENLOCK:
                break;
            case HIDDENWALL:
                break;
            case HINT:
                break;
            case ICE:
                break;
            case ICEBLOCK:
                break;
            case ICESKATES:
                break;
            case INVISIBLEWALL:
                break;
            case LOCK:
                break;
            case PARAMECIUM:
                break;
            case PINKBALL:
                break;
            case RECESSEDWALL:
                break;
            case REDBUTTON:
                break;
            case REDKEY:
                break;
            case REDLOCK:
                break;
            case SOCKET:
                break;
            case SUCTIONBOOTS:
                break;
            case SWIMMINGCHIP:
                break;
            case TANK:
                break;
            case TEETH:
                ret = new Block(type, facing, nullTick, canMove, CreatureTo.getInstance(), nullButton);
                ret.setBlockTickBehavior(new TeethTickBehavior(ret));
                Game.getInstance().addGameListener(ret);
                break;
            case TELEPORT:
                break;
            case THIEF:
                break;
            case THINWALL:
                break;
            case TOGGLEWALLCLOSED:
                break;
            case TOGGLEWALLOPEN:
                break;
            case TRAP:
                break;
            case WALKER:
                break;
            case WALL:
                break;
            case WATER:
                ret = new Block(type, facing, nullTick, canMove, WaterTo.getInstance(), nullButton);
                break;
            case YELLOWKEY:
                break;
            case YELLOWLOCK:
                break;
        }
        if(ret == null) {
            System.out.println("The block requested hasn't been implemented. Using default behaviors");
            ret = new Block(type, facing);
        }
        return ret;
    }
}
