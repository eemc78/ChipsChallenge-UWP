namespace ChipsChallenge.Features.Game
{
    using System;
    using System.Diagnostics;
    using System.Numerics;
    using System.Threading.Tasks;

    using Windows.Foundation;
    using Windows.UI.ViewManagement;
    using Windows.UI.Xaml;

    using UserInput;

    using Microsoft.Graphics.Canvas;
    using Microsoft.Graphics.Canvas.Brushes;
    using Microsoft.Graphics.Canvas.Effects;
    using Microsoft.Graphics.Canvas.UI;
    using Microsoft.Graphics.Canvas.UI.Xaml;

    public sealed partial class Game
    {
        private const float DefaultDpi = 96;
        private readonly GamePadInput gamepadInput = new GamePadInput();
        private readonly KeyBoardInput keyBoardInput = new KeyBoardInput();
        private readonly SwipeJoystickInput swipeJoystickInput = new SwipeJoystickInput();
        private readonly DispatcherTimer gamepadTimer = new DispatcherTimer();
        private int gameWidth, gameHeight;
        private CanvasRenderTarget currentSurface, nextSurface;
        private CanvasBitmap backgroundImageLandscape;
        private CanvasBitmap backgroundImagePortrait;
        private CanvasBitmap backgroundTile;
        private CanvasImageBrush backgroundTileBrush;

        public Game()
        {
            InitializeComponent();
            GameCanvas.TargetElapsedTime = TimeSpan.FromMilliseconds(100);
            Loaded += OnLoaded;
            Unloaded += OnUnloaded;
        }

        private void RefreshGameSize()
        {
            if (backgroundImageLandscape == null || backgroundImagePortrait == null)
            {
                return;
            }

            double canvasAspectRatio = GameCanvas.Size.Width / GameCanvas.Size.Height;
            if (canvasAspectRatio > 1)
            {
                gameWidth = (int)backgroundImageLandscape.Size.Width;
                gameHeight = (int)backgroundImageLandscape.Size.Height;
                MainPage.Current.GameViewModel.Orientation = ApplicationViewOrientation.Landscape;
            }
            else
            {
                gameWidth = (int)backgroundImagePortrait.Size.Width;
                gameHeight = (int)backgroundImagePortrait.Size.Height;
                MainPage.Current.GameViewModel.Orientation = ApplicationViewOrientation.Portrait;
            }

            double gameAspectRatio = (double)gameWidth / gameHeight;
            if (canvasAspectRatio > gameAspectRatio)
            {
                gameWidth = (int)(gameHeight * canvasAspectRatio);
            }
            else
            {
                gameHeight = (int)(gameWidth / canvasAspectRatio);
            }
        }

        private void GamepadTimerOnTick(object sender, object o)
        {
            gamepadInput.ReadGamePadInput();
        }

        private void OnLoaded(object sender, RoutedEventArgs e)
        {
            PageTargetForKeyboardFocus.Focus(FocusState.Programmatic);
            Window.Current.CoreWindow.KeyDown += keyBoardInput.WindowKeyDown;
            Window.Current.CoreWindow.KeyUp += keyBoardInput.WindowKeyUp;

            gamepadInput.UserInputReceived += (s, args) => MainPage.Current.GameViewModel.ExecuteUserInput(args.Input);
            keyBoardInput.UserInputReceived += (s, args) => MainPage.Current.GameViewModel.ExecuteUserInput(args.Input);
            swipeJoystickInput.UserInputReceived += (s, args) => MainPage.Current.GameViewModel.ExecuteUserInput(args.Input);
            swipeJoystickInput.ActivateTouchInput(GameCanvas);
            gamepadTimer.Interval = TimeSpan.FromMilliseconds(20);
            gamepadTimer.Tick += GamepadTimerOnTick;
            gamepadTimer.Start();
        }

        private void OnUnloaded(object sender, RoutedEventArgs e)
        {
            MainPage.Current.GameViewModel.PauseGame();

            Window.Current.CoreWindow.KeyDown -= keyBoardInput.WindowKeyDown;
            Window.Current.CoreWindow.KeyUp -= keyBoardInput.WindowKeyUp;
            
            GameCanvas.RemoveFromVisualTree();
            GameCanvas = null;

            gamepadTimer.Tick -= GamepadTimerOnTick;
            gamepadTimer.Stop();
        }

        private void GameCanvasCreateResources(CanvasAnimatedControl sender, CanvasCreateResourcesEventArgs args)
        {
            args.TrackAsyncAction(Task.Run(async () =>
            {
                backgroundImageLandscape = await CanvasBitmap.LoadAsync(sender, new Uri("ms-appx:///Features/Game/Assets/WindowLandscape.png"));
                backgroundImagePortrait = await CanvasBitmap.LoadAsync(sender, new Uri("ms-appx:///Features/Game/Assets/WindowPortrait.png"));
                backgroundTile = await CanvasBitmap.LoadAsync(sender, new Uri("ms-appx:///Features/Game/Assets/Background.png"));
                backgroundTileBrush = new CanvasImageBrush(sender, backgroundTile);
                backgroundTileBrush.ExtendX = backgroundTileBrush.ExtendY = CanvasEdgeBehavior.Wrap;
            }).AsAsyncAction());
        }

        private void GameCanvasDraw(ICanvasAnimatedControl sender, CanvasAnimatedDrawEventArgs args)
        {
            if (!MainPage.Current.GameViewModel.IsGameInitialized || GameCanvas == null)
            {
                return;
            }

            var dpiCompensationEffect = new DpiCompensationEffect
            {
                Source = currentSurface,
                SourceDpi = new Vector2(GameCanvas.Dpi)
            };

            var transformEffect = new Transform2DEffect
            {
                Source = dpiCompensationEffect,
                InterpolationMode = CanvasImageInterpolation.NearestNeighbor,
                TransformMatrix = GetDisplayTransform(sender)
            };

            args.DrawingSession.DrawImage(transformEffect);
        }

        private void GameCanvasUpdate(ICanvasAnimatedControl sender, CanvasAnimatedUpdateEventArgs args)
        {
            if (!MainPage.Current.GameViewModel.IsGameInitialized || GameCanvas == null)
            {
                return;
            }

            try
            {
                
                MainPage.Current.GameViewModel.UpdateGame();

                RefreshGameSize();
                InitializeOrRefreshDrawingSurface();
                
                using (CanvasDrawingSession drawingSession = nextSurface.CreateDrawingSession())
                {
                    drawingSession.FillRectangle(new Rect(new Point(), nextSurface.Size), backgroundTileBrush);
                }

                if (gameWidth > gameHeight)
                {
                    nextSurface.CopyPixelsFromBitmap(backgroundImageLandscape);
                    nextSurface.CopyPixelsFromBitmap(MainPage.Current.GameViewModel.PlayField, 32, 32);
                    nextSurface.CopyPixelsFromBitmap(MainPage.Current.GameViewModel.HudLandscape, 339, 26);
                }
                else
                {
                    nextSurface.CopyPixelsFromBitmap(backgroundImagePortrait);
                    nextSurface.CopyPixelsFromBitmap(MainPage.Current.GameViewModel.PlayField, 32, 32);
                    nextSurface.CopyPixelsFromBitmap(MainPage.Current.GameViewModel.HudPortrait, 26, 339);
                }

                SwapSurfaces();
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
            }
        }

        private void InitializeOrRefreshDrawingSurface()
        {
            if (nextSurface == null || (int)nextSurface.Size.Width != gameWidth || (int)nextSurface.Size.Height != gameHeight)
            {
                nextSurface?.Dispose();
                nextSurface = new CanvasRenderTarget(GameCanvas, gameWidth, gameHeight, DefaultDpi);
            }
        }

        private void SwapSurfaces()
        {
            var tmp = currentSurface;
            currentSurface = nextSurface;
            nextSurface = tmp;
        }

        private Matrix3x2 GetDisplayTransform(ICanvasAnimatedControl canvas)
        {
            // A transform matrix scales up the game image rendertarget and moves
            // it to the right part of the screen. This uses nearest neighbor filtering
            // to avoid unwanted blurring of the cell shapes.
            var outputSize = canvas.Size.ToVector2();
            var sourceSize = new Vector2(canvas.ConvertPixelsToDips(gameWidth), canvas.ConvertPixelsToDips(gameHeight));

            return GetDisplayTransform(outputSize, sourceSize);
        }

        private Matrix3x2 GetDisplayTransform(Vector2 outputSize, Vector2 sourceSize)
        {
            // Scale the display to fill the control.
            var scale = outputSize / sourceSize;
            var offset = Vector2.Zero;

            // Letterbox or pillarbox to preserve aspect ratio.
            if (scale.X > scale.Y)
            {
                scale.X = scale.Y;
                offset.X = (outputSize.X - sourceSize.X * scale.X) / 2;
            }
            else
            {
                scale.Y = scale.X;
                offset.Y = (outputSize.Y - sourceSize.Y * scale.Y) / 2;
            }

            return Matrix3x2.CreateScale(scale) * Matrix3x2.CreateTranslation(offset);
        }
    }
}