namespace ChipsChallenge.Shared.Blockreactions
{
    using Moves = Move.Moves;

    public abstract class SlipReaction : BlockReaction
    {
        public abstract override void React(Block moving, Block standing);

        public abstract override bool canMove(Block moving, Block standing);

        public override Moves? CausesSlip(Block moving, Block standing)
        {
            return moving.Facing;
        }
    }
}