namespace ChipsChallenge.Features.Game.UserInput
{
    using Shared.Gui;

    public abstract class UserInputBase
    {
        public event InputHandler UserInputReceived;

        public delegate void InputHandler(object sender, UserInputArgs args);

        protected void OnUserInputReceived(UserInput userInput)
        {
            var userInputArgs = new UserInputArgs { Input = userInput };

            InputHandler handler = UserInputReceived;
            handler?.Invoke(this, userInputArgs);
        }
    }
}