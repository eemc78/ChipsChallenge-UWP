namespace ChipsChallenge.Shared.Blockreactions
{
    using Type = Block.Type;

    public class WaterFrom : NoSlipReaction
    {
        private static WaterFrom instance;

        private WaterFrom()
        {
        }

        public static WaterFrom Instance
        {
            get
            {
                lock (typeof(WaterFrom))
                {
                    return instance ?? (instance = new WaterFrom());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                moving.SetType(Type.CHIP);
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}