using System.Collections.Generic;

namespace ChipsChallenge.Shared
{
    using Type = Block.Type;
    using Moves = Move.Moves;

    public abstract class BlockFactory
    {
        private readonly IDictionary<Type, IDictionary<Moves, Block>> loadedBlocks = new Dictionary<Type, IDictionary<Moves, Block>>();

        public Block Get(Type type)
        {
            return Get(type, Moves.DOWN);
        }

        public Block Get(Type type, Moves direction)
        {
            IDictionary<Moves, Block> movesBlocks;
            loadedBlocks.TryGetValue(type, out movesBlocks);

            if (movesBlocks == null)
            {
                movesBlocks = new Dictionary<Moves, Block>();
                loadedBlocks[type] = movesBlocks;
            }

            BlockImageFactory.Instance.GetImage(type, direction, false);

            Block b = CreateBlock(type, direction);
            movesBlocks[direction] = b;
            return b;
        }

        public abstract Block CreateBlock(Type type, Moves direction);
    }
}