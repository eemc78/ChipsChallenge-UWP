namespace ChipsChallenge.Shared.Blockreactions
{
    public class ChipTo : NoSlipReaction
    {
        private static ChipTo instance;

        private ChipTo()
        {
        }

        public static ChipTo Instance
        {
            get
            {
                lock (typeof(ChipTo))
                {
                    return instance ?? (instance = new ChipTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (!moving.Chip)
            {
                if (moving.IsBlock())
                {
                    Die("Ooops! Watch out for moving blocks!");
                }
                else
                {
                    Die("Ooops! Look out for creatures!");
                }
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            if (standing.Chip)
            {
                if (moving.IsBlock())
                {
                    Die("Ooops! Watch out for moving blocks!");
                }
                else
                {
                    Die("Ooops! Look out for creatures!");
                }
            }

            return true;
        }
    }
}