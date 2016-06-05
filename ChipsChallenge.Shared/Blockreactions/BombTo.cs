namespace ChipsChallenge.Shared.Blockreactions
{
    public class BombTo : NoSlipReaction
    {
        private static BombTo instance;

        private BombTo()
        {
        }

        public static BombTo Instance
        {
            get
            {
                lock (typeof(BombTo))
                {
                    return instance ?? (instance = new BombTo());
                }
            }
        }


        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                Die("Ooops! Don't touch the bombs!");
            }
            else
            {
                // If collision, both bomb and other block is removed
                moving.Destroy();
                standing.Destroy();
                Sound().Play(Shared.Sound.Bomb);
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}