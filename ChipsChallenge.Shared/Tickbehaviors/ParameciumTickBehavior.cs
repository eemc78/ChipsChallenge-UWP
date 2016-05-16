namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Moves = Move.Moves;

    public class ParameciumTickBehavior : IBlockTickBehavior
    {
        private ParameciumTickBehavior()
        {
        }

        private static ParameciumTickBehavior instance;

        public static ParameciumTickBehavior Instance
        {
            get
            {
                lock (typeof(ParameciumTickBehavior))
                {
                    return instance ?? (instance = new ParameciumTickBehavior());
                }
            }
        }

        public virtual void Tick(Block caller)
        {
            Moves m = caller.Facing;

            switch (m)
            {
                case Moves.UP:
                    if (!caller.Move(Moves.RIGHT))
                    {
                        if (!caller.Move(Moves.UP))
                        {
                            if (!caller.Move(Moves.LEFT))
                            {
                                caller.Move(Moves.DOWN);
                            }
                        }
                    }

                    break;
                case Moves.LEFT:
                    if (!caller.Move(Moves.UP))
                    {
                        if (!caller.Move(Moves.LEFT))
                        {
                            if (!caller.Move(Moves.DOWN))
                            {
                                caller.Move(Moves.RIGHT);
                            }
                        }
                    }

                    break;
                case Moves.DOWN:
                    if (!caller.Move(Moves.LEFT))
                    {
                        if (!caller.Move(Moves.DOWN))
                        {
                            if (!caller.Move(Moves.RIGHT))
                            {
                                caller.Move(Moves.UP);
                            }
                        }
                    }

                    break;
                case Moves.RIGHT:
                    if (!caller.Move(Moves.DOWN))
                    {
                        if (!caller.Move(Moves.RIGHT))
                        {
                            if (!caller.Move(Moves.UP))
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