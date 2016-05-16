namespace ChipsChallenge.Shared.Blockreactions
{
    using Moves = Move.Moves;

    /// <summary>
    /// Defines whether a block can move, and what the side effects are.
    /// </summary>
    public abstract class BlockReaction
    {
        public Block CreateBlock(Block.Type blockType)
        {
            return Shared.Game.Instance.BlockFactory.Get(blockType);
        }

        public void TakeKey(Inventory.Key key)
        {
            Shared.Game.Instance.Inventory.TakeKey(key);
            Sound().Play(Shared.Sound.TakeItem);
        }

        public bool HasKey(Inventory.Key key)
        {
            return Shared.Game.Instance.Inventory.HasKey(key);
        }

        public void UseKey(Inventory.Key key)
        {
            Shared.Game.Instance.Inventory.UseKey(key);
            Sound().Play(Shared.Sound.Door);
        }

        public void TakeBoots(Inventory.Boots b)
        {
            Shared.Game.Instance.Inventory.TakeBoots(b);
            Sound().Play(Shared.Sound.TakeItem);
        }

        public bool HasBoots(Inventory.Boots b)
        {
            return Shared.Game.Instance.Inventory.HasBoots(b);
        }

        public void Die(string message)
        {
            Shared.Game.Instance.Die(message, Shared.Sound.Die);
        }

        public Game Game()
        {
            return Shared.Game.Instance;
        }

        public SoundPlayer Sound()
        {
            return SoundPlayer.Instance;
        }

        public GameLevel Level()
        {
            return Shared.Game.Instance.Level;
        }

        public Inventory Inventory()
        {
            return Shared.Game.Instance.Inventory;
        }

        public abstract void React(Block moving, Block standing);

        public abstract bool canMove(Block moving, Block standing);

        public abstract Moves? CausesSlip(Block moving, Block standing);
    }
}