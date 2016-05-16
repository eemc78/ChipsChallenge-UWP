namespace ChipsChallenge.Shared
{
    using Type = Block.Type;
    using Moves = Move.Moves;
    using Blockreactions;

    using Buttonbehaviors;

    using ChipsChallenge.Shared.Tickbehaviors;
    using ChipsChallenge.Shared.Trapreleasebehaviors;

    using Destroybehaviors;

    using CloneMachineBehavior = Buttonbehaviors.CloneMachineBehavior;
    using NullButtonBehavior = Buttonbehaviors.NullButtonBehavior;
    using ToggleWallBehavior = Buttonbehaviors.ToggleWallBehavior;
    using TrapBehavior = Buttonbehaviors.TrapBehavior;
    using BlobCloneBehavior = Clonebehaviors.BlobCloneBehavior;
    using CloneBehavior = Clonebehaviors.CloneBehavior;
    using CloneBoss = Clonebehaviors.CloneBoss;
    using CloneSameDirection = Clonebehaviors.CloneSameDirection;
    using NullCloneBehavior = Clonebehaviors.NullCloneBehavior;
    using DefaultDestroyBehavior = Destroybehaviors.DefaultDestroyBehavior;
    using ChipTickBehavior = Tickbehaviors.ChipTickBehavior;
    using NullTickBehavior = Tickbehaviors.NullTickBehavior;
    using BlobTickBehavior = Tickbehaviors.BlobTickBehavior;
    using BugTickBehavior = Tickbehaviors.BugTickBehavior;
    using FireballTickBehavior = Tickbehaviors.FireballTickBehavior;
    using GliderTickBehavior = Tickbehaviors.GliderTickBehavior;
    using ParameciumTickBehavior = Tickbehaviors.ParameciumTickBehavior;
    using PinkballTickBehavior = Tickbehaviors.PinkballTickBehavior;
    using TankBehavior = Tickbehaviors.TankBehavior;
    using TeethTickBehavior = Tickbehaviors.TeethTickBehavior;
    using WalkerTickBehavior = Tickbehaviors.WalkerTickBehavior;
    using ControllerTrapReleaseBehavior = Trapreleasebehaviors.ControllerTrapReleaseBehavior;
    using DefaultTrapReleaseBehavior = Trapreleasebehaviors.DefaultTrapReleaseBehavior;

    public class MicrosoftBlockFactory : BlockFactory
    {
        private MicrosoftBlockFactory()
        {
        }

        private static MicrosoftBlockFactory instance;

        public static MicrosoftBlockFactory Instance
        {
            get
            {
                lock (typeof(MicrosoftBlockFactory))
                {
                    return instance ?? (instance = new MicrosoftBlockFactory());
                }
            }
        }

        public override Block CreateBlock(Type type, Moves facing)
        {
            Block ret = null;
            IBlockTickBehavior nullTick = NullTickBehavior.Instance;
            IButtonBehavior nullButton = NullButtonBehavior.Instance;
            CloneBehavior nullClone = NullCloneBehavior.Instance;
            ITrapReleaseBehavior defaultTrap = DefaultTrapReleaseBehavior.Instance;
            BlockReaction canMove = CanMove.Instance;
            BlockReaction cannotMove = CannotMove.Instance;
            IDestroyBehavior defaultDestroy = DefaultDestroyBehavior.Instance;
            switch (type)
            {
                case Type.BLOB:
                    ret = new Block(type, facing, BlobTickBehavior.Instance, canMove, CreatureTo.Instance, nullButton, BlobCloneBehavior.Instance, defaultTrap, defaultDestroy);
                    break;
                case Type.BLOCK:
                    ret = new Block(type, facing, nullTick, canMove, BlockTo.Instance, nullButton, CloneSameDirection.Instance, defaultTrap, defaultDestroy);
                    break;
                case Type.BLUEBUTTON:
                    ret = new Block(type, facing, nullTick, canMove, BlueButtonTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.BLUEKEY:
                    ret = new Block(type, facing, nullTick, canMove, BlueKeyTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.BLUELOCK:
                    ret = new Block(type, facing, nullTick, canMove, BlueLockTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.BLUEWALLFAKE:
                    ret = new Block(type, facing, nullTick, canMove, BlueWallFakeTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.BLUEWALLREAL:
                    ret = new Block(type, facing, nullTick, canMove, BlueWallRealTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.BOMB:
                    ret = new Block(type, facing, nullTick, canMove, BombTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.BROWNBUTTON:
                    ret = new Block(type, facing, nullTick, BrownButtonFrom.Instance, BrownButtonTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.BUG:
                    ret = new Block(type, facing, BugTickBehavior.Instance, canMove, CreatureTo.Instance, nullButton, CloneBoss.Instance, ControllerTrapReleaseBehavior.Instance, defaultDestroy);
                    break;
                case Type.BURNEDCHIP:
                    break;
                case Type.CHIP:
                    ret = new Block(type, facing, ChipTickBehavior.Instance, canMove, ChipTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    Game.Instance.Chip = ret;
                    break;
                case Type.CLONEBLOCK:
                    break;
                case Type.CLONEMACHINE:
                    ret = new Block(type, facing, nullTick, cannotMove, cannotMove, CloneMachineBehavior.Instance, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.COMPUTERCHIP:
                    ret = new Block(type, facing, nullTick, canMove, ComputerChipTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.DIRT:
                    ret = new Block(type, facing, nullTick, canMove, DirtTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.DROWNEDCHIP:
                    break;
                case Type.EXIT:
                    ret = new Block(type, facing, nullTick, canMove, ExitTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.FAKEEXIT:
                    break;
                case Type.FIRE:
                    ret = new Block(type, facing, nullTick, canMove, FireTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.FIREBOOTS:
                    ret = new Block(type, facing, nullTick, canMove, FirebootsTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.FIREBALL:
                    ret = new Block(type, facing, FireballTickBehavior.Instance, canMove, CreatureTo.Instance, nullButton, CloneSameDirection.Instance, defaultTrap, defaultDestroy);
                    break;
                case Type.FLIPPERS:
                    ret = new Block(type, facing, nullTick, canMove, FlippersTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.FLOOR:
                    ret = new Block();
                    ret.SetType(Type.FLOOR);
                    break;
                case Type.FORCEFLOOR:
                    ret = new Block(type, facing, nullTick, canMove, ForceFloorTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.RANDOMFORCEFLOOR:
                    ret = new Block(type, facing, nullTick, canMove, RandomForceFloorTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.GLIDER:
                    ret = new Block(type, facing, GliderTickBehavior.Instance, canMove, CreatureTo.Instance, nullButton, CloneSameDirection.Instance, defaultTrap, defaultDestroy);
                    break;
                case Type.GRAVEL:
                    ret = new Block(type, facing, nullTick, canMove, GravelTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.GREENBUTTON:
                    ret = new Block(type, facing, nullTick, canMove, GreenButtonTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.GREENKEY:
                    ret = new Block(type, facing, nullTick, canMove, GreenKeyTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.GREENLOCK:
                    ret = new Block(type, facing, nullTick, canMove, GreenLockTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.HIDDENWALL:
                    ret = new Block(type, facing, nullTick, canMove, BlueWallRealTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.HINT:
                    ret = new Block(type, facing, nullTick, HintFrom.Instance, HintTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.ICE:
                    ret = new Block(type, facing, nullTick, IceFrom.Instance, IceTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.ICECORNER:
                    ret = new Block(type, facing, nullTick, IceFrom.Instance, IceCornerTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.ICEBLOCK: // ?
                    break;
                case Type.ICESKATES:
                    ret = new Block(type, facing, nullTick, canMove, IceskatesTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.INVISIBLEWALL:
                    ret = new Block(type, facing, nullTick, canMove, cannotMove, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.LOCK:
                    break;
                case Type.PARAMECIUM:
                    ret = new Block(type, facing, ParameciumTickBehavior.Instance, canMove, CreatureTo.Instance, nullButton, CloneBoss.Instance, ControllerTrapReleaseBehavior.Instance, defaultDestroy);
                    break;
                case Type.PINKBALL:
                    ret = new Block(type, facing, PinkballTickBehavior.Instance, canMove, CreatureTo.Instance, nullButton, CloneSameDirection.Instance, defaultTrap, defaultDestroy);
                    break;
                case Type.RECESSEDWALL:
                    ret = new Block(type, facing, nullTick, canMove, RecessedWallTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.REDBUTTON:
                    ret = new Block(type, facing, nullTick, canMove, RedButtonTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.REDKEY:
                    ret = new Block(type, facing, nullTick, canMove, RedKeyTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.REDLOCK:
                    ret = new Block(type, facing, nullTick, canMove, RedLockTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.SOCKET:
                    ret = new Block(type, facing, nullTick, canMove, SocketTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.SUCTIONBOOTS:
                    ret = new Block(type, facing, nullTick, canMove, SuctionbootsTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.SWIMMINGCHIP:
                    break;
                case Type.TANK:
                    TankBehavior tb = new TankBehavior();
                    IButtonBehavior bb = tb;
                    ret = new Block(type, facing, tb, canMove, CreatureTo.Instance, bb, CloneSameDirection.Instance, defaultTrap, defaultDestroy);
                    Buttons.AddBlueButtonsListener(ret);
                    break;
                case Type.TEETH:
                    ret = new Block(type, facing, TeethTickBehavior.Instance, canMove, CreatureTo.Instance, nullButton, CloneBoss.Instance, ControllerTrapReleaseBehavior.Instance, defaultDestroy);
                    break;
                case Type.TELEPORT:
                    ret = new Block(type, facing, nullTick, canMove, TeleportTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.THIEF:
                    ret = new Block(type, facing, nullTick, canMove, ThiefTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.THINWALL:
                    ret = new Block(type, facing, nullTick, ThinWallFrom.Instance, ThinWallTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.THINWALLSE:
                    ret = new Block(type, facing, nullTick, ThinWallSeFrom.Instance, ThinWallSeTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.TOGGLEWALLCLOSED: // Yes, fallthrough
                case Type.TOGGLEWALLOPEN:
                    ret = new Block(type, facing, nullTick, canMove, ToggleWallTo.Instance, ToggleWallBehavior.Instance, nullClone, defaultTrap, defaultDestroy);
                    Buttons.AddGreenButtonsListener(ret);
                    break;
                case Type.TRAP:
                    ret = new Block(type, facing, nullTick, cannotMove, TrapTo.Instance, TrapBehavior.Instance, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.WALKER:
                    ret = new Block(type, facing, WalkerTickBehavior.Instance, canMove, CreatureTo.Instance, nullButton, CloneSameDirection.Instance, defaultTrap, defaultDestroy);
                    break;
                case Type.WALL:
                    ret = new Block(type, facing, nullTick, canMove, cannotMove, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.WATER:
                    ret = new Block(type, facing, nullTick, WaterFrom.Instance, WaterTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.YELLOWKEY:
                    ret = new Block(type, facing, nullTick, canMove, YellowKeyTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
                case Type.YELLOWLOCK:
                    ret = new Block(type, facing, nullTick, canMove, YellowLockTo.Instance, nullButton, nullClone, defaultTrap, defaultDestroy);
                    break;
            }

            if (ret == null)
            {
                //System.out.println("The block requested hasn't been implemented. Using default behaviors");
                ret = new Block(type, facing);
            }
            return ret;
        }
    }
}