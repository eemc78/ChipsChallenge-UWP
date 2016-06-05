namespace ChipsChallenge.Shared.Blockreactions
{
    public class ToggleWallTo : NoSlipReaction
    {
        private static ToggleWallTo instance;

        private ToggleWallTo()
        {
        }

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