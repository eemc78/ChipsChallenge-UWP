namespace ChipsChallenge.Shared.Blockreactions
{
    public class SocketTo : NoSlipReaction
    {
        private SocketTo()
        {
        }

        private static SocketTo instance;

        public static SocketTo Instance
        {
            get
            {
                lock (typeof(SocketTo))
                {
                    if (instance == null)
                    {
                        instance = new SocketTo();
                    }
                    return instance;
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            standing.Destroy();
            Sound().Play(Shared.Sound.Socket);
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip && Level().NumChipsNeeded <= 0;
        }
    }
}