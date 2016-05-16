namespace ChipsChallenge.Shared.Blockreactions
{
    public class HintTo : NoSlipReaction
    {
        private HintTo()
        {
        }
        private static HintTo mInstance;

        public static HintTo Instance
        {
            get
            {
                lock (typeof(HintTo))
                {
                    return mInstance ?? (mInstance = new HintTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            Game().IsChipOnHintField = true;
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}