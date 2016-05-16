namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Moves = Move.Moves;

    public class GliderTickBehavior : IBlockTickBehavior
    {
        private GliderTickBehavior()
        {
        }
        private static GliderTickBehavior instance;

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

        private bool move(Block caller, Moves m)
        {
            Moves before = caller.Facing;
            bool ret = caller.Move(m);
            if (!ret)
            {
                caller.Facing = before;
            }
            return ret;
        }

        public virtual void Tick(Block caller)
        {
            Moves m = caller.Facing;
            switch (m)
            {
                case Moves.UP:
                    if (move(caller, Moves.UP) || move(caller, Moves.LEFT) || move(caller, Moves.RIGHT) || move(caller, Moves.DOWN))
                    {
                    }
                    break;
                case Moves.RIGHT:
                    if (move(caller, Moves.RIGHT) || move(caller, Moves.UP) || move(caller, Moves.DOWN) || move(caller, Moves.LEFT))
                    {
                    }
                    break;
                case Moves.LEFT:
                    if (move(caller, Moves.LEFT) || move(caller, Moves.DOWN) || move(caller, Moves.UP) || move(caller, Moves.RIGHT))
                    {
                    }
                    break;
                case Moves.DOWN:
                    if (move(caller, Moves.DOWN) || move(caller, Moves.RIGHT) || move(caller, Moves.LEFT) || move(caller, Moves.UP))
                    {
                    }
                    break;
            }
        }
    }
}