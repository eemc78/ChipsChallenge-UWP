namespace ChipsChallenge.Shared.Destroybehaviors
{
    /// <summary>
    /// Determines how a block will remove itself from the playfield.
    /// </summary>
    public interface IDestroyBehavior
    {
        void Destroy(Block block);
    }
}