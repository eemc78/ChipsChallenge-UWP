namespace ChipsChallenge.Shared.Tickbehaviors
{
    public class NullTickBehavior : IBlockTickBehavior
    {
        private static NullTickBehavior instance;

        private NullTickBehavior()
        {
        }

        public static NullTickBehavior Instance
        {
            get
            {
                lock (typeof(NullTickBehavior))
                {
                    return instance ?? (instance = new NullTickBehavior());
                }
            }
        }

        public virtual void Tick(Block caller)
        {
            // Ignore
        }
    }
}