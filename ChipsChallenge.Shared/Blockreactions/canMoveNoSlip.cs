namespace ChipsChallenge.Shared.Blockreactions
{
    /// <summary>
    /// Standard behavior for when things can move
    /// </summary>
    public class CanMoveNoSlip : NoSlipReaction
    {
        private static CanMoveNoSlip instance;

        private CanMoveNoSlip()
        {
        }

        public static CanMoveNoSlip Instance
        {
            get
            {
                lock (typeof(CanMoveNoSlip))
                {
                    return instance ?? (instance = new CanMoveNoSlip());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}