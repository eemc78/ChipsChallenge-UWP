namespace ChipsChallenge.Shared.Destroybehaviors
{
    public class ToggleWallDestroyBehavior : IDestroyBehavior
    {
        private ToggleWallDestroyBehavior()
        {
        }
        
        private static ToggleWallDestroyBehavior instance;

        public static ToggleWallDestroyBehavior Instance
        {
            get
            {
                lock (typeof(ToggleWallDestroyBehavior))
                {
                    return instance ?? (instance = new ToggleWallDestroyBehavior());
                }
            }
        }

        public virtual void Destroy(Block block)
        {
            Buttons.RemoveGreenButtonsListener(block);
        }
    }
}