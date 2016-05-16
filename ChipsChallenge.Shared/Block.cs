// ReSharper disable InconsistentNaming
namespace ChipsChallenge.Shared
{
    using Buttonbehaviors;
    using Blockreactions;

    using ChipsChallenge.Shared.Tickbehaviors;
    using ChipsChallenge.Shared.Trapreleasebehaviors;

    using Destroybehaviors;

    using Microsoft.Graphics.Canvas;

    using NullCloneBehavior = Clonebehaviors.NullCloneBehavior;
    using CloneBehavior = Clonebehaviors.CloneBehavior;
    using NullButtonBehavior = Buttonbehaviors.NullButtonBehavior;
    using Moves = Move.Moves;
    using BlockReaction = Blockreactions.BlockReaction;
    using DefaultDestroyBehavior = Destroybehaviors.DefaultDestroyBehavior;
    using NullTickBehavior = Tickbehaviors.NullTickBehavior;
    using DefaultTrapReleaseBehavior = Trapreleasebehaviors.DefaultTrapReleaseBehavior;

    public class Block
    {
        private Type type;
        private Moves facing;
        private IBlockTickBehavior tickBehavior = NullTickBehavior.Instance;
        private BlockReaction @from = CanMoveNoSlip.Instance;
        private BlockReaction to = CanMoveNoSlip.Instance;
        private IButtonBehavior buttonBehavior = NullButtonBehavior.Instance;
        private readonly CloneBehavior cloneBehavior = NullCloneBehavior.Instance;
        private readonly ITrapReleaseBehavior trapReleaseBehavior = DefaultTrapReleaseBehavior.Instance;
        private readonly IDestroyBehavior destroyBehavior = DefaultDestroyBehavior.Instance;

        public enum Type
        {
            BLOB,
            BLOCK,
            BLUEBUTTON,
            BLUEKEY,
            BLUELOCK,
            BLUEWALLREAL,
            BLUEWALLFAKE,
            BOMB,
            BROWNBUTTON,
            BUG,
            BURNEDCHIP,
            CHIP,
            CLONEBLOCK,
            CLONEMACHINE,
            COMPUTERCHIP,
            DIRT,
            DROWNEDCHIP,
            EXIT,
            FAKEEXIT,
            FIRE,
            FIREBOOTS,
            FIREBALL,
            FLIPPERS,
            FLOOR,
            FORCEFLOOR,
            RANDOMFORCEFLOOR,
            GLIDER,
            GRAVEL,
            GREENBUTTON,
            GREENKEY,
            GREENLOCK,
            HIDDENWALL,
            HINT,
            ICE,
            ICECORNER,
            ICEBLOCK,
            ICESKATES,
            INVISIBLEWALL,
            LOCK,
            PARAMECIUM,
            PINKBALL,
            RECESSEDWALL,
            REDBUTTON,
            REDKEY,
            REDLOCK,
            SOCKET,
            SUCTIONBOOTS,
            SWIMMINGCHIP,
            TANK,
            TEETH,
            TELEPORT,
            THIEF,
            THINWALL,
            THINWALLSE,
            TOGGLEWALLCLOSED,
            TOGGLEWALLOPEN,
            TRAP,
            WALKER,
            WALL,
            WATER,
            YELLOWKEY,
            YELLOWLOCK
        }

        public Block()
        {
            Trapped = false;
        }

        public Block(Type t, Moves m)
        {
            Trapped = false;
            type = t;
            facing = m;
        }

        public Block(Type t, Moves m, IBlockTickBehavior btb, BlockReaction from, BlockReaction to, IButtonBehavior bb, CloneBehavior cb, ITrapReleaseBehavior trb, IDestroyBehavior db)
        {
            Trapped = false;
            type = t;
            facing = m;
            tickBehavior = btb;
            this.@from = from;
            this.to = to;
            buttonBehavior = bb;
            cloneBehavior = cb;
            trapReleaseBehavior = trb;
            destroyBehavior = db;
        }

        public static Block Create(Type t)
        {
            return Game.Instance.BlockFactory.Get(t);
        }

        public virtual Type getType()
        {
            return type;
        }

        public virtual bool IsA(Type t)
        {
            return type == t;
        }

        public virtual bool Chip => type == Type.CHIP || type == Type.SWIMMINGCHIP;

        public virtual bool ChipWithIceSkates => Chip && Game.Instance.Inventory.HasBoots(Inventory.Boots.ICESKATES);

        public virtual bool IsBlock()
        {
            return type == Type.BLOCK;
        }

        public virtual bool Ice => type == Type.ICE || type == Type.ICECORNER;

        public virtual bool ForceFloor => type == Type.FORCEFLOOR || type == Type.RANDOMFORCEFLOOR;

        public virtual bool OnIce
        {
            get
            {
                BlockContainer bc = Game.Instance.Level.GetBlockContainer(this);
                return bc.Find(Type.ICE) != null || bc.Find(Type.ICECORNER) != null;
            }
        }

        public virtual bool OnForceFloor
        {
            get
            {
                if (Chip)
                {
                    var block = Game.Instance.Level.GetBlockContainer(this);
                    if (block.Upper != null && block.Upper.ForceFloor)
                    {
                        return true;
                    }
                }

                return false;
            }
        }

        public virtual bool IsOn(Type blockType)
        {
            return Game.Instance.Level.GetBlockContainer(this).Find(blockType) != null;
        }

        public virtual bool OnTrap => Game.Instance.Level.GetBlockContainer(this).Find(Type.TRAP) != null;

        public virtual bool Trapped { get; set; }

        public virtual bool OnCloner => Game.Instance.Level.GetBlockContainer(this).Lower.IsA(Type.CLONEMACHINE);

        public virtual bool Creature
        {
            get
            {
                return getType() == Type.BLOB || getType() == Type.BUG || getType() == Type.FIREBALL || getType() == Type.GLIDER || getType() == Type.PARAMECIUM || getType() == Type.PINKBALL || getType() == Type.TANK || getType() == Type.TEETH || getType() == Type.WALKER;
            }
        }

        public override string ToString()
        {
            return getType().ToString();
        }

        public virtual void SetType(Type blockType)
        {
            type = blockType;
        }

        public virtual Moves Facing
        {
            get
            {
                return facing;
            }
            set
            {
                facing = value;
            }
        }

        public virtual void Tick()
        {
            tickBehavior.Tick(this);
        }

        public virtual bool CanMoveFrom()
        {
            return Game.Instance.Level.GetBlockContainer(this).CanMoveFrom(this);
        }

        public virtual bool CanMoveTo(Moves direction)
        {
            return Game.Instance.Level.GetBlockContainer(this, direction).CanMoveTo(this);
        }

        public virtual bool CanMove(Moves direction)
        {
            return CanMoveFrom() && CanMoveTo(direction);
        }

        public virtual bool Move(Moves direction, bool ignoreFrom, bool ignoreTo)
        {
            return Game.Instance.Level.MoveBlock(this, direction, ignoreFrom, ignoreTo);
        }

        public virtual bool Move(Moves direction)
        {
            return Move(direction, false, false);
        }

        public virtual void Destroy()
        {
            destroyBehavior.Destroy(this);
        }

        public virtual void ButtonDown(Block button)
        {
            buttonBehavior.ButtonDown(this, button);
        }

        public virtual void ButtonUp(Block button)
        {
            buttonBehavior.ButtonUp(this, button);
        }

        public virtual CanvasBitmap GetImage(bool overlay)
        {
            return BlockImageFactory.Instance.GetImage(type, facing, overlay);
        }

        public virtual BlockReaction FromReaction
        {
            set
            {
                @from = value;
            }
            get
            {
                return @from;
            }
        }

        public virtual BlockReaction ToReaction
        {
            set
            {
                to = value;
            }
            get
            {
                return to;
            }
        }

        public virtual IBlockTickBehavior BlockTickBehavior
        {
            set
            {
                tickBehavior = value;
            }
        }

        public virtual IButtonBehavior ButtonBehavior
        {
            set
            {
                buttonBehavior = value;
            }
        }

        public virtual Point Point => Game.Instance.Level.GetPoint(this);

        public virtual BlockContainer BlockContainer => Game.Instance.Level.GetBlockContainer(this);

        public virtual void Replace(Block b)
        {
            Game.Instance.Level.ReplaceBlock(this, b);
        }

        public Block Clone()
        {
            return cloneBehavior.CloneIt(this);
        }

        public virtual bool Forced { set; get; }

        public virtual void ReleaseFromTrap()
        {
            trapReleaseBehavior.ReleaseFromTrap(this);
        }
    }
}