using System.Collections.Generic;
using System.Linq;

namespace ChipsChallenge.Shared
{
    public class Creatures
    {
        private static readonly IList<Block> CreatureList = new List<Block>();
        private static int creatureTicks;
        private static bool blobMove;

        public static Block Boss { get; set; }

        public static Block GetController(Block b)
        {
            int index = CreatureList.IndexOf(b);
            if (index == 0)
            {
                return null;
            }

            return CreatureList[CreatureList.IndexOf(b) - 1];
        }

        public static void Clear()
        {
            CreatureList.Clear();
        }

        public static void AddCreature(Block block)
        {
            CreatureList.Add(block);
        }

        public static void RemoveCreature(Block block)
        {
            CreatureList.Remove(block);
        }

        public static void Tick()
        {
            creatureTicks = (creatureTicks + 1) % Game.SpeedFrac;
            if (creatureTicks == 0)
            {
                blobMove = !blobMove;

                foreach (Block block in CreatureList.ToList())
                {
                    if (!block.Forced)
                    {
                        if (!block.Trapped && (blobMove || !(block.IsA(Block.Type.BLOB) || block.IsA(Block.Type.TEETH))))
                        {
                            block.Tick();
                        }
                    }
                }
            }
        }
    }
}