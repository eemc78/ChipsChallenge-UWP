namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;

    public class FirebootsTo : NoSlipReaction
    {
        private FirebootsTo()
        {
        }
        private static FirebootsTo instance;

        public static FirebootsTo Instance
        {
            get
            {
                lock (typeof(FirebootsTo))
                {
                    return instance ?? (instance = new FirebootsTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                TakeBoots(Boots.FIREBOOTS);
                standing.Replace(CreateBlock(Block.Type.FLOOR));
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip || moving.IsBlock();
        }
    }
}