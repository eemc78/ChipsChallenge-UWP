using Windows.UI.Core;

namespace ChipsChallenge.Features.Game
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel;
    using System.IO;
    using System.Runtime.CompilerServices;
    using System.Threading.Tasks;

    using Windows.Storage;
    using Windows.UI.Popups;
    using Windows.UI.ViewManagement;

    using Shared;
    using Shared.Gui;
    using Shared.Tickbehaviors;

    using Microsoft.Graphics.Canvas;

    public class GameViewModel : INotifyPropertyChanged
    {
        public readonly AudioPlayer AudioPlayer = new AudioPlayer();
        private string levelTitle = string.Empty;
        private CanvasBitmap playField, hudLandscape, hudPortrait;
        private Dictionary<Shared.Gui.UserInput, Action> userInputMapping;
        private bool isGameInitialized;
        public event PropertyChangedEventHandler PropertyChanged;
        private readonly PlayField gamePlayField = new PlayField(9, 9);
        private ApplicationViewOrientation orientation = ApplicationViewOrientation.Landscape;

        public async Task Initialize()
        {
            await AudioPlayer.InitializeSound();

            userInputMapping = new Dictionary<Shared.Gui.UserInput, Action>
                             {
                                 { Shared.Gui.UserInput.MoveUp, MoveUp },
                                 { Shared.Gui.UserInput.MoveDown, MoveDown },
                                 { Shared.Gui.UserInput.MoveLeft, MoveLeft },
                                 { Shared.Gui.UserInput.MoveRight, MoveRight },
                                 { Shared.Gui.UserInput.NextLevel, NextLevel },
                                 { Shared.Gui.UserInput.PreviousLevel, PreviousLevel },
                                 { Shared.Gui.UserInput.RestartLevel, RestartLevel },
                                 { Shared.Gui.UserInput.Pause, PauseGame },
                                 { Shared.Gui.UserInput.Unpause, StartOrResumeGame },
                                 { Shared.Gui.UserInput.TogglePause, TogglePause }
                             };

            await InitializeHudImageFactory();
            await InitializeBlockImageFactory();
            await InitializeLevelFactory();

            GameInstance.BlockFactory = MicrosoftBlockFactory.Instance;
            GameInstance.ChipsDied += GameInstanceOnChipsDied;

            GoToLevel(1);

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

        public bool IsGameInitialized
        {
            get
            {
                return isGameInitialized;
            }
            private set
            {
                isGameInitialized = value;
                OnPropertyChanged();
            }
        }
        public int TotalLevelCount => GameInstance.TotalLevelCount;

        public int CurrentLevelNumber => GameInstance.CurrentLevelNumber;

        public CanvasBitmap PlayField
        {
            get
            {
                return playField;
            }

            set
            {
                playField = value;
                OnPropertyChanged();
            }
        }

        public string LevelTitle
        {
            get
            {
                return levelTitle;
            }

            set
            {
                levelTitle = value;
                OnPropertyChanged();
            }
        }

        public CanvasBitmap HudLandscape
        {
            get
            {
                return hudLandscape;
            }

            set
            {
                hudLandscape = value;
                OnPropertyChanged();
            }
        }

        public CanvasBitmap HudPortrait
        {
            get
            {
                return hudPortrait;
            }

            set
            {
                hudPortrait = value;
                OnPropertyChanged();
            }
        }

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
                OnPropertyChanged();
            }
        }

        public void ExecuteUserInput(Shared.Gui.UserInput input)
        {

            if (userInputMapping.ContainsKey(input))
            {
                userInputMapping[input].Invoke();
            }

            else
            {
                throw new NotImplementedException("Input mapping not implemented!");
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

        private void TogglePause()
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

        public async void  UpdateGame()
        {
            await Windows.ApplicationModel.Core.CoreApplication.MainView.CoreWindow.Dispatcher.RunAsync(CoreDispatcherPriority.Normal, () =>
            {
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
            });
        }

        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            var handler = PropertyChanged;
            handler?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }

        private async void GameInstanceOnChipsDied(object sender, MessageEventArgs eventArgs)
        {
            await Windows.ApplicationModel.Core.CoreApplication.MainView.CoreWindow.Dispatcher.RunAsync(CoreDispatcherPriority.Normal, async () =>
            {
                var dialog = new MessageDialog(eventArgs.Message);
                dialog.Commands.Add(new UICommand("Restart level") {Id = 0});

                await dialog.ShowAsync();

                GameInstance.Restart();
            });
        }
    }
}