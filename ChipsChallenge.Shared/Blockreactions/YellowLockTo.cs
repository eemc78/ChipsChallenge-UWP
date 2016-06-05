namespace ChipsChallenge.Shared.Blockreactions
{
    using Key = Inventory.Key;

    public class YellowLockTo : NoSlipReaction
    {
        private static YellowLockTo instance;

        private YellowLockTo()
        {
        }

        public static YellowLockTo Instance
        {
            get
            {
                lock (typeof(YellowLockTo))
                {
                    return instance ?? (instance = new YellowLockTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            UseKey(Key.YELLOW);
            standing.Destroy();
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip && HasKey(Key.YELLOW);
        }
    }
}