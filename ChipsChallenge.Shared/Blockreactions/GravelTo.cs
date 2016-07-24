namespace ChipsChallenge.Shared.Blockreactions
{
    public class GravelTo : NoSlipReaction
    {
        private static GravelTo instance;

        private GravelTo()
        {
        }

        public static GravelTo Instance
        {
            get
            {
                lock (typeof(GravelTo))
                {
                    return instance ?? (instance = new GravelTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip || moving.IsBlock();
        }
    }
}