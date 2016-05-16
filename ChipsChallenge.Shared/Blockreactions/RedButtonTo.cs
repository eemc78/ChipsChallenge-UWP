namespace ChipsChallenge.Shared.Blockreactions
{
    public class RedButtonTo : NoSlipReaction
    {
        private RedButtonTo()
        {
        }
        
        private static RedButtonTo instance;

        public static RedButtonTo Instance
        {
            get
            {
                lock (typeof(RedButtonTo))
                {
                    return instance ?? (instance = new RedButtonTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            Buttons.RedButtonDown(standing);
            Sound().Play(Shared.Sound.Button);
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}