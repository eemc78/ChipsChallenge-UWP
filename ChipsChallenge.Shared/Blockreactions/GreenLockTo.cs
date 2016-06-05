namespace ChipsChallenge.Shared.Blockreactions
{
    using Key = Inventory.Key;

    public class GreenLockTo : NoSlipReaction
    {
        private static GreenLockTo instance;

        private GreenLockTo()
        {
        }

        public static GreenLockTo Instance
        {
            get
            {
                lock (typeof(GreenLockTo))
                {
                    return instance ?? (instance = new GreenLockTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            UseKey(Key.GREEN);
            standing.Destroy();
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip && HasKey(Key.GREEN);
        }
    }
}