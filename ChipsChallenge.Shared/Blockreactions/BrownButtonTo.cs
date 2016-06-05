namespace ChipsChallenge.Shared.Blockreactions
{
    public class BrownButtonTo : NoSlipReaction
    {
        private static BrownButtonTo instance;

        private BrownButtonTo()
        {
        }

        public static BrownButtonTo Instance
        {
            get
            {
                lock (typeof(BrownButtonTo))
                {
                    return instance ?? (instance = new BrownButtonTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            Buttons.BrownButtonDown(standing);
            Sound().Play(Shared.Sound.Button);
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}