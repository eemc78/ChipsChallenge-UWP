namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Moves = Move.Moves;

    public class FireballTickBehavior : IBlockTickBehavior
    {
        private static FireballTickBehavior instance;

        private FireballTickBehavior()
        {
        }

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

        public virtual void Tick(Block caller)
        {
            Moves m = caller.Facing;
            switch (m)
            {
                case Moves.UP:
                    if (Move(caller, Moves.UP) || Move(caller, Moves.RIGHT) || Move(caller, Moves.LEFT) || Move(caller, Moves.DOWN))
                    {
                    }

                    break;
                case Moves.RIGHT:
                    if (Move(caller, Moves.RIGHT) || Move(caller, Moves.DOWN) || Move(caller, Moves.UP) || Move(caller, Moves.LEFT))
                    {
                    }

                    break;
                case Moves.LEFT:
                    if (Move(caller, Moves.LEFT) || Move(caller, Moves.UP) || Move(caller, Moves.DOWN) || Move(caller, Moves.RIGHT))
                    {
                    }

                    break;
                case Moves.DOWN:
                    if (Move(caller, Moves.DOWN) || Move(caller, Moves.LEFT) || Move(caller, Moves.RIGHT) || Move(caller, Moves.UP))
                    {
                    }

                    break;
            }
        }

        private static bool Move(Block caller, Moves m)
        {
            return caller.Move(m);
        }
    }
}