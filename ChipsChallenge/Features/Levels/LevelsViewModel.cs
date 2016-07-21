using System.Linq;

namespace ChipsChallenge.Features.Levels
{
    using System.Collections.ObjectModel;

    using Windows.UI.Xaml.Controls;

    public class LevelsViewModel
    {
        public ObservableCollection<Level> Levels { get; } = new ObservableCollection<Level>();

        public ObservableCollection<LevelControl> LevelControls { get; } = new ObservableCollection<LevelControl>();

        public void Initialize()
        {
            LevelControls.Add(new LevelControl(Symbol.Previous, "Previous", _ => PreviousLevel(), _ => HasPreviousLevel()));
            LevelControls.Add(new LevelControl(Symbol.Refresh, "Restart", _ => RestartLevel(), _ => true));
            LevelControls.Add(new LevelControl(Symbol.Next, "Next", o => NextLevel(), o => HasNextLevel()));

            var currentLevelNumber = MainPage.Current.GameViewModel.CurrentLevelNumber;
            for (int levelNumber = 1; levelNumber <= MainPage.Current.GameViewModel.TotalLevelCount; levelNumber++)
            {
                var isActive = levelNumber == currentLevelNumber;
                var level = new Level(levelNumber, isActive);
                Levels.Add(level);
            }
        }

        public void Refresh()
        {
            Levels.Single(x => x.IsActive).IsActive = false;
            Levels.Single(x => x.Number == MainPage.Current.GameViewModel.CurrentLevelNumber).IsActive = true;
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