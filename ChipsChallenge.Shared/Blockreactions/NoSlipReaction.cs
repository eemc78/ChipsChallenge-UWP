namespace ChipsChallenge.Shared.Blockreactions
{
    using Moves = Move.Moves;

    public abstract class NoSlipReaction : BlockReaction
    {
        public abstract override void React(Block moving, Block standing);

        public abstract override bool canMove(Block moving, Block standing);

        public sealed override Moves? CausesSlip(Block moving, Block standing)
        {
            return null;
        }
    }
}