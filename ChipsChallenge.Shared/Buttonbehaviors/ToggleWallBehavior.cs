namespace ChipsChallenge.Shared.Buttonbehaviors
{
    /// <summary>
    /// If the button was green and the listener is a toggle wall. Toggle it.
    /// </summary>
    public class ToggleWallBehavior : IButtonBehavior
    {
        private static ToggleWallBehavior instance;

        private ToggleWallBehavior()
        {
        }

        public static ToggleWallBehavior Instance
        {
            get
            {
                lock (typeof(ToggleWallBehavior))
                {
                    return instance ?? (instance = new ToggleWallBehavior());
                }
            }
        }

        public virtual void ButtonDown(Block listener, Block button)
        {
            Game.Instance.MoveOccured(listener.Point);
            if (listener.IsA(Block.Type.TOGGLEWALLOPEN))
            {
                listener.Replace(Block.Create(Block.Type.TOGGLEWALLCLOSED));
            }
            else if (listener.IsA(Block.Type.TOGGLEWALLCLOSED))
            {
                listener.Replace(Block.Create(Block.Type.TOGGLEWALLOPEN));
            }
        }

        public virtual void ButtonUp(Block listener, Block button)
        {
        }
    }
}