namespace ChipsChallenge.Shared.Tickbehaviors
{
    public class NullTickBehavior : IBlockTickBehavior
    {
        private NullTickBehavior()
        {
        }

        private static NullTickBehavior _mInstance;

        public static NullTickBehavior Instance
        {
            get
            {
                lock (typeof(NullTickBehavior))
                {
                    return _mInstance ?? (_mInstance = new NullTickBehavior());
                }
            }
        }

        public virtual void Tick(Block caller)
        {
            // Ignore
        }
    }
}