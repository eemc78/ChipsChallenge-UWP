namespace ChipsChallenge.Shared.Blockreactions
{
    public class TrapTo : NoSlipReaction
    {
        private static TrapTo instance;

        private TrapTo()
        {
        }

        public static TrapTo Instance
        {
            get
            {
                lock (typeof(TrapTo))
                {
                    return instance ?? (instance = new TrapTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.IsBlock() && moving.Forced)
            {
                Game().AddToSlipList(moving, moving.Facing);
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}