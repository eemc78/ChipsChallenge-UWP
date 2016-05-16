namespace ChipsChallenge.Shared.Trapreleasebehaviors
{
    public class DefaultTrapReleaseBehavior : ITrapReleaseBehavior
    {
        private DefaultTrapReleaseBehavior()
        {
        }
        private static DefaultTrapReleaseBehavior instance;

        public static DefaultTrapReleaseBehavior Instance
        {
            get
            {
                lock (typeof(DefaultTrapReleaseBehavior))
                {
                    return instance ?? (instance = new DefaultTrapReleaseBehavior());
                }
            }
        }

        public virtual void ReleaseFromTrap(Block trapped)
        {
            trapped.Trapped = false;
        }
    }
}