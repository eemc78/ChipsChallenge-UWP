namespace ChipsChallenge.Shared
{
    using System.Collections.Concurrent;
    using System.Collections.Generic;

    using Moves = Move.Moves;

    public class SlipList
    {
        private readonly IDictionary<Block, Moves> map = new ConcurrentDictionary<Block, Moves>();
        private readonly IList<Block> order = new List<Block>();

        public virtual void Put(Block b, Moves m)
        {
            map[b] = m;
            if (!order.Contains(b))
            {
                order.Add(b);
            }
        }

        public virtual void Remove(Block b)
        {
            order.Remove(b);
            map.Remove(b);
        }

        public virtual BlockMove Get(int i)
        {
            Block b = order[i];
            return new BlockMove(b, map[b]);
        }

        public virtual int Size()
        {
            return order.Count;
        }

        public virtual void Clear()
        {
            map.Clear();
            order.Clear();
        }
    }
}