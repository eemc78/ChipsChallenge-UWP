namespace ChipsChallenge.Shared.Clonebehaviors
{
    public class NullCloneBehavior : CloneBehavior
    {
        private NullCloneBehavior()
        {
        }
        private static NullCloneBehavior instance;

        public static NullCloneBehavior Instance
        {
            get
            {
                lock (typeof(NullCloneBehavior))
                {
                    return instance ?? (instance = new NullCloneBehavior());
                }
            }
        }

        public override Block CloneIt(Block original)
        {
            throw new CloneNotSupportedException();
        }
    }
}