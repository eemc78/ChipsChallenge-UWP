namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;
    using Type = Block.Type;

    public class WaterTo : NoSlipReaction
    {
        private static WaterTo instance;

        private WaterTo()
        {
        }

        public static WaterTo Instance
        {
            get
            {
                lock (typeof(WaterTo))
                {
                    return instance ?? (instance = new WaterTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            switch (moving.getType())
            {
                case Type.CHIP:
                    if (HasBoots(Boots.FLIPPERS))
                    {
                        moving.SetType(Type.SWIMMINGCHIP);
                    }
                    else
                    {
                        moving.Destroy();
                        standing.Replace(CreateBlock(Type.DROWNEDCHIP));
                        Game().Die("Ooops! Chip can't swim without flippers!", Shared.Sound.Die);
                    }
                    break;
                case Type.BLOCK:
                    moving.Destroy();
                    standing.Replace(CreateBlock(Type.DIRT));
                    Sound().Play(Shared.Sound.Water);
                    break;
                case Type.BUG:
                case Type.TEETH:
                case Type.FIREBALL:
                case Type.PARAMECIUM:
                case Type.PINKBALL:
                case Type.WALKER:
                case Type.TANK:
                case Type.BLOB:
                    moving.Destroy();
                    break;
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}