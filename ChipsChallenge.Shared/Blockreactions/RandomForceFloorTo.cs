namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;
    using Moves = Move.Moves;

    public class RandomForceFloorTo : BlockReaction
    {
        private RandomForceFloorTo()
        {
        }
        private static RandomForceFloorTo instance;

        public static RandomForceFloorTo Instance
        {
            get
            {
                lock (typeof(RandomForceFloorTo))
                {
                    return instance ?? (instance = new RandomForceFloorTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip;
        }

        public override Moves? CausesSlip(Block moving, Block standing)
        {
            if ((moving.Chip && HasBoots(Boots.SUCTIONBOOTS)))
            {
                return null;
            }

            return Move.Random;
        }
    }
}