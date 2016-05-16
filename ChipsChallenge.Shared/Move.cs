// ReSharper disable InconsistentNaming
namespace ChipsChallenge.Shared
{
    public class Move
    {
        public enum Moves
        {
            UP,
            DOWN,
            LEFT,
            RIGHT
        }

        public static void UpdatePoint(ref Point p, Moves m)
        {
            switch (m)
            {
                case Moves.UP:
                    p.Y--;
                    break;
                case Moves.DOWN:
                    p.Y++;
                    break;
                case Moves.LEFT:
                    p.X--;
                    break;
                case Moves.RIGHT:
                    p.X++;
                    break;
            }
        }

        public static Moves Random
        {
            get
            {
                int random = Utils.Random.Next(4);

                switch (random)
                {
                    case 0:
                        return Moves.UP;
                    case 1:
                        return Moves.DOWN;
                    case 2:
                        return Moves.LEFT;
                    case 3:
                        return Moves.RIGHT;
                    default:
                        return Moves.UP;
                }
            }
        }

        public static Moves Reverse(Moves m)
        {
            switch (m)
            {
                case Moves.UP:
                    return Moves.DOWN;
                case Moves.DOWN:
                    return Moves.UP;
                case Moves.LEFT:
                    return Moves.RIGHT;
                case Moves.RIGHT:
                    return Moves.LEFT;
                default:
                    return Moves.UP;
            }
        }
    }
}