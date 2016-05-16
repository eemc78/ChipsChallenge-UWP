namespace ChipsChallenge.Shared
{
    using System;

    public class AudioArgs : EventArgs
    {
        public Sound Sound { set; get; }
    }

    public class SoundPlayer
    {
        public event AudioHandler AudioRequestReceived;

        public delegate void AudioHandler(object sender, AudioArgs args);

        private SoundPlayer()
        {
        }

        private static SoundPlayer instance;

        public static SoundPlayer Instance
        {
            get
            {
                lock (typeof(SoundPlayer))
                {
                    return instance ?? (instance = new SoundPlayer());
                }
            }
        }

        public virtual void Play(Sound sound)
        {
            var audioArgs = new AudioArgs { Sound = sound };
            OnAudioRequestReceived(audioArgs);
        }

        protected virtual void OnAudioRequestReceived(AudioArgs args)
        {
            AudioRequestReceived?.Invoke(this, args);
        }
    }
}