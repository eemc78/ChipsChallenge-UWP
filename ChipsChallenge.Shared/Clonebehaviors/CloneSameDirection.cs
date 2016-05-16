namespace ChipsChallenge.Shared.Clonebehaviors
{
    public class CloneSameDirection : CloneBehavior
    {
        private CloneSameDirection()
        {
        }
        private static CloneSameDirection instance;

        public static CloneSameDirection Instance
        {
            get
            {
                lock (typeof(CloneSameDirection))
                {
                    return instance ?? (instance = new CloneSameDirection());
                }
            }
        }

        public override Block CloneIt(Block original)
        {
            return CloneTo(original, original.Facing);
        }
    }
}