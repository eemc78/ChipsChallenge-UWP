namespace ChipsChallenge.Features.Game.UserInput
{
    using Shared.Gui;

    using Windows.System;
    using Windows.UI.Core;

    public class KeyBoardInput : UserInputBase
    {
        private bool isCtrlKeyPressed;

        public void WindowKeyUp(CoreWindow sender, KeyEventArgs args)
        {
            if (args.VirtualKey == VirtualKey.Control)
            {
                isCtrlKeyPressed = false;
            }
        }

        public void WindowKeyDown(CoreWindow sender, KeyEventArgs args)
        {
            if (args.VirtualKey == VirtualKey.Control)
            {
                isCtrlKeyPressed = true;
            }

            if (isCtrlKeyPressed)
            {
                switch (args.VirtualKey)
                {
                    case VirtualKey.R:
                        OnUserInputReceived(UserInput.RestartLevel);
                        break;
                    case VirtualKey.N:
                        OnUserInputReceived(UserInput.NextLevel);
                        break;
                    case VirtualKey.P:
                        OnUserInputReceived(UserInput.PreviousLevel);
                        break;
                }
            }
            else
            {
                switch (args.VirtualKey)
                {
                    case VirtualKey.P:
                        OnUserInputReceived(UserInput.TogglePause);
                        break;
                    case VirtualKey.Up:
                    case VirtualKey.W:
                        OnUserInputReceived(UserInput.MoveUp);
                        break;
                    case VirtualKey.Down:
                    case VirtualKey.S:
                        OnUserInputReceived(UserInput.MoveDown);
                        break;
                    case VirtualKey.Left:
                    case VirtualKey.A:
                        OnUserInputReceived(UserInput.MoveLeft);
                        break;
                    case VirtualKey.Right:
                    case VirtualKey.D:
                        OnUserInputReceived(UserInput.MoveRight);
                        break;
                }
            }
        }
    }
}