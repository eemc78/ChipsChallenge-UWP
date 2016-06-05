namespace ChipsChallenge.Shared.Blockreactions
{
    public class ComputerChipTo : NoSlipReaction
    {
        private static ComputerChipTo instance;

        private ComputerChipTo()
        {
        }

        public static ComputerChipTo Instance
        {
            get
            {
                lock (typeof(ComputerChipTo))
                {
                    return instance ?? (instance = new ComputerChipTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            if (moving.Chip)
            {
                Game().TakeChip();
                standing.Replace(CreateBlock(Block.Type.FLOOR));
                Sound().Play(Shared.Sound.TakeChip);
            }
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip;
        }
    }
}