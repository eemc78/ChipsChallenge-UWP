namespace ChipsChallenge.Shared.Blockreactions
{
    public class TeleportTo : SlipReaction
    {

        private TeleportTo()
        {
        }

        private static TeleportTo instance;

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
            BlockContainer goal;
            Point remote;
            Point moveTo;
            do
            {
                remote = Teleports.Next(currentStart);
                moveTo = new Point(remote.X, remote.Y);
                Move.UpdatePoint(ref moveTo, moving.Facing);
                goal = Level().GetBlockContainer(moveTo.X, moveTo.Y);
                currentStart = remote;
            } while (!goal.CanMoveTo(moving) && !currentStart.Equals(origin));
            if (remote.Equals(origin))
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