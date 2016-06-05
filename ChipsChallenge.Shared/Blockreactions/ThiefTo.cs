namespace ChipsChallenge.Shared.Blockreactions
{
    public class ThiefTo : NoSlipReaction
    {
        private static ThiefTo instance;

        private ThiefTo()
        {
        }

        public static ThiefTo Instance
        {
            get
            {
                lock (typeof(ThiefTo))
                {
                    if (instance == null)
                    {
                        instance = new ThiefTo();
                    }
                    return instance;
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            Inventory().ClearBoots();
            Sound().Play(Shared.Sound.Thief);
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip;
        }
    }
}