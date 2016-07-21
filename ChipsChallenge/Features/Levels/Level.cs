namespace ChipsChallenge.Features.Levels
{
    using PropertyChanged;

    using System;

    [ImplementPropertyChanged]
    public class Level
    {
        public Level(int levelNumber, bool isActive)
        {
            Number = levelNumber;
            IsActive = isActive;
            var formattedLevelNumber = levelNumber.ToString().PadLeft(3, '0');
            Image = new Uri($"ms-appx:///Features/Levels/Previews/{formattedLevelNumber}.png");
        }

        public int Number { get; private set; }

        public bool IsActive { get; set; }

        public Uri Image { get; private set; }
    }
}