namespace ChipsChallenge.Features.Settings
{
    using ChipsChallenge.Features.Game;

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
    }
}