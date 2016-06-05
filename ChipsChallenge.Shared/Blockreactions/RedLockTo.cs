namespace ChipsChallenge.Shared.Blockreactions
{
    using Key = Inventory.Key;

    public class RedLockTo : NoSlipReaction
    {
        private static RedLockTo instance;

        private RedLockTo()
        {
        }

        public static RedLockTo Instance
        {
            get
            {
                lock (typeof(RedLockTo))
                {
                    return instance ?? (instance = new RedLockTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            UseKey(Key.RED);
            standing.Destroy();
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip && HasKey(Key.RED);
        }
    }
}