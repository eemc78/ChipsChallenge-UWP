namespace ChipsChallenge.Shared.Gui
{
    using Windows.Foundation;
    using Windows.UI;
    using Windows.UI.Text;

    using Microsoft.Graphics.Canvas;
    using Microsoft.Graphics.Canvas.Text;

    using System;
    using System.Diagnostics;

    internal class PlayField
    {
        private readonly int width;
        private readonly int height;

        private Game GameInstance => Game.Instance;

        public PlayField(int width, int height)
        {
            this.width = width;
            this.height = height;
        }

        public CanvasBitmap GeneratePlayField()
        {
            CanvasDevice device = CanvasDevice.GetSharedDevice();
            CanvasRenderTarget offscreen = new CanvasRenderTarget(device, width * 32, height * 32, 96);

            try
            {
                using (CanvasDrawingSession drawingSession = offscreen.CreateDrawingSession())
                {
                    if (!GameInstance.IsPaused)
                    {
                        DrawPlayField(drawingSession);
                    }
                    else
                    {
                        DrawPauseScreen(drawingSession);
                    }
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
            }

            return offscreen;
        }

        private void DrawPauseScreen(CanvasDrawingSession drawingSession)
        {
            drawingSession.Clear(Colors.Black);
            drawingSession.DrawImage(HudImageFactory.Instance.PauseScreen, 58, 126);
        }

        private void DrawPlayField(CanvasDrawingSession drawingSession)
        {
            var level = GameInstance.Level;
            var chipPosition = level.FindChip();
            var top = GetTopTile(level, chipPosition.Y);
            var left = GetLeftTile(level, chipPosition.X);

            for (var x = 0; x < width; x++)
            {
                for (var y = 0; y < height; y++)
                {
                    drawingSession.DrawImage(level.GetBlockContainer(x + left, y + top).Image, new Rect(x * 32, y * 32, 32, 32));
                }
            }

            if (GameInstance.ShowLevelPassword)
            {
                PaintLevelTitleAndPassword(drawingSession);
            }
        }

        private void PaintLevelTitleAndPassword(CanvasDrawingSession drawingSession)
        {
            int x = 49;
            int y = 212;
            CanvasBitmap passwordField = HudImageFactory.Instance.PasswordBackground;
            drawingSession.DrawImage(passwordField, x, y);

            var titleRect = new Rect(x + 3, y + 4, passwordField.Size.Width - 8, (passwordField.Size.Height - 8) / 2);
            var passwordRect = new Rect(x + 3, y + 4 + (passwordField.Size.Height - 8) / 2, passwordField.Size.Width - 8, (passwordField.Size.Height - 8) / 2);

            int titleFontSize = 20;
            if (GameInstance.Level.MapTitle.Length > 18)
            {
                titleFontSize = 14;
            }
            else if (GameInstance.Level.MapTitle.Length > 13)
            {
                titleFontSize = 16;
            }

            var titleCanvasTextFormat = new CanvasTextFormat
            {
                FontStyle = FontStyle.Normal,
                FontWeight = FontWeights.Bold,
                FontFamily = "Arial",
                FontSize = titleFontSize,
                HorizontalAlignment = CanvasHorizontalAlignment.Center,
                VerticalAlignment = CanvasVerticalAlignment.Center
            };

            var passwordCanvasTextFormat = new CanvasTextFormat
            {
                FontStyle = FontStyle.Normal,
                FontWeight = FontWeights.Bold,
                FontFamily = "Arial",
                FontSize = 20,
                HorizontalAlignment = CanvasHorizontalAlignment.Center
            };

            drawingSession.TextAntialiasing = CanvasTextAntialiasing.Aliased;
            drawingSession.DrawText(GameInstance.Level.MapTitle, titleRect, Colors.Yellow, titleCanvasTextFormat);
            drawingSession.DrawText("Password: " + GameInstance.Level.Password, passwordRect, Colors.Yellow, passwordCanvasTextFormat);
        }

        private int GetTopTile(GameLevel level, int chipY)
        {
            var top = chipY - 4;
            if (top < 0)
            {
                return 0;
            }

            if (top > level.Height - height)
            {
                return level.Height - height;
            }

            return top;
        }

        private int GetLeftTile(GameLevel level, int chipX)
        {
            var left = chipX - 4;
            if (left < 0)
            {
                return 0;
            }

            if (left > level.Width - width)
            {
                return level.Width - width;
            }

            return left;
        }

        /*
        public virtual void mousePressed(MouseEvent me)
        {
            if (me.Button == MouseEvent.BUTTON1)
            {
                GameLevel gl = Game.Instance.Level;
                Point chip = gl.findChip();
                int top = GetTopTile(gl, chip.y);
                int left = GetLeftTile(gl, chip.x);
                int clickedX = me.X / 32;
                int clickedY = me.Y / 32;
                Point moveTo = new Point(left + clickedX, top + clickedY);
                ChipTickBehavior.Instance.MoveTo(MoveTo);
            }
        }

*/
    }
}