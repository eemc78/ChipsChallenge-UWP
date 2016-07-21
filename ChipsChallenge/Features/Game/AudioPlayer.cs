namespace ChipsChallenge.Features.Game
{
    using System;
    using System.Collections.Concurrent;
    using System.Collections.Generic;
    using System.Threading.Tasks;

    using Shared;
    using Settings;

    using Windows.Media.Audio;
    using Windows.Media.Render;
    using Windows.Storage;

    public class AudioPlayer
    {
        private readonly ConcurrentDictionary<string, AudioFileInputNode> fileInputs = new ConcurrentDictionary<string, AudioFileInputNode>();
        private readonly Dictionary<Sound, string> soundMappings;
        private AudioGraph graph;
        private AudioDeviceOutputNode deviceOutput;
        private bool isInitialized;
        private readonly UserSettings userSettings = new UserSettings();

        public AudioPlayer()
        {
            SoundPlayer.Instance.AudioRequestReceived += InstanceOnAudioRequestReceived;

            soundMappings = new Dictionary<Sound, string>
                                {
                                    { Sound.BackgroundMusic1, "CHIP01.mp3" },
                                    { Sound.BackgroundMusic2, "CHIP02.mp3" },
                                    { Sound.TakeItem, "BLIP2.WAV" },
                                    { Sound.Bomb, "HIT3.WAV" },
                                    { Sound.ChipHum, "OOF3.WAV" },
                                    { Sound.Door, "DOOR.WAV" },
                                    { Sound.Exit, "DITTY1.WAV" },
                                    { Sound.Teleport, "TELEPORT.WAV" },
                                    { Sound.Water, "WATER2.WAV" },
                                    { Sound.Die, "BUMMER.WAV" },
                                    { Sound.TakeChip, "CLICK3.WAV" },
                                    { Sound.Tick, "CLICK1.WAV" },
                                    { Sound.Button, "POP2.WAV" },
                                    { Sound.Thief, "STRIKE.WAV" },
                                    { Sound.TimeOver, "BELL.WAV" },
                                    { Sound.Socket, "DOOR.WAV" }
                                };
        }

        public bool SoundEffects {
            get { return userSettings.SoundEffects; }
            set
            {
                userSettings.SoundEffects = value;
            }
        }

        public bool BackgroundMusic
        {
            get
            {
                return userSettings.BackgroundMusic;
            }
            set
            {
                userSettings.BackgroundMusic = value;

                if (!userSettings.BackgroundMusic)
                {
                    StopAllBackgroundMusic();
                }
            }
        }

        public async Task<bool> InitializeSound()
        {
            AudioGraphSettings settings = new AudioGraphSettings(AudioRenderCategory.Media);
            CreateAudioGraphResult result = await AudioGraph.CreateAsync(settings);

            if (result.Status != AudioGraphCreationStatus.Success)
            {
                return false;
            }

            graph = result.Graph;
            CreateAudioDeviceOutputNodeResult deviceOutputNodeResult = await graph.CreateDeviceOutputNodeAsync();

            if (deviceOutputNodeResult.Status == AudioDeviceNodeCreationStatus.Success)
            {
                deviceOutput = deviceOutputNodeResult.DeviceOutputNode;
                graph.ResetAllNodes();

                foreach (var soundMapping in soundMappings)
                {
                    await AddFileToSoundDictionary("ms-appx:///Features/Game/Assets/" + soundMapping.Value);
                }

                graph.Start();
            }

            isInitialized = true;
            return isInitialized;
        }

        private void InstanceOnAudioRequestReceived(object sender, AudioArgs args)
        {
            var audioFile = fileInputs[soundMappings[args.Sound]];
            audioFile.Stop();
            audioFile.Reset();

            if (args.Sound == Sound.BackgroundMusic1 || args.Sound == Sound.BackgroundMusic2)
            {
                StopAllBackgroundMusic();
                audioFile.LoopCount = int.MaxValue;

                if (BackgroundMusic)
                {
                    audioFile.Start();
                }
            }
            else
            {
                if (SoundEffects)
                {
                    audioFile.Start();
                }
            }
        }

        private void StopAllBackgroundMusic()
        {
            if (!isInitialized)
            {
                return;
            }

            fileInputs[soundMappings[Sound.BackgroundMusic1]].Stop();
            fileInputs[soundMappings[Sound.BackgroundMusic2]].Stop();
        }

        private async Task AddFileToSoundDictionary(string uri)
        {
            StorageFile soundFile = await StorageFile.GetFileFromApplicationUriAsync(new Uri(uri));
            CreateAudioFileInputNodeResult fileInputResult = await graph.CreateFileInputNodeAsync(soundFile);

            if (AudioFileNodeCreationStatus.Success == fileInputResult.Status)
            {
                fileInputs.TryAdd(soundFile.Name, fileInputResult.FileInputNode);
                fileInputResult.FileInputNode.Stop();
                fileInputResult.FileInputNode.AddOutgoingConnection(deviceOutput);
            }
        }
    }
}