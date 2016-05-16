namespace ChipsChallenge.Features.Levels
{
    using System.Collections.ObjectModel;

    public class LevelsViewModel
    {
        public ObservableCollection<int> Levels { get; } = new ObservableCollection<int>();

        public LevelsViewModel()
        {
            for (int i = 1; i <= MainPage.Current.GameViewModel.TotalLevelCount; i++)
            {
                Levels.Add(i);
            }
        }
    }
}