namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Moves = Move.Moves;

    public class FireballTickBehavior : IBlockTickBehavior
    {
        private FireballTickBehavior()
        {
        }
        private static FireballTickBehavior instance;

        public static FireballTickBehavior Instance
        {
            get
            {
                lock (typeof(FireballTickBehavior))
                {
                    return instance ?? (instance = new FireballTickBehavior());
                }
            }
        }

        private bool move(Block caller, Moves m)
        {
            return caller.Move(m);
        }

        public virtual void Tick(Block caller)
        {
            Moves m = caller.Facing;
            switch (m)
            {
                case Moves.UP:
                    if (move(caller, Moves.UP) || move(caller, Moves.RIGHT) || move(caller, Moves.LEFT) || move(caller, Moves.DOWN))
                    {
                    }
                    break;
                case Moves.RIGHT:
                    if (move(caller, Moves.RIGHT) || move(caller, Moves.DOWN) || move(caller, Moves.UP) || move(caller, Moves.LEFT))
                    {
                    }
                    break;
                case Moves.LEFT:
                    if (move(caller, Moves.LEFT) || move(caller, Moves.UP) || move(caller, Moves.DOWN) || move(caller, Moves.RIGHT))
                    {
                    }
                    break;
                case Moves.DOWN:
                    if (move(caller, Moves.DOWN) || move(caller, Moves.LEFT) || move(caller, Moves.RIGHT) || move(caller, Moves.UP))
                    {
                    }
                    break;
            }
        }
    }
}