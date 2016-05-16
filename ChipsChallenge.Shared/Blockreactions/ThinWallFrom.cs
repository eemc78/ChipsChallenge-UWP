namespace ChipsChallenge.Shared.Blockreactions
{
    using Moves = Move.Moves;

    public class ThinWallFrom : NoSlipReaction
    {
        private ThinWallFrom()
        {
        }

        private static ThinWallFrom instance;

        public static ThinWallFrom Instance
        {
            get
            {
                lock (typeof(ThinWallFrom))
                {
                    return instance ?? (instance = new ThinWallFrom());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            switch (moving.Facing)
            {
                case Moves.UP:
                    return standing.Facing != Moves.UP;
                case Moves.DOWN:
                    return standing.Facing != Moves.DOWN;
                case Moves.LEFT:
                    return standing.Facing != Moves.LEFT;
                case Moves.RIGHT:
                    return standing.Facing != Moves.RIGHT;
            }
            return false;
        }
    }
}