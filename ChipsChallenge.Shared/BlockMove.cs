namespace ChipsChallenge.Shared
{
    using Moves = Move.Moves;

    public class BlockMove
    {
        public readonly Block Block;
        public readonly Moves Move;

        public BlockMove(Block b, Moves m)
        {
            Block = b;
            Move = m;
        }

        public BlockMove Clone()
        {
            return new BlockMove(Block, Move);
        }
    }
}