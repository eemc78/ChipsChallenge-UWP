namespace ChipsChallenge.Shared.Gui
{
    using System;

    using Windows.Foundation;

    using Microsoft.Graphics.Canvas;

    public class HudImageFactory
    {
        private CanvasBitmap windowSprites;
        private readonly CanvasBitmap[] loadedNumbers = new CanvasBitmap[24];
        private static HudImageFactory instance;

        private HudImageFactory()
        {
        }

        public static HudImageFactory Instance
        {
            get
            {
                lock (typeof(HudImageFactory))
                {
                    return instance ?? (instance = new HudImageFactory());
                }
            }
        }

        public void Initialize(CanvasBitmap sprites)
        {
            windowSprites = sprites;
        }

        public virtual CanvasBitmap HudBackgroundLandscape => Clone(windowSprites, new Rect(339, 26, 154, 300));
        public virtual CanvasBitmap HudBackgroundPortrait => Clone(windowSprites, new Rect(951, 0, 299, 116));
        public virtual CanvasBitmap PauseScreen => Clone(windowSprites, new Rect(0, 398, 175, 33));
        public virtual CanvasBitmap PasswordBackground => Clone(windowSprites, new Rect(176, 425, 190, 56));
        public virtual CanvasBitmap HintFieldLandscape => Clone(windowSprites, new Rect(812, 301, 128, 146));
        public virtual CanvasBitmap HintFieldPortrait => Clone(windowSprites, new Rect(951, 118, 196, 87));

        /// <summary>
        /// Returns a digital number for Hud
        /// </summary>
        /// <param name="number"> The number to get. -1 for blank. -2 for - (minus sign) </param>
        /// <param name="yellow"> false = green. true = yellow </param>
        /// <returns> The number as an image </returns>
        public virtual CanvasBitmap GetNumber(int number, bool yellow)
        {
            if (number > 9 || number < -2)
            {
                throw new ArgumentException("number must be between -2 and 9");
            }

            int code = number + 2;
            if (yellow)
            {
                code += 12;
            }

            if (loadedNumbers[code] != null)
            {
                return loadedNumbers[code];
            }

            int x;
            int y = yellow ? 375 : 353;

            if (number >= 0)
            {
                x = 15 + number * 14;
            }
            else if (number == -1)
            {
                x = 1;
            }
            else
            {
                x = 155;
            }

            var img = Clone(windowSprites, new Rect(x, y, 13, 21));
            loadedNumbers[code] = img;
            return img;
        }

        public virtual CanvasBitmap GetNumber(char number, bool yellow)
        {
            return GetNumber(CharToInt(number), yellow);
        }

        /// <summary>
        /// Used by getNumber only </summary>
        /// <param name="c"> 0-9 or "-" for - and "x" for blank
        /// @return </param>
        private static int CharToInt(char c)
        {
            if (c == '-')
            {
                return -2;
            }
            if (c == 'x')
            {
                return -1;
            }
            if (c < '0' || c > '9')
            {
                throw new ArgumentException("char must be 0 to 9");
            }
            return c - '0';
        }

        private CanvasBitmap Clone(CanvasBitmap bitmapSource, Rect sourceArea)
        {
            CanvasDevice device = CanvasDevice.GetSharedDevice();
            CanvasRenderTarget offscreen = new CanvasRenderTarget(device, (float)sourceArea.Width, (float)sourceArea.Height, 96);
            using (CanvasDrawingSession ds = offscreen.CreateDrawingSession())
            {
                ds.DrawImage(bitmapSource, new Rect(0,0, sourceArea.Width, sourceArea.Height), sourceArea);
            }

            return offscreen;
        }
    }
}