using System;

namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Moves = Move.Moves;

    public class TeethTickBehavior : IBlockTickBehavior
    {
        private TeethTickBehavior()
        {
        }
        private static TeethTickBehavior instance;

        public static TeethTickBehavior Instance
        {
            get
            {
                lock (typeof(TeethTickBehavior))
                {
                    return instance ?? (instance = new TeethTickBehavior());
                }
            }
        }

        public virtual Point FindChip()
        {
            return Game.Instance.Level.FindChip();
        }

        public virtual Point FindMe(Block caller)
        {
            return Game.Instance.Level.GetPoint(caller);
        }

        public virtual bool InUse(Block caller)
        {
            return Game.Instance.Level.Contains(caller);
        }

        public virtual void Tick(Block caller)
        {
            if (!InUse(caller))
            {
                return;
            }
            Point chip = FindChip();
            Point me = FindMe(caller);
            int dx = chip.X - me.X;
            int dy = chip.Y - me.Y;
            int pdx = Math.Abs(dx);
            int pdy = Math.Abs(dy);
            Moves xDirection = dx > 0 ? Moves.RIGHT : Moves.LEFT;
            Moves yDirection = dy > 0 ? Moves.DOWN : Moves.UP;
            if (pdx > pdy)
            {
                if (caller.CanMove(xDirection))
                {
                    caller.Move(xDirection);
                }
                else if (pdy > 0 && caller.CanMove(yDirection))
                {
                    caller.Move(yDirection);
                }
            }
            else
            {
                if (caller.CanMove(yDirection))
                {
                    caller.Move(yDirection);
                }
                else if (pdx > 0 && caller.CanMove(xDirection))
                {
                    caller.Move(xDirection);
                }
            }
        }
    }
}