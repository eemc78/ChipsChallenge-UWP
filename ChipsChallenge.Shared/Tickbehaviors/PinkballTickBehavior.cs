namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Moves = Move.Moves;

    public class PinkballTickBehavior : IBlockTickBehavior
    {
        private static PinkballTickBehavior instance;

        private PinkballTickBehavior()
        {
        }

        public static PinkballTickBehavior Instance
        {
            get
            {
                lock (typeof(PinkballTickBehavior))
                {
                    return instance ?? (instance = new PinkballTickBehavior());
                }
            }
        }

        public virtual void Tick(Block caller)
        {
            if (caller.OnCloner)
            {
                return;
            }

            Moves moves = caller.Facing;
            if (!caller.Move(moves))
            {
                moves = Move.Reverse(moves);
                caller.Facing = moves;
                caller.Move(moves);
            }
        }
    }
}