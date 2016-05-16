namespace ChipsChallenge.Shared.Buttonbehaviors
{
    public class NullButtonBehavior : IButtonBehavior
    {
        private NullButtonBehavior()
        {
        }

        private static NullButtonBehavior instance;

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