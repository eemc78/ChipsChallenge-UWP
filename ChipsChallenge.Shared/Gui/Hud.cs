namespace ChipsChallenge.Shared.Gui
{
    using System;

    using Windows.Foundation;
    using Windows.UI;
    using Windows.UI.Text;

    using Microsoft.Graphics.Canvas;
    using Microsoft.Graphics.Canvas.Text;

    using Moves = Move.Moves;
    using Type = Block.Type;

    internal class Hud
    {
        private readonly Inventory inventory = Game.Instance.Inventory;
        private bool timeLimit = true;
        private static Hud hud;

        private Hud()
        {
        }

        public static Hud Instance
        {
            get
            {
                lock (typeof(Game))
                {
                    return hud ?? (hud = new Hud());
                }
            }
        }

        private Game GameInstance => Game.Instance;


        public CanvasBitmap GetHudPortrait()
        {
            CanvasBitmap hudBackground = HudImageFactory.Instance.HudBackgroundPortrait;

            CanvasDevice device = CanvasDevice.GetSharedDevice();
            CanvasRenderTarget offscreen = new CanvasRenderTarget(device, hudBackground.SizeInPixels.Width, hudBackground.SizeInPixels.Height, 96);
            using (CanvasDrawingSession drawingSession = offscreen.CreateDrawingSession())
            {
                drawingSession.DrawImage(hudBackground);
                PaintLevel(drawingSession, 60, 39);
                PaintTime(drawingSession, 139, 39);
                PaintInventoryKeys(drawingSession, 13, 70);

                if (GameInstance.IsChipOnHintField)
                {
                    CanvasBitmap hintField = HudImageFactory.Instance.HintFieldPortrait;
                    PaintHint(drawingSession, hintField, 92, 15);
                }
                else
                {
                    PaintChipsLeft(drawingSession, 236, 39);
                    PaintInventoryBoots(drawingSession, 159, 70);
                }
            }

            return offscreen;
        }

        public CanvasBitmap GetHudLandscape()
        {
            CanvasBitmap hudBackground = HudImageFactory.Instance.HudBackgroundLandscape;

            CanvasDevice device = CanvasDevice.GetSharedDevice();
            CanvasRenderTarget offscreen = new CanvasRenderTarget(device, hudBackground.SizeInPixels.Width, hudBackground.SizeInPixels.Height, 96);
            using (CanvasDrawingSession drawingSession = offscreen.CreateDrawingSession())
            {
                drawingSession.DrawImage(hudBackground);
                PaintLevel(drawingSession, 83, 38);
                PaintTime(drawingSession, 83, 100);

                if (GameInstance.IsChipOnHintField)
                {
                    CanvasBitmap hintField = HudImageFactory.Instance.HintFieldLandscape;
                    PaintHint(drawingSession, hintField, 13, 139);
                }
                else
                {
                    PaintChipsLeft(drawingSession, 83, 190);
                    PaintInventoryKeys(drawingSession, 13, 221);
                    PaintInventoryBoots(drawingSession, 13, 253);
                }
            }

            return offscreen;
        }

        private void PaintLevel(CanvasDrawingSession ds, int x, int y)
        {
            string s = intToPaintableString(GameInstance.Level.LevelNumber);
            for (int i = s.Length - 1; i >= 0; i--)
            {
                var img = HudImageFactory.Instance.GetNumber(s[i], false);
                ds.DrawImage(img, x, y);
                x -= 17;
            }
        }

        private void PaintTime(CanvasDrawingSession ds, int x, int y)
        {
            string s = timeLimit ? intToPaintableString(GameInstance.Level.TimeLeft) : "---";
            bool yellow = !timeLimit || GameInstance.Level.TimeLeft <= 15;
            for (int i = s.Length - 1; i >= 0; i--)
            {
                CanvasBitmap img = HudImageFactory.Instance.GetNumber(s[i], yellow);
                ds.DrawImage(img, x, y);
                x -= 17;
            }
        }

        private void PaintHint(CanvasDrawingSession ds, CanvasBitmap hintField, int x, int y)
        {
            ds.DrawImage(hintField, x, y);

            var hintText = GameInstance.Level.Hint;
            int fontSize = hintText.Length > 83 ? 14 : 16;
            var rect = new Rect(x + 4, y + 4, hintField.Size.Width - 8, hintField.Size.Height - 8);
            var canvasTextFormat = new CanvasTextFormat
            {
                FontStyle = FontStyle.Italic,
                FontWeight = FontWeights.Bold,
                FontFamily = "Arial",
                FontSize = fontSize,
                HorizontalAlignment = CanvasHorizontalAlignment.Center
            };

            ds.TextAntialiasing = CanvasTextAntialiasing.Aliased;
            ds.DrawText("Hint: " + hintText, rect, Colors.Aqua, canvasTextFormat);
        }

        private void PaintChipsLeft(CanvasDrawingSession ds, int x, int y)
        {
            string s = intToPaintableString(GameInstance.Level.ChipsLeft);
            bool yellow = GameInstance.Level.ChipsLeft == 0;
            for (int i = s.Length - 1; i >= 0; i--)
            {
                CanvasBitmap img = HudImageFactory.Instance.GetNumber(s[i], yellow);
                ds.DrawImage(img, x, y);
                x -= 17;
            }
        }

        private void PaintInventoryKeys(CanvasDrawingSession ds, int x, int y)
        {
            BlockImageFactory bif = BlockImageFactory.Instance;
            CanvasBitmap im;
            CanvasBitmap empty = bif.GetImage(Type.FLOOR, Moves.UP, false);
            im = empty;
            if (inventory.HasKey(Inventory.Key.RED))
            {
                im = bif.GetImage(Type.REDKEY, Moves.UP, false);
            }
            ds.DrawImage(im, x, y);
            im = empty;
            if (inventory.HasKey(Inventory.Key.BLUE))
            {
                im = bif.GetImage(Type.BLUEKEY, Moves.UP, false);
            }
            ds.DrawImage(im, x + 32, y);
            im = empty;
            if (inventory.HasKey(Inventory.Key.YELLOW))
            {
                im = bif.GetImage(Type.YELLOWKEY, Moves.UP, false);
            }
            ds.DrawImage(im, x + 2 * 32, y);
            im = empty;
            if (inventory.HasKey(Inventory.Key.GREEN))
            {
                im = bif.GetImage(Type.GREENKEY, Moves.UP, false);
            }
            ds.DrawImage(im, x + 3 * 32, y);
        }

        private void PaintInventoryBoots(CanvasDrawingSession ds, int x, int y)
        {
            BlockImageFactory bif = BlockImageFactory.Instance;
            CanvasBitmap im;
            CanvasBitmap empty = bif.GetImage(Type.FLOOR, Moves.UP, false);

            im = empty;
            if (inventory.HasBoots(Inventory.Boots.ICESKATES))
            {
                im = bif.GetImage(Type.ICESKATES, Moves.UP, false);
            }
            ds.DrawImage(im, x, y);
            im = empty;
            if (inventory.HasBoots(Inventory.Boots.SUCTIONBOOTS))
            {
                im = bif.GetImage(Type.SUCTIONBOOTS, Moves.UP, false);
            }
            ds.DrawImage(im, x + 32, y);
            im = empty;
            if (inventory.HasBoots(Inventory.Boots.FIREBOOTS))
            {
                im = bif.GetImage(Type.FIREBOOTS, Moves.UP, false);
            }
            ds.DrawImage(im, x + 2 * 32, y);
            im = empty;
            if (inventory.HasBoots(Inventory.Boots.FLIPPERS))
            {
                im = bif.GetImage(Type.FLIPPERS, Moves.UP, false);
            }
            ds.DrawImage(im, x + 3 * 32, y);
        }

        /// <summary>
        /// Makes an int into a 3-character String that covers the entire textfield when painted </summary>
        /// <param name="n"> The string to fix </param>
        /// <returns> A string that is 3 characters of length </returns>
        private string intToPaintableString(int n)
        {
            string s = Convert.ToString(n);
            switch (s.Length)
            {
                case 3:
                    return s;
                case 2:
                    return "x" + s;
                case 1:
                    return "xx" + s;
                case 0:
                    return "xxx";
            }
            return "999";
        }
    }
}