namespace ChipsChallenge.Features.Game.UserInput
{
    using System;
    using System.Threading;

    using Shared.Gui;

    using Windows.System;
    using Windows.UI.Core;

    public class KeyBoardInput : UserInputBase
    {
        private readonly Timer repeatTimer;
        private UserInput repeatMoveRequested = UserInput.None;
        private bool isCtrlKeyPressed;

        public KeyBoardInput()
        {
            repeatTimer = new Timer(RaiseRepeateMoveEvent, null, Timeout.InfiniteTimeSpan, Timeout.InfiniteTimeSpan);
        }

        public void WindowKeyUp(CoreWindow sender, KeyEventArgs args)
        {
            if (args.VirtualKey == VirtualKey.Control)
            {
                isCtrlKeyPressed = false;
            }
            else
            {
                switch (args.VirtualKey)
                {
                    case VirtualKey.Up:
                    case VirtualKey.W:
                        ProcessKeyUpEvent(UserInput.MoveUp);
                        break;
                    case VirtualKey.Down:
                    case VirtualKey.S:
                        ProcessKeyUpEvent(UserInput.MoveDown);
                        break;
                    case VirtualKey.Left:
                    case VirtualKey.A:
                        ProcessKeyUpEvent(UserInput.MoveLeft);
                        break;
                    case VirtualKey.Right:
                    case VirtualKey.D:
                        ProcessKeyUpEvent(UserInput.MoveRight);
                        break;
                }
            }
        }

        public void WindowKeyDown(CoreWindow sender, KeyEventArgs args)
        {
            if (args.KeyStatus.RepeatCount > 1)
            {
                // Ignore key repeats...let the timer handle that
                return;
            }

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
                        ProcessKeyDownEvent(UserInput.MoveUp);
                        break;
                    case VirtualKey.Down:
                    case VirtualKey.S:
                        ProcessKeyDownEvent(UserInput.MoveDown);
                        break;
                    case VirtualKey.Left:
                    case VirtualKey.A:
                        ProcessKeyDownEvent(UserInput.MoveLeft);
                        break;
                    case VirtualKey.Right:
                    case VirtualKey.D:
                        ProcessKeyDownEvent(UserInput.MoveRight);
                        break;
                }

                StartRepeatTimer();
            }
        }

        public void StopUserInput()
        {
            StopRepeatTimer();
            repeatMoveRequested = UserInput.None;
        }

        private void ProcessKeyDownEvent(UserInput input)
        {
            repeatMoveRequested = input;
            OnUserInputReceived(input);
        }

        private void ProcessKeyUpEvent(UserInput input)
        {
            if (repeatMoveRequested == input)
            {
                repeatMoveRequested = UserInput.None;
            }
        }

        private void RaiseRepeateMoveEvent(object o)
        {
            if (repeatMoveRequested == UserInput.None)
            {
                OnUserInputReceived(UserInput.None);
                StopRepeatTimer();
            }
            else
            {
                switch (repeatMoveRequested)
                {
                    case UserInput.MoveDown:
                        OnUserInputReceived(UserInput.RepeatMoveDown);
                        break;
                    case UserInput.MoveLeft:
                        OnUserInputReceived(UserInput.RepeatMoveLeft);
                        break;
                    case UserInput.MoveRight:
                        OnUserInputReceived(UserInput.RepeatMoveRight);
                        break;
                    case UserInput.MoveUp:
                        OnUserInputReceived(UserInput.RepeatMoveUp);
                        break;
                }
            }
        }

        private void StartRepeatTimer()
        {
            // Wait at least one game cycle (100ms) but less than two cylces before sending any repeat moves.
            var delay = new TimeSpan(0, 0, 0, 0, 150);

            // Send repeat moves just a bit more frequent than one cylce (otherwise there is a chance to miss an update cycle since timer execution is not that exact).
            var interval = new TimeSpan(0, 0, 0, 0, 80);
            repeatTimer.Change(delay, interval);
        }

        private void StopRepeatTimer()
        {
            repeatTimer.Change(Timeout.Infinite, Timeout.Infinite);
        }
    }
}