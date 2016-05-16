namespace ChipsChallenge.Shared.Blockreactions
{
    using Type = Block.Type;
    using Boots = Inventory.Boots;

    public class SuctionbootsTo : NoSlipReaction
    {
        private SuctionbootsTo()
        {
        }
        private static SuctionbootsTo instance;

        public static SuctionbootsTo Instance
        {
            get
            {
                lock (typeof(SuctionbootsTo))
                {
                    return instance ?? (instance = new SuctionbootsTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                TakeBoots(Boots.SUCTIONBOOTS);
                standing.Replace(CreateBlock(Type.FLOOR));
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip || moving.IsBlock();
        }
    }
}