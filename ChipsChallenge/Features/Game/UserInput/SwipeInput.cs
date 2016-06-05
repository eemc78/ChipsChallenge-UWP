namespace ChipsChallenge.Features.Game.UserInput
{
    using System;

    using Microsoft.Graphics.Canvas.UI.Xaml;

    using Shared.Gui;

    using Windows.UI.Xaml.Input;

    public class SwipeInput : UserInputBase
    {
        private int x1;
        private int x2;
        private int y1;
        private int y2;

        public void EnableSwipeGesturesFor(CanvasAnimatedControl control)
        {
            control.ManipulationMode = ManipulationModes.TranslateRailsX | ManipulationModes.TranslateRailsY;
            control.ManipulationStarted += (s, e) => x1 = (int)e.Position.X;
            control.ManipulationStarted += (s, e) => y1 = (int)e.Position.Y;
            control.ManipulationCompleted += (s, e) =>
            {
                x2 = (int)e.Position.X;
                y2 = (int)e.Position.Y;

                System.Diagnostics.Debug.WriteLine(x1);
                System.Diagnostics.Debug.WriteLine(x2);
                System.Diagnostics.Debug.WriteLine(y1);
                System.Diagnostics.Debug.WriteLine(y2);

                int horizontalDiff = Math.Abs(x1 - x2);
                int verticalDiff = Math.Abs(y1 - y2);

                if (horizontalDiff > verticalDiff)
                {
                    if (x1 > x2)
                    {
                        System.Diagnostics.Debug.WriteLine("left");
                        OnUserInputReceived(UserInput.MoveLeft);
                    }
                    else
                    {
                        System.Diagnostics.Debug.WriteLine("right");
                        OnUserInputReceived(UserInput.MoveRight);
                    }
                }
                else
                {
                    if (y1 > y2)
                    {
                        System.Diagnostics.Debug.WriteLine("up");
                        OnUserInputReceived(UserInput.MoveUp);
                    }
                    else
                    {
                        System.Diagnostics.Debug.WriteLine("down");
                        OnUserInputReceived(UserInput.MoveDown);
                    }
                }
            };
        }
    }
}