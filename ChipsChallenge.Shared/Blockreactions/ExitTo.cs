namespace ChipsChallenge.Shared.Blockreactions
{
    public class ExitTo : NoSlipReaction
    {
        private ExitTo()
        {
        }
        private static ExitTo instance;

        public static ExitTo Instance
        {
            get
            {
                lock (typeof(ExitTo))
                {
                    if (instance == null)
                    {
                        instance = new ExitTo();
                    }
                    return instance;
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                Sound().Play(Shared.Sound.Exit);
                Game().SetLevelComplete();
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip || moving.IsBlock();
        }
    }
}