namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Moves = Move.Moves;

    public class BugTickBehavior : IBlockTickBehavior
    {
        private BugTickBehavior()
        {
        }

        private static BugTickBehavior instance;

        public static BugTickBehavior Instance
        {
            get
            {
                lock (typeof(BugTickBehavior))
                {
                    return instance ?? (instance = new BugTickBehavior());
                }
            }
        }

        public virtual void Tick(Block caller)
        {
            Moves m = caller.Facing;
            
            switch (m)
            {
                case Moves.UP:
                    if (!caller.Move(Moves.LEFT))
                    {
                        if (!caller.Move(Moves.UP))
                        {
                            if (!caller.Move(Moves.RIGHT))
                            {
                                caller.Move(Moves.DOWN);
                            }
                        }
                    }
                    break;
                case Moves.LEFT:
                    if (!caller.Move(Moves.DOWN))
                    {
                        if (!caller.Move(Moves.LEFT))
                        {
                            if (!caller.Move(Moves.UP))
                            {
                                caller.Move(Moves.RIGHT);
                            }
                        }
                    }
                    break;
                case Moves.DOWN:
                    if (!caller.Move(Moves.RIGHT))
                    {
                        if (!caller.Move(Moves.DOWN))
                        {
                            if (!caller.Move(Moves.LEFT))
                            {
                                caller.Move(Moves.UP);
                            }
                        }
                    }
                    break;
                case Moves.RIGHT:
                    if (!caller.Move(Moves.UP))
                    {
                        if (!caller.Move(Moves.RIGHT))
                        {
                            if (!caller.Move(Moves.DOWN))
                            {
                                caller.Move(Moves.LEFT);
                            }
                        }
                    }
                    break;
            }
        }
    }
}