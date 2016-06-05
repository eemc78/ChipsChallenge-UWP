namespace ChipsChallenge.Features.Levels
{
    using Windows.UI.Xaml;
    using Windows.UI.Xaml.Controls;

    public sealed partial class Levels
    {
        public Levels()
        {
            InitializeComponent();
            DataContext = MainPage.Current.LevelsViewModel;
        }

        private void ButtonBase_OnClick(object sender, RoutedEventArgs e)
        {
            object buttonTag = (sender as Button)?.Tag;
            if (buttonTag != null)
            {
                int levelNumber = (int)buttonTag;
                MainPage.Current.GameViewModel.GoToLevel(levelNumber);
                MainPage.Current.NavigateBackToGame();
            }
        }
    }
}