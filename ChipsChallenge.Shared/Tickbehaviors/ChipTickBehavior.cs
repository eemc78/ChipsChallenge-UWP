namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Boots = Inventory.Boots;
    using Moves = Move.Moves;

    public class ChipTickBehavior : IBlockTickBehavior
    {
        private static ChipTickBehavior instance;
        private Moves? proposedMove;
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

                if (proposedMove != null)
                {
                    if (caller.OnIce && !Game.Instance.Inventory.HasBoots(Boots.ICESKATES))
                    {
                        proposedMove = null; // Ignore proposed move
                    }
                    else
                    {
                        var couldMove = caller.Move((Moves)proposedMove);

                        if (!couldMove)
                        {
                            SoundPlayer.Instance.Play(Sound.ChipHum); 
                        }

                        proposedMove = null;
                    }

                    if (caller.OnForceFloor)
                    {
                        Game.Instance.Level.GetBlockContainer(caller).MoveTo(caller);
                    }

                    ticksBeforeTurn = 12;
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

        public void MoveUp()
        {
            proposedMove = Moves.UP;
        }

        public void MoveDown()
        {
            proposedMove = Moves.DOWN;
        }

        public void MoveLeft()
        {
            proposedMove = Moves.LEFT;
        }

        public void MoveRight()
        {
            proposedMove = Moves.RIGHT;
        }
    }
}