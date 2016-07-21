using PropertyChanged;

namespace ChipsChallenge.Features.Game
{
    using System;
    using System.ComponentModel;
    using System.IO;
    using System.Threading.Tasks;

    using Windows.Storage;
    using Windows.UI.Popups;
    using Windows.UI.ViewManagement;

    using Shared;
    using Shared.Gui;
    using Shared.Tickbehaviors;
    using Settings;

    using Microsoft.Graphics.Canvas;

    using Windows.UI.Core;

    public class GameViewModel : INotifyPropertyChanged
    {
        private readonly UserSettings userSettings = new UserSettings();
        public readonly AudioPlayer AudioPlayer = new AudioPlayer();
        private bool restartRequested;
        public event PropertyChangedEventHandler PropertyChanged;
        private readonly PlayField gamePlayField = new PlayField(9, 9);
        private ApplicationViewOrientation orientation = ApplicationViewOrientation.Landscape;
        private Shared.Gui.UserInput proposedmove;

        public async Task Initialize()
        {
            await AudioPlayer.InitializeSound();
            await InitializeHudImageFactory();
            await InitializeBlockImageFactory();
            await InitializeLevelFactory();

            GameInstance.BlockFactory = MicrosoftBlockFactory.Instance;
            GameInstance.ChipsDied += GameInstanceOnChipsDied;

            GoToLevel(userSettings.LevelNumber);

            HudLandscape = Hud.Instance.GetHudLandscape();
            HudPortrait = Hud.Instance.GetHudPortrait();

            IsGameInitialized = true;
        }

        public async Task InitializeLevelFactory()
        {
            var file = await StorageFile.GetFileFromApplicationUriAsync(new Uri("ms-appx:///Features/Game/Assets/CHIPS.DAT"));
            var inputStream = await file.OpenReadAsync();
            var levelFileStream = inputStream.AsStreamForRead();

            GameInstance.LevelFactory = new MicrosoftLevelFactory(RandomAccessFileLevelReader.Create(levelFileStream));
        }

        public async Task InitializeHudImageFactory()
        {
            CanvasDevice device = CanvasDevice.GetSharedDevice();
            var windowSprites = await CanvasBitmap.LoadAsync(device, new Uri("ms-appx:///Features/Game/Assets/WindowSprites.png"));

            HudImageFactory.Instance.Initialize(windowSprites);
        }

        public async Task InitializeBlockImageFactory()
        {
            CanvasDevice device = CanvasDevice.GetSharedDevice();
            var sprites = await CanvasBitmap.LoadAsync(device, new Uri("ms-appx:///Features/Game/Assets/Sprites.png"));

            BlockImageFactory.Instance.Initialize(sprites);
        }

        private Shared.Game GameInstance => Shared.Game.Instance;

        public bool IsGameInitialized { get; private set; }

        public int TotalLevelCount => GameInstance.TotalLevelCount;

        public int CurrentLevelNumber => GameInstance.CurrentLevelNumber;

        [DoNotNotify]
        public CanvasBitmap PlayField { get; set; }

        public string LevelTitle { get; set; }

        [DoNotNotify]
        public CanvasBitmap HudLandscape { get; set; }

        [DoNotNotify]
        public CanvasBitmap HudPortrait { get; set; }

        [DoNotNotify]
        public ApplicationViewOrientation Orientation
        {
            get
            {
                return orientation;
            }
            set
            {
                if (value == orientation)
                {
                    return;
                }

                orientation = value;
                UpdateGame();
            }
        }

        public void MoveUp()
        {
            ChipTickBehavior.Instance.MoveUp();
            StartOrResumeGame();
        }

        public void MoveDown()
        {
            ChipTickBehavior.Instance.MoveDown();
            StartOrResumeGame();
        }

        public void MoveLeft()
        {
            ChipTickBehavior.Instance.MoveLeft();
            StartOrResumeGame();
        }

        public void MoveRight()
        {
            ChipTickBehavior.Instance.MoveRight();
            StartOrResumeGame();
        }

        public void RepeatMoveUp()
        {
            proposedmove = Shared.Gui.UserInput.MoveUp;
        }

        public void RepeatMoveDown()
        {
            proposedmove = Shared.Gui.UserInput.MoveDown;
        }

        public void RepeatMoveLeft()
        {
            proposedmove = Shared.Gui.UserInput.MoveLeft;
        }

        public void RepeatMoveRight()
        {
            proposedmove = Shared.Gui.UserInput.MoveRight;
        }

        public void ClearMove()
        {
            proposedmove = Shared.Gui.UserInput.None;
        }

        public void StartOrResumeGame()
        {
            if (!GameInstance.IsStarted)
            {
                GameInstance.StartOrResumeGame();
            }
        }

        public void PauseGame()
        {
            GameInstance.Pause();
            UpdateGame();
        }

        public void TogglePause()
        {
            if (!GameInstance.IsStarted)
            {
                StartOrResumeGame();
            }
            else
            {
                PauseGame();
            }
        }

        public void NextLevel()
        {
            GameInstance.NextLevel();
            UpdateGame();
            LevelTitle = GameInstance.Level.MapTitle;
        }

        public void PreviousLevel()
        {
            GameInstance.PreviousLevel();
            UpdateGame();
            LevelTitle = GameInstance.Level.MapTitle;
        }

        public void RestartLevel()
        {
            GameInstance.Restart();
            UpdateGame();
        }

        public void GoToLevel(int levelNumber)
        {
            GameInstance.GoToLevel(levelNumber);
            UpdateGame();
            LevelTitle = GameInstance.Level.MapTitle;
        }

        public void StartBackgroundMusic()
        {
            GameInstance.PlayBackgroundMusic();
        }

        public void UpdateGame()
        {
            if (restartRequested)
            {
                restartRequested = false;
                RestartLevel();
                return;
            }

            if (ChipTickBehavior.Instance.proposedMove == null && proposedmove != Shared.Gui.UserInput.None)
            {
                switch (proposedmove)
                {
                        case Shared.Gui.UserInput.MoveUp:
                        MoveUp();
                        break;
                        case Shared.Gui.UserInput.MoveDown:
                        MoveDown();
                        break;
                        case Shared.Gui.UserInput.MoveLeft:
                        MoveLeft();
                        break;
                        case Shared.Gui.UserInput.MoveRight:
                        MoveRight();
                        break;
                }
            }

            proposedmove = Shared.Gui.UserInput.None;
            GameInstance.Tick();
            PlayField = gamePlayField.GeneratePlayField();

            if (Orientation == ApplicationViewOrientation.Landscape)
            {
                HudLandscape = Hud.Instance.GetHudLandscape();
            }
            else
            {
                HudPortrait = Hud.Instance.GetHudPortrait();
            }
        }

        private async void GameInstanceOnChipsDied(object sender, MessageEventArgs eventArgs)
        {
            await Windows.ApplicationModel.Core.CoreApplication.MainView.CoreWindow.Dispatcher.RunAsync(CoreDispatcherPriority.Normal, async () =>
            {
                var dialog = new MessageDialog(eventArgs.Message);
                dialog.Commands.Add(new UICommand("Restart level", _ => restartRequested = true) { Id = 0 });
                await dialog.ShowAsync();
            });
        }
    }
}