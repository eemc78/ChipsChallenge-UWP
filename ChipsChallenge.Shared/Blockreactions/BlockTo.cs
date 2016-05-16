namespace ChipsChallenge.Shared.Blockreactions
{
    using Moves = Move.Moves;

    /// <summary>
    /// Moving to a Block
    /// </summary>
    public class BlockTo : NoSlipReaction
    {
        private BlockTo()
        {
        }
        private static BlockTo instance;

        public static BlockTo Instance
        {
            get
            {
                lock (typeof(BlockTo))
                {
                    return instance ?? (instance = new BlockTo());
                }
            }
        }


        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                Game().RemoveFromSlipList(standing);
                Level().MoveBlock(standing, moving.Facing, true, false);
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                Moves facingBefore = standing.Facing;
                standing.Facing = moving.Facing;

                // We want to exclude ice corners here
                bool isOnIce = standing.IsOn(Block.Type.ICE);
                bool ret = (isOnIce || !isOnIce && standing.CanMoveFrom()) && standing.CanMoveTo(moving.Facing);
                standing.Facing = facingBefore;
                return ret;
            }

            return false;
        }
    }
}