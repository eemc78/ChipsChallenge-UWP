package chipschallenge;

import chipschallenge.Block.Type;
import chipschallenge.Move.Moves;
import chipschallenge.blockreactions.*;
import chipschallenge.buttonbehaviors.ButtonBehavior;
import chipschallenge.buttonbehaviors.CloneMachineBehavior;
import chipschallenge.buttonbehaviors.NullButtonBehavior;
import chipschallenge.buttonbehaviors.ToggleWallBehavior;
import chipschallenge.buttonbehaviors.TrapBehavior;
import chipschallenge.tickbehaviors.BlockTickBehavior;
import chipschallenge.tickbehaviors.ChipTickBehavior;
import chipschallenge.tickbehaviors.NullTickBehavior;
import chipschallenge.tickbehaviors.BlobTickBehavior;
import chipschallenge.tickbehaviors.BugTickBehavior;
import chipschallenge.tickbehaviors.FireballTickBehavior;
import chipschallenge.tickbehaviors.GliderTickBehavior;
import chipschallenge.tickbehaviors.ParameciumTickBehavior;
import chipschallenge.tickbehaviors.PinkballTickBehavior;
import chipschallenge.tickbehaviors.TankBehavior;
import chipschallenge.tickbehaviors.TeethTickBehavior;
import chipschallenge.tickbehaviors.WalkerTickBehavior;

public class MicrosoftBlockFactory extends BlockFactory {

    private MicrosoftBlockFactory() {
    }
    private static MicrosoftBlockFactory mInstance = null;

    public static synchronized MicrosoftBlockFactory getInstance() {
        if (mInstance == null) {
            mInstance = new MicrosoftBlockFactory();
        }
        return mInstance;
    }

    public Block createBlock(Type type, Moves facing) {
        Block ret = null;
        BlockTickBehavior nullTick = NullTickBehavior.getInstance();
        ButtonBehavior nullButton = NullButtonBehavior.getInstance();
        BlockReaction canMove = CanMove.getInstance();
        BlockReaction cannotMove = CannotMove.getInstance();
        switch (type) {
            case BLOB:
                ret = new Block(type, facing, BlobTickBehavior.getInstance(), canMove, CreatureTo.getInstance(), nullButton);
                break;
            case BLOCK:
                ret = new Block(type, facing, nullTick, canMove, BlockTo.getInstance(), nullButton);
                break;
            case BLUEBUTTON:
                ret = new Block(type, facing, nullTick, canMove, BlueButtonTo.getInstance(), nullButton);
                break;
            case BLUEKEY:
                ret = new Block(type, facing, nullTick, canMove, BlueKeyTo.getInstance(), nullButton);
                break;
            case BLUELOCK:
                ret = new Block(type, facing, nullTick, canMove, BlueLockTo.getInstance(), nullButton);
                break;
            case BLUEWALLFAKE:
                ret = new Block(type, facing, nullTick, canMove, BlueWallFakeTo.getInstance(), nullButton);
                break;
            case BLUEWALLREAL:
                ret = new Block(type, facing, nullTick, canMove, BlueWallRealTo.getInstance(), nullButton);
                break;
            case BOMB:
                ret = new Block(type, facing, nullTick, canMove, BombTo.getInstance(), nullButton);
                break;
            case BROWNBUTTON:
                ret = new Block(type, facing, nullTick, BrownButtonFrom.getInstance(), BrownButtonTo.getInstance(), nullButton);
                break;
            case BUG:
                ret = new Block(type, facing, BugTickBehavior.getInstance(), canMove, CreatureTo.getInstance(), nullButton);
                break;
            case BURNEDCHIP:
                break;
            case CHIP:
                ret = new Block(type, facing, ChipTickBehavior.getInstance(), canMove, ChipTo.getInstance(), nullButton);
                Game.getInstance().setChip(ret);
                break;
            case CLONEBLOCK:
                break;
            case CLONEMACHINE:
                ret = new Block(type, facing, nullTick, cannotMove, cannotMove, CloneMachineBehavior.getInstance());
                break;
            case COMPUTERCHIP:
                ret = new Block(type, facing, nullTick, canMove, ComputerChipTo.getInstance(), nullButton);
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
                ret = new Block(type, facing, nullTick, canMove, FireTo.getInstance(), nullButton);
                break;
            case FIREBOOTS:
                ret = new Block(type, facing, nullTick, canMove, FirebootsTo.getInstance(), nullButton);
                break;
            case FIREBALL:
                ret = new Block(type, facing, FireballTickBehavior.getInstance(), canMove, CreatureTo.getInstance(), nullButton);
                break;
            case FLIPPERS:
                ret = new Block(type, facing, nullTick, canMove, FlippersTo.getInstance(), nullButton);
                break;
            case FLOOR:
                ret = new Block();
                ret.setType(Type.FLOOR);
                break;
            case FORCEFLOOR:
                ret = new Block(type, facing, nullTick, canMove, ForceFloorTo.getInstance(), nullButton);
                break;
            case RANDOMFORCEFLOOR:
                ret = new Block(type, facing, nullTick, canMove, RandomForceFloorTo.getInstance(), nullButton);
                break;
            case GLIDER:
                ret = new Block(type, facing, GliderTickBehavior.getInstance(), canMove, CreatureTo.getInstance(), nullButton);
                break;
            case GRAVEL:
                ret = new Block(type, facing, nullTick, canMove, GravelTo.getInstance(), nullButton);
                break;
            case GREENBUTTON:
                ret = new Block(type, facing, nullTick, canMove, GreenButtonTo.getInstance(), nullButton);
                break;
            case GREENKEY:
                ret = new Block(type, facing, nullTick, canMove, GreenKeyTo.getInstance(), nullButton);
                break;
            case GREENLOCK:
                ret = new Block(type, facing, nullTick, canMove, GreenLockTo.getInstance(), nullButton);
                break;
            case HIDDENWALL:
                ret = new Block(type, facing, nullTick, canMove, BlueWallRealTo.getInstance(), nullButton);
                break;
            case HINT:
                ret = new Block(type, facing, nullTick, HintFrom.getInstance(), HintTo.getInstance(), nullButton);
                break;
            case ICE:
                ret = new Block(type, facing, nullTick, IceFrom.getInstance(), IceTo.getInstance(), nullButton);
                break;
            case ICECORNER:
                ret = new Block(type, facing, nullTick, IceFrom.getInstance(), IceCornerTo.getInstance(), nullButton);
                break;
            case ICEBLOCK: // ?
                break;
            case ICESKATES:
                ret = new Block(type, facing, nullTick, canMove, IceskatesTo.getInstance(), nullButton);
                break;
            case INVISIBLEWALL:
                ret = new Block(type, facing, nullTick, canMove, cannotMove, nullButton);
                break;
            case LOCK:
                break;
            case PARAMECIUM:
                ret = new Block(type, facing, ParameciumTickBehavior.getInstance(), canMove, CreatureTo.getInstance(), nullButton);
                break;
            case PINKBALL:
                ret = new Block(type, facing, PinkballTickBehavior.getInstance(), canMove, CreatureTo.getInstance(), nullButton);
                break;
            case RECESSEDWALL:
                ret = new Block(type, facing, nullTick, canMove, RecessedWallTo.getInstance(), nullButton);
                break;
            case REDBUTTON:
                ret = new Block(type, facing, nullTick, canMove, RedButtonTo.getInstance(), nullButton);
                break;
            case REDKEY:
                ret = new Block(type, facing, nullTick, canMove, RedKeyTo.getInstance(), nullButton);
                break;
            case REDLOCK:
                ret = new Block(type, facing, nullTick, canMove, RedLockTo.getInstance(), nullButton);
                break;
            case SOCKET:
                ret = new Block(type, facing, nullTick, canMove, SocketTo.getInstance(), nullButton);
                break;
            case SUCTIONBOOTS:
                ret = new Block(type, facing, nullTick, canMove, SuctionbootsTo.getInstance(), nullButton);
                break;
            case SWIMMINGCHIP:
                break;
            case TANK:
                TankBehavior tb = new TankBehavior();
                BlockTickBehavior btb = tb;
                ButtonBehavior bb = tb;
                ret = new Block(type, facing, tb, canMove, CreatureTo.getInstance(), bb);
                Buttons.addBlueButtonsListener(ret);
                break;
            case TEETH:
                ret = new Block(type, facing, TeethTickBehavior.getInstance(), canMove, CreatureTo.getInstance(), nullButton);
                break;
            case TELEPORT:
                ret = new Block(type, facing, nullTick, canMove, TeleportTo.getInstance(), nullButton);
                break;
            case THIEF:
                ret = new Block(type, facing, nullTick, canMove, ThiefTo.getInstance(), nullButton);
                break;
            case THINWALL:
                ret = new Block(type, facing, nullTick, ThinWallFrom.getInstance(), ThinWallTo.getInstance(), nullButton);
                break;
            case THINWALLSE:
                ret = new Block(type, facing, nullTick, ThinWallSeFrom.getInstance(), ThinWallSeTo.getInstance(), nullButton);
                break;
            case TOGGLEWALLCLOSED: // Yes, fallthrough
            case TOGGLEWALLOPEN:
                ret = new Block(type, facing, nullTick, canMove, ToggleWallTo.getInstance(), ToggleWallBehavior.getInstance());
                Buttons.addGreenButtonsListener(ret);
                break;
            case TRAP:
                ret = new Block(type, facing, nullTick, cannotMove, TrapTo.getInstance(), TrapBehavior.getInstance());
                break;
            case WALKER:
                ret = new Block(type, facing, WalkerTickBehavior.getInstance(), canMove, CreatureTo.getInstance(), nullButton);
                break;
            case WALL:
                ret = new Block(type, facing, nullTick, canMove, cannotMove, nullButton);
                break;
            case WATER:
                ret = new Block(type, facing, nullTick, WaterFrom.getInstance(), WaterTo.getInstance(), nullButton);
                break;
            case YELLOWKEY:
                ret = new Block(type, facing, nullTick, canMove, YellowKeyTo.getInstance(), nullButton);
                break;
            case YELLOWLOCK:
                ret = new Block(type, facing, nullTick, canMove, YellowLockTo.getInstance(), nullButton);
                break;
        }
        if (ret == null) {
            //System.out.println("The block requested hasn't been implemented. Using default behaviors");
            ret = new Block(type, facing);
        }
        return ret;
    }
}
