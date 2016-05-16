namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Moves = Move.Moves;

    public class WalkerTickBehavior : IBlockTickBehavior
    {
        private WalkerTickBehavior()
        {
        }
        private static WalkerTickBehavior instance;

        public static WalkerTickBehavior Instance
        {
            get
            {
                lock (typeof(WalkerTickBehavior))
                {
                    return instance ?? (instance = new WalkerTickBehavior());
                }
            }
        }

        private bool Move(Block caller, Moves m)
        {
            caller.Facing = m;
            return caller.Move(m);
        }

        public virtual void Tick(Block caller)
        {
            Moves m = caller.Facing;
            if (!caller.Move(m))
            {
                int dir = Utils.Random.Next(4);
                for (int i = 0; i < 2; i++)
                {
                    switch (dir)
                    {
                        case 0:
                            if (Move(caller, Moves.UP))
                            {
                                goto outerBreak;
                            }
                            else
                            {
                                caller.Facing = m;
                            }
                            goto case 1;
                        case 1:
                            if (Move(caller, Moves.DOWN))
                            {
                                goto outerBreak;
                            }
                            else
                            {
                                caller.Facing = m;
                            }
                            goto case 2;
                        case 2:
                            if (Move(caller, Moves.LEFT))
                            {
                                goto outerBreak;
                            }
                            else
                            {
                                caller.Facing = m;
                            }
                            goto case 3;
                        case 3:
                            if (Move(caller, Moves.RIGHT))
                            {
                                goto outerBreak;
                            }
                            else
                            {
                                dir = 0;
                                caller.Facing = m;
                            }
                        break;
                    }
                }
                outerBreak:;
            }
        }
    }
}