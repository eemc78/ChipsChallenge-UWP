namespace ChipsChallenge.Features.Levels
{
    using System.Collections.ObjectModel;

    using Windows.UI.Xaml.Controls;

    public class LevelsViewModel
    {
        public ObservableCollection<int> Levels { get; } = new ObservableCollection<int>();

        public ObservableCollection<LevelControl> LevelControls { get; } = new ObservableCollection<LevelControl>();

        public void Initialize()
        {
            LevelControls.Add(new LevelControl(Symbol.Previous, "Previous", _ => PreviousLevel(), _ => HasPreviousLevel()));
            LevelControls.Add(new LevelControl(Symbol.Refresh, "Restart", _ => RestartLevel(), _ => true));
            LevelControls.Add(new LevelControl(Symbol.Next, "Next", o => NextLevel(), o => HasNextLevel()));

            for (int i = 1; i <= MainPage.Current.GameViewModel.TotalLevelCount; i++)
            {
                Levels.Add(i);
            }
        }

        public void PreviousLevel()
        {
            MainPage.Current.GameViewModel.PreviousLevel();
            MainPage.Current.NavigateBackToGame();
        }

        public void NextLevel()
        {
            MainPage.Current.GameViewModel.NextLevel();
            MainPage.Current.NavigateBackToGame();
        }

        public void RestartLevel()
        {
            MainPage.Current.GameViewModel.RestartLevel();
            MainPage.Current.NavigateBackToGame();
        }

        public bool HasPreviousLevel()
        {
            return MainPage.Current.GameViewModel.CurrentLevelNumber != 1;
        }

        public bool HasNextLevel()
        {
            return MainPage.Current.GameViewModel.CurrentLevelNumber < MainPage.Current.GameViewModel.TotalLevelCount;
        }
    }
}