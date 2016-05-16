namespace ChipsChallenge.Features
{
    using System.ComponentModel;
    using System.Linq;

    using Windows.UI.ViewManagement;
    using Windows.UI.Xaml.Controls;

    using Game;

    public sealed partial class MainPage
    {
        public static MainPage Current;
        public GameViewModel GameViewModel = new GameViewModel();

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

                ContentFrame.Navigate(typeof(Game.Game));
            };
        }

        public void NavigateBackToGame()
        {
            Menu.SelectedItem = Menu.Items.OfType<MenuItem>().First(x => x.View == typeof(Game.Game));
        }

        private void GameViewModelOnPropertyChanged(object sender, PropertyChangedEventArgs propertyChangedEventArgs)
        {
            if (propertyChangedEventArgs.PropertyName == nameof(GameViewModel.LevelTitle))
            {
                var appView = ApplicationView.GetForCurrentView();
                appView.Title = GameViewModel.LevelTitle;
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

        private void MenuButtonClick(object sender, Windows.UI.Xaml.RoutedEventArgs e)
        {
            GameViewModel.PauseGame();
            NavigationSplitView.IsPaneOpen = !NavigationSplitView.IsPaneOpen;
        }
    }
}