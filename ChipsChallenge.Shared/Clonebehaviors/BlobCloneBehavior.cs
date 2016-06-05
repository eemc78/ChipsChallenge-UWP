namespace ChipsChallenge.Shared.Clonebehaviors
{
    using Moves = Move.Moves;

    public class BlobCloneBehavior : CloneBehavior
    {
        private static BlobCloneBehavior instance;

        private BlobCloneBehavior()
        {
        }

        public static BlobCloneBehavior Instance
        {
            get
            {
                lock (typeof(BlobCloneBehavior))
                {
                    return instance ?? (instance = new BlobCloneBehavior());
                }
            }
        }

        public override Block CloneIt(Block original)
        {
            int dir = Utils.Random.Next(4);
            Block clone = null;
            for (int i = 0; i < 2; i++)
            {
                switch (dir)
                {
                    case 0:
                        if ((clone = CloneTo(original, Moves.UP)) != null)
                        {
                            goto outerBreak;
                        }
                        break;
                    case 1:
                        if ((clone = CloneTo(original, Moves.DOWN)) != null)
                        {
                            goto outerBreak;
                        }
                        break;
                    case 2:
                        if ((clone = CloneTo(original, Moves.LEFT)) != null)
                        {
                            goto outerBreak;
                        }
                        break;
                    case 3:
                        if ((clone = CloneTo(original, Moves.RIGHT)) != null)
                        {
                            goto outerBreak;
                        }
                        else
                        {
                            dir = 0;
                        }
                    break;
                }
            }
            outerBreak:
            return clone;
        }
    }
}