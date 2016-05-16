using System;
using System.Diagnostics;
using Windows.Foundation;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Input;
using Microsoft.Graphics.Canvas.UI.Xaml;

namespace ChipsChallenge.Features.Game.UserInput
{
    public class SwipeJoystickInput : UserInputBase
    {
        private static readonly Point InvalidPoint = new Point(-1, -1);

        private readonly DispatcherTimer timer;
        private Point startManipulation = InvalidPoint;
        private Point currentPoint = InvalidPoint;

        public SwipeJoystickInput()
        {
            timer = new DispatcherTimer();
        }

        public void ActivateTouchInput(CanvasAnimatedControl gameCanvas)
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
                currentPoint = args.Position;
                ProcessInput();
                timer.Stop();
                timer.Start();
            };
            gameCanvas.ManipulationCompleted += (sender, args) =>
            {
                Debug.WriteLine($"ManipulationCompleted: {args.Position}");
                currentPoint = startManipulation = InvalidPoint;
                timer.Stop();
            };

            timer.Interval = TimeSpan.FromMilliseconds(80);
            timer.Tick += CheckInput;
        }

        private void ProcessInput()
        {
            var userInput = GetUserInput(startManipulation, currentPoint);
            if (userInput != null)
            {
                OnUserInputReceived(userInput.Value);
            }
        }

        private Shared.Gui.UserInput? GetUserInput(Point centerPoint, Point point)
        {
            if (startManipulation == InvalidPoint
                || point == InvalidPoint
                || IsWithinCenter(point, centerPoint))
            {
                return null;
            }

            var xDelta = centerPoint.X - point.X;
            var yDelta = centerPoint.Y - point.Y;
            Debug.WriteLine($"S: {startManipulation} - P: {point} - XD (a): {xDelta} - YD: {yDelta}");
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

        private bool IsWithinCenter(Point centerPoint, Point point)
        {
            return Math.Pow(point.X - centerPoint.X, 2) + Math.Pow(point.Y - centerPoint.Y, 2) < Math.Pow(2, 2);
        }

        private void CheckInput(object sender, object state)
        {
            ProcessInput();
        }
    }
}