namespace ChipsChallenge.Shared.Blockreactions
{
    public class CreatureTo : NoSlipReaction
    {
        private CreatureTo()
        {
        }

        private static CreatureTo instance;

        public static CreatureTo Instance
        {
            get
            {
                lock (typeof(CreatureTo))
                {
                    return instance ?? (instance = new CreatureTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving != standing)
            {
                Die("Ooops! Look out for creatures!");
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip;
        }
    }
}