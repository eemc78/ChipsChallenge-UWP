namespace ChipsChallenge.Shared.Blockreactions
{
    using Type = Block.Type;

    public class RecessedWallTo : NoSlipReaction
    {
        private static RecessedWallTo instance;

        private RecessedWallTo()
        {
        }

        public static RecessedWallTo Instance
        {
            get
            {
                lock (typeof(RecessedWallTo))
                {
                    return instance ?? (instance = new RecessedWallTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            standing.Replace(CreateBlock(Type.WALL));
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip;
        }
    }
}