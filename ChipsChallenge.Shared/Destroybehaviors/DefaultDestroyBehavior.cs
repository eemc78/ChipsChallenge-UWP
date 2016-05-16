namespace ChipsChallenge.Shared.Destroybehaviors
{
    using NullButtonBehavior = Buttonbehaviors.NullButtonBehavior;
    using NullTickBehavior = Tickbehaviors.NullTickBehavior;

    public class DefaultDestroyBehavior : IDestroyBehavior
    {
        private DefaultDestroyBehavior()
        {
        }
        
        private static DefaultDestroyBehavior instance;

        public static DefaultDestroyBehavior Instance
        {
            get
            {
                lock (typeof(DefaultDestroyBehavior))
                {
                    return instance ?? (instance = new DefaultDestroyBehavior());
                }
            }
        }

        public virtual void Destroy(Block block)
        {
            ClearReactions(block);
            bool creature = block.Creature;

            if (creature)
            {
                Creatures.RemoveCreature(block);
            }

            if (creature || block.IsBlock())
            {
                Game.Instance.RemoveFromSlipList(block);
            }
            
            Game.Instance.Level.RemoveBlock(block);
        }

        private void ClearReactions(Block block)
        {
            block.BlockTickBehavior = NullTickBehavior.Instance;
            block.ButtonBehavior = NullButtonBehavior.Instance;
        }
    }
}