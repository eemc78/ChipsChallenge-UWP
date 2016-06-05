namespace ChipsChallenge.Features
{
    using System;
    using System.ComponentModel;
    using System.Linq;

    using Windows.UI.Core;
    using Windows.UI.ViewManagement;
    using Windows.UI.Xaml;
    using Windows.UI.Xaml.Controls;
    using Windows.UI.Xaml.Data;

    using ChipsChallenge.Features.Levels;

    using Game;

    public sealed partial class MainPage
    {
        public static MainPage Current;
        public GameViewModel GameViewModel = new GameViewModel();
        public LevelsViewModel LevelsViewModel = new LevelsViewModel();

        public Frame MainContentFrame => ContentFrame;

        public MainPage()
        {
            InitializeComponent();

            Current = this;
            ContentFrame.Navigate(typeof(Loading));

            Loaded += async (sender, args) =>
            {
                GameViewModel.PropertyChanged += GameViewModelOnPropertyChanged;
                await GameViewModel.Initialize();
                LevelsViewModel.Initialize();


                ContentFrame.Navigate(typeof(Game.Game));
            };
        }

        public void NavigateBackToGame()
        {
            Menu.SelectedItem = Menu.Items.OfType<MenuItem>().First(x => x.View == typeof(Game.Game));
        }

        private async void GameViewModelOnPropertyChanged(object sender, PropertyChangedEventArgs propertyChangedEventArgs)
        {
            if (propertyChangedEventArgs.PropertyName == nameof(GameViewModel.LevelTitle))
            {
                await Windows.ApplicationModel.Core.CoreApplication.MainView.CoreWindow.Dispatcher.RunAsync(CoreDispatcherPriority.Normal, () =>
                {
                    var appView = ApplicationView.GetForCurrentView();
                    appView.Title = GameViewModel.LevelTitle;
                });
            }
        }

        private void MenuSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            ListBox list = (ListBox)sender;
            if (list.SelectedIndex == -1)
            {
                return;
            }

            MenuItem selectedItem = (MenuItem)list.SelectedItem;
            if (selectedItem != null)
            {
                if (GameViewModel.IsGameInitialized)
                {
                    ContentFrame.Navigate(selectedItem.View);
                }
                else
                {
                    NavigateBackToGame();
                }
            }

            NavigationSplitView.IsPaneOpen = false;
        }

        private void MenuButtonClick(object sender, RoutedEventArgs e)
        {
            GameViewModel.PauseGame();
            NavigationSplitView.IsPaneOpen = !NavigationSplitView.IsPaneOpen;
        }

        private void PreviousButton_OnClick(object sender, RoutedEventArgs e)
        {
            LevelsViewModel.PreviousLevel();
        }

        private void Restart_OnClick(object sender, RoutedEventArgs e)
        {
            LevelsViewModel.RestartLevel();
        }

        private void Next_OnClick(object sender, RoutedEventArgs e)
        {
            LevelsViewModel.NextLevel();
        }
    }
}