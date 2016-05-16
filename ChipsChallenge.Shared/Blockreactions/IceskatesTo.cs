namespace ChipsChallenge.Shared.Blockreactions
{
    using Type = Block.Type;
    using Boots = Inventory.Boots;

    public class IceskatesTo : NoSlipReaction
    {

        private IceskatesTo()
        {
        }
        
        private static IceskatesTo instance;

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