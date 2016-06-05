namespace ChipsChallenge.Shared.Blockreactions
{
    using Key = Inventory.Key;
    using Type = Block.Type;

    public class GreenKeyTo : NoSlipReaction
    {
        private static GreenKeyTo instance;

        private GreenKeyTo()
        {
        }

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