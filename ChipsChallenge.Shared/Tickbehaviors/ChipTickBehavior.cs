namespace ChipsChallenge.Shared.Tickbehaviors
{
    using System;
    using System.Collections.Generic;
    using Boots = Inventory.Boots;
    using Moves = Move.Moves;

    public class ChipTickBehavior : IBlockTickBehavior
    {
        private static ChipTickBehavior instance;
        private readonly LinkedList<Moves> proposedMoves = new LinkedList<Moves>();
        private int ticksBeforeTurn;
        private int chipTicks;

        private ChipTickBehavior()
        {
        }

        public static ChipTickBehavior Instance
        {
            get
            {
                lock (typeof(ChipTickBehavior))
                {
                    return instance ?? (instance = new ChipTickBehavior());
                }
            }
        }

        public virtual void Tick(Block caller)
        {
            chipTicks = (chipTicks + 1) % Game.SpeedFrac;
            if (chipTicks == 0 || caller.Forced)
            {
                caller.Forced = false;
                lock (proposedMoves)
                {
                    if (proposedMoves.Count > 0)
                    {
                        if (caller.OnIce && !Game.Instance.Inventory.HasBoots(Boots.ICESKATES))
                        {
                            proposedMoves.RemoveFirst(); // Ignore proposed move
                        }
                        else if (proposedMoves.Count > 0)
                        {
                            var move = proposedMoves.First.Value;
                            var couldMove = caller.Move(move);

                            if (!couldMove)
                            {
                                SoundPlayer.Instance.Play(Sound.ChipHum); 
                            }

                            proposedMoves.RemoveFirst();
                        }
                        else
                        {
                            proposedMoves.Clear();
                            SoundPlayer.Instance.Play(Sound.ChipHum);
                        }

                        if (caller.OnForceFloor)
                        {
                            Game.Instance.Level.GetBlockContainer(caller).MoveTo(caller);
                        }

                        ticksBeforeTurn = 12;
                    }
                }
            }

            if (!caller.OnIce && ticksBeforeTurn > 0)
            {
                ticksBeforeTurn--;
                if (ticksBeforeTurn == 0)
                {
                    caller.Facing = Moves.DOWN;
                }
            }
        }

        public virtual void MoveTo(Point goal)
        {
            lock (proposedMoves)
            {
                proposedMoves.Clear();
                var chip = Game.Instance.Level.FindChip();
                var startPoint = new Point(chip.X, chip.Y);
                while (!startPoint.Equals(goal))
                {
                    int dx = goal.X - startPoint.X;
                    int dy = goal.Y - startPoint.Y;
                    int pdx = Math.Abs(dx);
                    int pdy = Math.Abs(dy);
                    Moves proposedMove;
                    if (pdx > pdy)
                    {
                        if (dx > 0)
                        {
                            proposedMove = Moves.RIGHT;
                        }
                        else
                        {
                            proposedMove = Moves.LEFT;
                        }
                    }
                    else
                    {
                        if (dy > 0)
                        {
                            proposedMove = Moves.DOWN;
                        }
                        else
                        {
                            proposedMove = Moves.UP;
                        }
                    }

                    Move.UpdatePoint(ref startPoint, proposedMove);
                    proposedMoves.AddLast(proposedMove);
                }
            }
        }

        public void MoveUp()
        {
            lock (proposedMoves)
            {
                proposedMoves.Clear();
                proposedMoves.AddLast(Moves.UP);
            }
        }

        public void MoveDown()
        {
            lock (proposedMoves)
            {
                proposedMoves.Clear();
                proposedMoves.AddLast(Moves.DOWN);
            }
        }

        public void MoveLeft()
        {
            lock (proposedMoves)
            {
                proposedMoves.Clear();
                proposedMoves.AddLast(Moves.LEFT);
            }
        }

        public void MoveRight()
        {
            lock (proposedMoves)
            {
                proposedMoves.Clear();
                proposedMoves.AddLast(Moves.RIGHT);
            }
        }
    }
}