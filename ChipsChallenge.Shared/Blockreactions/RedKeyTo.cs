namespace ChipsChallenge.Shared.Blockreactions
{
    using Type = Block.Type;
    using Key = Inventory.Key;

    public class RedKeyTo : NoSlipReaction
    {
        private RedKeyTo()
        {
        }
        private static RedKeyTo instance;

        public static RedKeyTo Instance
        {
            get
            {
                lock (typeof(RedKeyTo))
                {
                    if (instance == null)
                    {
                        instance = new RedKeyTo();
                    }
                    return instance;
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                TakeKey(Key.RED);
                standing.Replace(CreateBlock(Type.FLOOR));
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}