using Windows.ApplicationModel;

namespace ChipsChallenge.Features.Settings
{
    using Game;

    public class SettingsViewModel
    {
        private AudioPlayer AudioPlayer => GameViewModel.AudioPlayer;

        public GameViewModel GameViewModel => MainPage.Current.GameViewModel;

        public bool SoundEffects
        {
            get
            {
                return AudioPlayer.SoundEffects;
            }
            set
            {
                AudioPlayer.SoundEffects = value;
            }
        }

        public bool BackgroundMusic
        {
            get
            {
                return AudioPlayer.BackgroundMusic;
            }
            set
            {
                AudioPlayer.BackgroundMusic = value;

                if (AudioPlayer.BackgroundMusic)
                {
                    GameViewModel.StartBackgroundMusic();
                }
            }
        }

        public string Version
        {
            get
            {
                Package package = Package.Current;
                PackageId packageId = package.Id;
                PackageVersion version = packageId.Version;

                return $"Chip's Challenge {version.Major}.{version.Minor}.{version.Build}.{version.Revision}";
            }
        }
    }
}