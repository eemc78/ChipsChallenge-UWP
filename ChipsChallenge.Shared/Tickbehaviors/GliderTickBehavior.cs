namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Moves = Move.Moves;

    public class GliderTickBehavior : IBlockTickBehavior
    {
        private static GliderTickBehavior instance;

        private GliderTickBehavior()
        {
        }

        public static GliderTickBehavior Instance
        {
            get
            {
                lock (typeof(GliderTickBehavior))
                {
                    return instance ?? (instance = new GliderTickBehavior());
                }
            }
        }

        public virtual void Tick(Block caller)
        {
            Moves m = caller.Facing;
            switch (m)
            {
                case Moves.UP:
                    if (Move(caller, Moves.UP) || Move(caller, Moves.LEFT) || Move(caller, Moves.RIGHT) || Move(caller, Moves.DOWN))
                    {
                    }

                    break;
                case Moves.RIGHT:
                    if (Move(caller, Moves.RIGHT) || Move(caller, Moves.UP) || Move(caller, Moves.DOWN) || Move(caller, Moves.LEFT))
                    {
                    }

                    break;
                case Moves.LEFT:
                    if (Move(caller, Moves.LEFT) || Move(caller, Moves.DOWN) || Move(caller, Moves.UP) || Move(caller, Moves.RIGHT))
                    {
                    }

                    break;
                case Moves.DOWN:
                    if (Move(caller, Moves.DOWN) || Move(caller, Moves.RIGHT) || Move(caller, Moves.LEFT) || Move(caller, Moves.UP))
                    {
                    }

                    break;
            }
        }

        private bool Move(Block caller, Moves m)
        {
            Moves before = caller.Facing;
            bool ret = caller.Move(m);
            if (!ret)
            {
                caller.Facing = before;
            }

            return ret;
        }
    }
}