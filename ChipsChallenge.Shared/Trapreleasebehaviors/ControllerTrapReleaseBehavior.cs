namespace ChipsChallenge.Shared.Trapreleasebehaviors
{
    public class ControllerTrapReleaseBehavior : ITrapReleaseBehavior
    {
        private static ControllerTrapReleaseBehavior instance;

        private ControllerTrapReleaseBehavior()
        {
        }

        public static ControllerTrapReleaseBehavior Instance
        {
            get
            {
                lock (typeof(ControllerTrapReleaseBehavior))
                {
                    return instance ?? (instance = new ControllerTrapReleaseBehavior());
                }
            }
        }

        public virtual void ReleaseFromTrap(Block trapped)
        {
            Block controller = Creatures.GetController(trapped);
            if (controller != null)
            {
                trapped.Facing = controller.Facing;
                trapped.Trapped = false;
            }
        }
    }
}