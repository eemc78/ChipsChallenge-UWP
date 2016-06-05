namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;
    using Moves = Move.Moves;

    public class IceTo : BlockReaction
    {
        private static IceTo instance;

        private IceTo()
        {
        }

        public static IceTo Instance
        {
            get
            {
                lock (typeof(IceTo))
                {
                    return instance ?? (instance = new IceTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }

        public override Moves? CausesSlip(Block moving, Block standing)
        {
            if (moving.Chip && HasBoots(Boots.ICESKATES))
            {
                return null;
            }

            return moving.Facing;
        }
    }
}