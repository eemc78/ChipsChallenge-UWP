namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;

    public class FlippersTo : NoSlipReaction
    {
        private static FlippersTo instance;

        private FlippersTo()
        {
        }

        public static FlippersTo Instance
        {
            get
            {
                lock (typeof(FlippersTo))
                {
                    if (instance == null)
                    {
                        instance = new FlippersTo();
                    }
                    return instance;
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                TakeBoots(Boots.FLIPPERS);
                standing.Replace(CreateBlock(Block.Type.FLOOR));
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip || moving.IsBlock();
        }
    }
}