namespace ChipsChallenge.Shared.Clonebehaviors
{
    public class CloneSameDirection : CloneBehavior
    {
        private static CloneSameDirection instance;

        private CloneSameDirection()
        {
        }

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