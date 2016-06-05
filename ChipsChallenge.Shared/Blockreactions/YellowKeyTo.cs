namespace ChipsChallenge.Shared.Blockreactions
{
    using Key = Inventory.Key;
    using Type = Block.Type;

    public class YellowKeyTo : NoSlipReaction
    {
        private static YellowKeyTo instance;

        private YellowKeyTo()
        {
        }

        public static YellowKeyTo Instance
        {
            get
            {
                lock (typeof(YellowKeyTo))
                {
                    if (instance == null)
                    {
                        instance = new YellowKeyTo();
                    }
                    return instance;
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                TakeKey(Key.YELLOW);
                standing.Replace(CreateBlock(Type.FLOOR));
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}