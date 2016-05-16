namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Moves = Move.Moves;

    public class BlobTickBehavior : IBlockTickBehavior
    {
        private BlobTickBehavior()
        {
        }
        
        private static BlobTickBehavior instance;

        public static BlobTickBehavior Instance
        {
            get
            {
                lock (typeof(BlobTickBehavior))
                {
                    return instance ?? (instance = new BlobTickBehavior());
                }
            }
        }

        public virtual void Tick(Block caller)
        {
            var couldUpdate = false;
            var initialDirection = Utils.Random.Next(4);

            for (var i = 0; i < 4; i++)
            {
                switch (initialDirection)
                {
                    case 0:
                        caller.Facing = Moves.UP;
                        couldUpdate = caller.Move(Moves.UP);
                        break;
                    case 1:
                        caller.Facing = Moves.DOWN;
                        couldUpdate = caller.Move(Moves.DOWN);
                        break;
                    case 2:
                        caller.Facing = Moves.LEFT;
                        couldUpdate = caller.Move(Moves.LEFT);
                        break;
                    case 3:
                        caller.Facing = Moves.RIGHT;
                        couldUpdate = caller.Move(Moves.RIGHT);
                        break;
                }

                if (initialDirection == 3)
                {
                    initialDirection = 0;
                }
                else
                {
                    initialDirection++;
                }

                if (couldUpdate)
                {
                    break;
                }
            }
        }
    }
}