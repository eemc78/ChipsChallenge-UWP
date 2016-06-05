namespace ChipsChallenge.Shared.Blockreactions
{
    using Type = Block.Type;

    public class BlueWallFakeTo : NoSlipReaction
    {
        private static BlueWallFakeTo instance;

        private BlueWallFakeTo()
        {
        }

        public static BlueWallFakeTo Instance
        {
            get
            {
                lock (typeof(BlueWallFakeTo))
                {
                    return instance ?? (instance = new BlueWallFakeTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            // Wall is converted to floor
            standing.Replace(CreateBlock(Type.FLOOR));
        }

        public override bool canMove(Block moving, Block standing)
        {
            // Only chip can move to it
            return moving.Chip;
        }
    }
}