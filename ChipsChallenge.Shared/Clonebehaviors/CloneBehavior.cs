namespace ChipsChallenge.Shared.Clonebehaviors
{
    using Moves = Move.Moves;
    using Type = Block.Type;

    public abstract class CloneBehavior
    {
        public abstract Block CloneIt(Block original);

        protected internal Block CloneTo(Block original, Moves direction)
        {
            Game gameInstance = Game.Instance;
            Block clone = gameInstance.BlockFactory.Get(original.getType(), direction);
            Point point = new Point(original.Point.X, original.Point.Y);

            Move.UpdatePoint(ref point, direction);
            try
            {
                switch (clone.getType())
                {
                    case Type.BLOCK:
                    case Type.BLOB:
                    case Type.TEETH:
                        if (gameInstance.Level.GetBlockContainer(point.X, point.Y).CanMoveTo(clone))
                        {
                            gameInstance.Level.AddBlock(point.X, point.Y, clone, 2);
                            gameInstance.Level.MoveBlock(clone, null, true, true);
                        }
                        else
                        {
                            //throw new CloneNotSupportedException();
                        }
                        break;
                    default:
                        gameInstance.AddBlockDelay(clone, point, 2);
                        break;
                }
            }
            catch (BlockContainerFullException)
            {
            }

            return clone;
        }
    }
}