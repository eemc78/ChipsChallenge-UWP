namespace ChipsChallenge.Shared.Blockreactions
{
    using Type = Block.Type;
    using Key = Inventory.Key;

    public class GreenKeyTo : NoSlipReaction
    {
        private GreenKeyTo()
        {
        }
        private static GreenKeyTo instance;

        public static GreenKeyTo Instance
        {
            get
            {
                lock (typeof(GreenKeyTo))
                {
                    if (instance == null)
                    {
                        instance = new GreenKeyTo();
                    }
                    return instance;
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                TakeKey(Key.GREEN);
                standing.Replace(CreateBlock(Type.FLOOR));
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}