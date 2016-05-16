namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;
    using Type = Block.Type;

    public class BootsTo : NoSlipReaction
    {
        private BootsTo()
        {
        }
        private static BootsTo instance;

        public static BootsTo Instance
        {
            get
            {
                lock (typeof(BootsTo))
                {
                    return instance ?? (instance = new BootsTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            switch (standing.getType())
            {
                case Type.FLIPPERS:
                    TakeBoots(Boots.FLIPPERS);
                    break;
                case Type.FIREBOOTS:
                    TakeBoots(Boots.FIREBOOTS);
                    break;
                case Type.ICESKATES:
                    TakeBoots(Boots.ICESKATES);
                    break;
                case Type.SUCTIONBOOTS:
                    TakeBoots(Boots.SUCTIONBOOTS);
                    break;
            }
            standing.Destroy();
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip || moving.IsBlock();
        }
    }
}