namespace ChipsChallenge.Shared.Blockreactions
{
    using Moves = Move.Moves;

    public class ThinWallTo : NoSlipReaction
    {
        private static ThinWallTo instance;

        private ThinWallTo()
        {
        }

        public static ThinWallTo Instance
        {
            get
            {
                lock (typeof(ThinWallTo))
                {
                    return instance ?? (instance = new ThinWallTo());
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
                    return standing.Facing != Moves.DOWN;
                case Moves.DOWN:
                    return standing.Facing != Moves.UP;
                case Moves.LEFT:
                    return standing.Facing != Moves.RIGHT;
                case Moves.RIGHT:
                    return standing.Facing != Moves.LEFT;
            }

            return false;
        }
    }
}