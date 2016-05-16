namespace ChipsChallenge.Shared.Trapreleasebehaviors
{
    public class ControllerTrapReleaseBehavior : ITrapReleaseBehavior
    {
        private ControllerTrapReleaseBehavior()
        {
        }
        private static ControllerTrapReleaseBehavior instance;

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