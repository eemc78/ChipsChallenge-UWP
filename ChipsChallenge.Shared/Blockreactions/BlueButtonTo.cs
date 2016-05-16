namespace ChipsChallenge.Shared.Blockreactions
{
    public class BlueButtonTo : NoSlipReaction
    {
        private BlueButtonTo()
        {
        }
        private static BlueButtonTo instance;

        public static BlueButtonTo Instance
        {
            get
            {
                lock (typeof(BlueButtonTo))
                {
                    return instance ?? (instance = new BlueButtonTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            Buttons.BlueButtonDown(standing);
            Sound().Play(Shared.Sound.Button);
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}