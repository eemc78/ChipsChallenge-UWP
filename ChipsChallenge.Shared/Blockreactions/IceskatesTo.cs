namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;
    using Type = Block.Type;

    public class IceskatesTo : NoSlipReaction
    {
        private static IceskatesTo instance;

        private IceskatesTo()
        {
        }

        public static IceskatesTo Instance
        {
            get
            {
                lock (typeof(IceskatesTo))
                {
                    return instance ?? (instance = new IceskatesTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                TakeBoots(Boots.ICESKATES);
                standing.Replace(CreateBlock(Type.FLOOR));
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip || moving.IsBlock();
        }
    }
}