namespace ChipsChallenge.Shared.Blockreactions
{
    public class ToggleWallTo : NoSlipReaction
    {
        private ToggleWallTo()
        {
        }
        private static ToggleWallTo instance;

        public static ToggleWallTo Instance
        {
            get
            {
                lock (typeof(ToggleWallTo))
                {
                    return instance ?? (instance = new ToggleWallTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            return standing.IsA(Block.Type.TOGGLEWALLOPEN);
        }
    }
}