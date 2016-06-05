namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;

    public class IceFrom : NoSlipReaction
    {
        private static IceFrom instance;

        private IceFrom()
        {
        }

        public static IceFrom Instance
        {
            get
            {
                lock (typeof(IceFrom))
                {
                    return instance ?? (instance = new IceFrom());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            return !moving.Forced || moving.Chip && HasBoots(Boots.ICESKATES);
        }
    }
}