namespace ChipsChallenge.Shared.Blockreactions
{
    using Type = Block.Type;
    using Key = Inventory.Key;

    public class YellowKeyTo : NoSlipReaction
    {
        private YellowKeyTo()
        {
        }
        private static YellowKeyTo instance;

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