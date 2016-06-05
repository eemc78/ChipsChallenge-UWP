namespace ChipsChallenge.Shared.Blockreactions
{
    /// <summary>
    /// Standard behavior for when things CANNOT move
    /// </summary>
    public class CannotMove : NoSlipReaction
    {
        private static CannotMove instance;

        private CannotMove()
        {
        }

        public static CannotMove Instance
        {
            get
            {
                lock (typeof(CannotMove))
                {
                    return instance ?? (instance = new CannotMove());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            return false;
        }
    }
}