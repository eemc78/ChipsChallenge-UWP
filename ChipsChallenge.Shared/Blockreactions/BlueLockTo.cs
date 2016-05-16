namespace ChipsChallenge.Shared.Blockreactions
{
    public class BlueLockTo : NoSlipReaction
    {
        private BlueLockTo()
        {
        }
        private static BlueLockTo instance;

        public static BlueLockTo Instance
        {
            get
            {
                lock (typeof(BlueLockTo))
                {
                    return instance ?? (instance = new BlueLockTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            UseKey(Shared.Inventory.Key.BLUE);
            standing.Destroy();
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip && HasKey(Shared.Inventory.Key.BLUE);
        }
    }
}