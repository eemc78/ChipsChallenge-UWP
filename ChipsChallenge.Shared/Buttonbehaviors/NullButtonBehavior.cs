namespace ChipsChallenge.Shared.Buttonbehaviors
{
    public class NullButtonBehavior : IButtonBehavior
    {
        private static NullButtonBehavior instance;

        private NullButtonBehavior()
        {
        }

        public static NullButtonBehavior Instance
        {
            get
            {
                lock (typeof(NullButtonBehavior))
                {
                    return instance ?? (instance = new NullButtonBehavior());
                }
            }
        }

        public virtual void ButtonDown(Block moving, Block button)
        {
        }

        public virtual void ButtonUp(Block listener, Block button)
        {
        }
    }
}