namespace ChipsChallenge.Shared.Tickbehaviors
{
    using Buttonbehaviors;

    public class TankBehavior : IButtonBehavior, IBlockTickBehavior
    {
        private bool isMoving = true;

        public virtual void Tick(Block caller)
        {
            if (isMoving)
            {
                if (!caller.Move(caller.Facing))
                {
                    isMoving = false;
                }
            }
        }

        public virtual void ButtonDown(Block listener, Block button)
        {
            listener.Facing = Move.Reverse(listener.Facing);
            isMoving = true;
        }

        public virtual void ButtonUp(Block listener, Block button)
        {
        }
    }
}