namespace ChipsChallenge.Shared.Blockreactions
{
    public class BlueWallRealTo : NoSlipReaction
    {
        private BlueWallRealTo()
        {
        }
        private static BlueWallRealTo instance;

        public static BlueWallRealTo Instance
        {
            get
            {
                lock (typeof(BlueWallRealTo))
                {
                    return instance ?? (instance = new BlueWallRealTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            Move.Moves facing = moving.Facing;
            moving.Move(Move.Reverse(facing));
            moving.Facing = facing;
            standing.Replace(CreateBlock(Block.Type.WALL));
            Sound().Play(Shared.Sound.ChipHum);
        }

        public override bool canMove(Block moving, Block standing)
        {
            return moving.Chip;
        }
    }
}