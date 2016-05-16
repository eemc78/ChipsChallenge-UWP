namespace ChipsChallenge.Shared.Buttonbehaviors
{
    using CanMove = Blockreactions.CanMove;
    using CannotMove = Blockreactions.CannotMove;

    public class TrapBehavior : IButtonBehavior
    {
        private TrapBehavior()
        {
        }

        private static TrapBehavior instance;

        public static TrapBehavior Instance
        {
            get
            {
                lock (typeof(TrapBehavior))
                {
                    return instance ?? (instance = new TrapBehavior());
                }
            }
        }

        public virtual void ButtonDown(Block listener, Block button)
        {
            if (button.getType() == Block.Type.BROWNBUTTON)
            {
                listener.FromReaction = CanMove.Instance;
                Block trapped = listener.BlockContainer.Upper;
                trapped?.ReleaseFromTrap();
            }
        }

        public virtual void ButtonUp(Block listener, Block button)
        {
            if (button.getType() == Block.Type.BROWNBUTTON)
            {
                listener.FromReaction = CannotMove.Instance;
            }
        }
    }
}