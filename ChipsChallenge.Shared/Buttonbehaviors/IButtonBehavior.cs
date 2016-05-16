namespace ChipsChallenge.Shared.Buttonbehaviors
{
    public interface IButtonBehavior
    {
        void ButtonDown(Block listener, Block button);

        void ButtonUp(Block listener, Block button);
    }
}