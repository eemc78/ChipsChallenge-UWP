using System;
using System.Diagnostics;
using Windows.Foundation;
using Windows.UI.Xaml.Input;
using Microsoft.Graphics.Canvas.UI.Xaml;

namespace ChipsChallenge.Features.Game.UserInput
{
    public class SwipeJoystickInput : UserInputBase
    {
        private static readonly Point InvalidPoint = new Point(-1, -1);
        private Point startManipulation = InvalidPoint;

        public void EnableSwipeJoystickFor(CanvasAnimatedControl gameCanvas)
        {
            gameCanvas.ManipulationMode = ManipulationModes.TranslateX | ManipulationModes.TranslateY;
            gameCanvas.ManipulationStarted += (sender, args) =>
            {
                Debug.WriteLine($"ManipulatonStart: {args.Position}");
                startManipulation = args.Position;
            };
            gameCanvas.ManipulationDelta += (sender, args) =>
            {
                Debug.WriteLine($"ManipulatonDelta: {args.Position}");
                ProcessInput(args.Position);
            };
            gameCanvas.ManipulationCompleted += (sender, args) =>
            {
                Debug.WriteLine($"ManipulationCompleted: {args.Position}");
                startManipulation = InvalidPoint;
            };
        }

        private void ProcessInput(Point currentPosition)
        {
            if (startManipulation == InvalidPoint
                || currentPosition == InvalidPoint
                || IsWithinCenter(startManipulation, currentPosition))
            {
                return;
            }

            var userInput = GetUserInput(startManipulation, currentPosition);
            OnUserInputReceived(userInput);
        }

        private Shared.Gui.UserInput GetUserInput(Point centerPoint, Point currentPosition)
        {
            var xDelta = centerPoint.X - currentPosition.X;
            var yDelta = centerPoint.Y - currentPosition.Y;
            Debug.WriteLine($"S: {startManipulation} - P: {currentPosition} - XD: {xDelta} - YD: {yDelta}");
            if (Math.Abs(xDelta) > Math.Abs(yDelta))
            {
                if (xDelta < 0)
                {
                    Debug.WriteLine("Right");
                    return Shared.Gui.UserInput.MoveRight;
                }

                Debug.WriteLine("Left");
                return Shared.Gui.UserInput.MoveLeft;
            }

            if (yDelta < 0)
            {
                Debug.WriteLine("Down");
                return Shared.Gui.UserInput.MoveDown;
            }

            Debug.WriteLine("Up");
            return Shared.Gui.UserInput.MoveUp;
        }

        private bool IsWithinCenter(Point centerPoint, Point currentPosition)
        {
            return Math.Pow(currentPosition.X - centerPoint.X, 2) + Math.Pow(currentPosition.Y - centerPoint.Y, 2) < Math.Pow(2, 2);
        }
    }
}