namespace ChipsChallenge.Shared.Blockreactions
{
    public class HintFrom : NoSlipReaction
    {
        private static HintFrom instance;

        private HintFrom()
        {
        }

        public static HintFrom Instance
        {
            get
            {
                lock (typeof(HintFrom))
                {
                    return instance ?? (instance = new HintFrom());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            Game().IsChipOnHintField = false;
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}