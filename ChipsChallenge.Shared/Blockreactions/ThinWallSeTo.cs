namespace ChipsChallenge.Shared.Blockreactions
{
    using Moves = Move.Moves;

    public class ThinWallSeTo : NoSlipReaction
    {
        private static ThinWallSeTo instance;

        private ThinWallSeTo()
        {
        }

        public static ThinWallSeTo Instance
        {
            get
            {
                lock (typeof(ThinWallSeTo))
                {
                    return instance ?? (instance = new ThinWallSeTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Facing == Moves.RIGHT || moving.Facing == Moves.DOWN;
        }
    }
}