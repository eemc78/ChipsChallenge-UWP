namespace ChipsChallenge.Shared.Clonebehaviors
{
    public class CloneBoss : CloneBehavior
    {
        private CloneBoss()
        {
        }
        
        private static CloneBoss instance;

        public static CloneBoss Instance
        {
            get
            {
                lock (typeof(CloneBoss))
                {
                    return instance ?? (instance = new CloneBoss());
                }
            }
        }

        public override Block CloneIt(Block original)
        {
            Block boss = Creatures.Boss;
            if (boss == null)
            {
                throw new CloneNotSupportedException("No boss");
            }

            return CloneTo(original, boss.Facing);
        }
    }
}