namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;
    using Moves = Move.Moves;

    public class IceCornerTo : BlockReaction
    {
        private IceCornerTo()
        {
        }
        private static IceCornerTo instance;

        public static IceCornerTo Instance
        {
            get
            {
                lock (typeof(IceCornerTo))
                {
                    return instance ?? (instance = new IceCornerTo());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
        }

        public override bool canMove(Block moving, Block standing)
        {
            Moves m = standing.Facing;
            switch (moving.Facing)
            {
                case Moves.UP:
                    return m == Moves.UP || m == Moves.LEFT;
                case Moves.DOWN:
                    return m == Moves.DOWN || m == Moves.RIGHT;
                case Moves.LEFT:
                    return m == Moves.UP || m == Moves.RIGHT;
                case Moves.RIGHT:
                    return m == Moves.LEFT || m == Moves.DOWN;
            }
            return false;
        }

        public override Moves? CausesSlip(Block moving, Block standing)
        {
            if (moving.Chip && HasBoots(Boots.ICESKATES))
            {
                return null;
            }
            else
            {
                Moves m = standing.Facing;
                Moves? force = null;
                switch (moving.Facing)
                {
                    case Moves.UP:
                        force = m == Moves.UP ? Moves.RIGHT : Moves.LEFT;
                        break;
                    case Moves.DOWN:
                        force = m == Moves.DOWN ? Moves.LEFT : Moves.RIGHT;
                        break;
                    case Moves.LEFT:
                        force = m == Moves.UP ? Moves.DOWN : Moves.UP;
                        break;
                    case Moves.RIGHT:
                        force = m == Moves.LEFT ? Moves.DOWN : Moves.UP;
                        break;
                }

                return force;
            }
        }
    }
}