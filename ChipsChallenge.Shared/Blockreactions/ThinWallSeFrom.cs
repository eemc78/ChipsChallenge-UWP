namespace ChipsChallenge.Shared.Blockreactions
{
    using Moves = Move.Moves;

    public class ThinWallSeFrom : NoSlipReaction
    {

        private ThinWallSeFrom()
        {
        }

        private static ThinWallSeFrom instance;

        public static ThinWallSeFrom Instance
        {
            get
            {
                lock (typeof(ThinWallSeFrom))
                {
                    if (instance == null)
                    {
                        instance = new ThinWallSeFrom();
                    }
                    return instance;
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Facing == Moves.UP || moving.Facing == Moves.LEFT;
        }
    }
}