namespace ChipsChallenge.Shared.Blockreactions
{
    /// <summary>
    /// Standard behavior for when things CANNOT move
    /// </summary>
    public class CanMove : NoSlipReaction
    {
        private CanMove()
        {
        }
        private static CanMove instance;

        public static CanMove Instance
        {
            get
            {
                lock (typeof(CanMove))
                {
                    return instance ?? (instance = new CanMove());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            // No reaction
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}