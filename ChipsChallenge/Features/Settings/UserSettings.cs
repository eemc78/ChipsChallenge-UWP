
using Windows.Foundation.Collections;

namespace ChipsChallenge.Features.Settings
{
    using Windows.Storage;

    public class UserSettings
    {
        private const string LevelNumberKey = nameof(LevelNumber);
        private const string BackgroundMusicKey = nameof(BackgroundMusic);
        private const string SoundEffectsKey = nameof(SoundEffects);
        private readonly IPropertySet propertySet = ApplicationData.Current.LocalSettings.Values;

        public int LevelNumber
        {
            get
            {
                return (int)(propertySet[LevelNumberKey] ?? 1);
            }

            set
            {
                propertySet[LevelNumberKey] = value;
            }
        }

        public bool SoundEffects
        {
            get
            {
                return (bool)(propertySet[SoundEffectsKey] ?? true);
            }

            set
            {
                propertySet[SoundEffectsKey] = value;
            }
        }

        public bool BackgroundMusic
        {
            get
            {
                return (bool)(propertySet[BackgroundMusicKey] ?? false);
            }

            set
            {
                propertySet[BackgroundMusicKey] = value;
            }
        }
    }
}