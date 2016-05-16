namespace ChipsChallenge.Shared.Blockreactions
{
    public class GreenButtonTo : NoSlipReaction
    {
        private GreenButtonTo()
        {
        }
        private static GreenButtonTo instance;

        public static GreenButtonTo Instance
        {
            get
            {
                lock (typeof(GreenButtonTo))
                {
                    return instance ?? (instance = new GreenButtonTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            Buttons.GreenButtonDown(standing);
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}