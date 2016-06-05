namespace ChipsChallenge.Shared.Blockreactions
{
    public class TeleportTo : SlipReaction
    {
        private static TeleportTo instance;

        private TeleportTo()
        {
        }

        public static TeleportTo Instance
        {
            get
            {
                lock (typeof(TeleportTo))
                {
                    return instance ?? (instance = new TeleportTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            Point origin = Level().GetPoint(standing);
            Point currentStart = origin;

            var remote = Teleports.Next(currentStart);
            var moveTo = new Point(remote.X, remote.Y);
            Move.UpdatePoint(ref moveTo, moving.Facing);
            var goal = Level().GetBlockContainer(moveTo.X, moveTo.Y);
            currentStart = remote;

            if (!goal.CanMoveTo(moving) && !currentStart.Equals(origin))
            {
                // destination blocked
                // TODO: Play ChipHum. If moving is block, do not move it away from teleport.
            }
            else if (remote.Equals(origin))
            {
                // Totally blocked
            }
            else
            {
                Level().Teleport(moving, remote);
                if (moving.Chip)
                {
                    Sound().Play(Shared.Sound.Teleport);
                }
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}