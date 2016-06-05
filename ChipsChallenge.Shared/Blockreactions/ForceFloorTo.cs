namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;
    using Moves = Move.Moves;

    public class ForceFloorTo : BlockReaction
    {
        private static ForceFloorTo instance;

        private ForceFloorTo()
        {
        }

        public static ForceFloorTo Instance
        {
            get
            {
                lock (typeof(ForceFloorTo))
                {
                    return instance ?? (instance = new ForceFloorTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (!(moving.Chip && HasBoots(Boots.SUCTIONBOOTS)))
            {
                moving.Facing = standing.Facing;
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }

        public override Moves? CausesSlip(Block moving, Block standing)
        {
            if (moving.Chip && HasBoots(Boots.SUCTIONBOOTS))
            {
                return null;
            }

            return standing.Facing;
        }
    }
}