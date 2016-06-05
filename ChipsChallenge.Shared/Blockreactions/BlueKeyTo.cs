namespace ChipsChallenge.Shared.Blockreactions
{
    using Key = Inventory.Key;

    public class BlueKeyTo : NoSlipReaction
    {
        private static BlueKeyTo instance;

        private BlueKeyTo()
        {
        }

        public static BlueKeyTo Instance
        {
            get
            {
                lock (typeof(BlueKeyTo))
                {
                    return instance ?? (instance = new BlueKeyTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                TakeKey(Key.BLUE);
                standing.Replace(CreateBlock(Block.Type.FLOOR));
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}