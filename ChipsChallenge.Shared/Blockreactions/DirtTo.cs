namespace ChipsChallenge.Shared.Blockreactions
{
    public class DirtTo : NoSlipReaction
    {
        private static DirtTo instance;

        private DirtTo()
        {
        }

        public static DirtTo Instance
        {
            get
            {
                lock (typeof(DirtTo))
                {
                    return instance ?? (instance = new DirtTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            standing.Replace(CreateBlock(Block.Type.FLOOR));
        }

        // Only chip can move
        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip;
        }
    }
}