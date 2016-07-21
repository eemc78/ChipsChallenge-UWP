namespace ChipsChallenge.Shared
{
    using System;
    using System.Collections.Generic;
    using System.Threading;
    using Features.Settings;

    using Moves = Move.Moves;

    public class Game
    {
        public const int SecondsTickInterval = 1;
        public const int SpeedFrac = 2;
        private readonly ICollection<Block> movingBlocks = new List<Block>();
        private static Game game;
        private readonly Inventory inventory = new Inventory();
        private GameLevel level;
        private LevelFactory mLevelFactory;
        private readonly Timer secondsTimer;
        private long tickCount;
        private bool levelCompleteRenamed;
        public bool IsStarted;
        public bool IsPaused;
        private readonly ICollection<Point> movesToCheck = new List<Point>();
        private volatile bool dead;
        private readonly IDictionary<long?, IList<Block>> addBlockAtTick = new Dictionary<long?, IList<Block>>();
        private readonly IDictionary<Block, Point> addBlocks = new Dictionary<Block, Point>();
        private readonly SlipList slipList = new SlipList();
        private BlockMove chipForced;
        private Block chip;
        public bool ShowLevelPassword;
        private UserSettings userSettings = new UserSettings();

        private Game()
        {
            secondsTimer = new Timer(UpdateTime, null, Timeout.InfiniteTimeSpan, Timeout.InfiniteTimeSpan);
        }

        public static Game Instance
        {
            get
            {
                lock (typeof(Game))
                {
                    return game ?? (game = new Game());
                }
            }
        }

        public event EventHandler<MessageEventArgs> ChipsDied;

        public int TotalLevelCount => mLevelFactory.LastLevelNumber;

        public int CurrentLevelNumber => level.LevelNumber;

        public virtual void ClearStuff()
        {
            tickCount = 0;
            chipForced = null;
            chip = null;
            dead = false;
            Buttons.Clear();
            Creatures.Clear();
            Teleports.Clear();
            slipList.Clear();
            inventory.Clear();
            movingBlocks.Clear();

            IsChipOnHintField = false;
            secondsTimer.Change(Timeout.Infinite, Timeout.Infinite);
        }

        public virtual void NextLevel()
        {
            if (CurrentLevelNumber < mLevelFactory.LastLevelNumber)
            {
                GoToLevel(CurrentLevelNumber + 1);
            }
        }

        public virtual void PreviousLevel()
        {
            if (CurrentLevelNumber > 1)
            {
                GoToLevel(CurrentLevelNumber - 1);
            }
        }

        public virtual void GoToLevel(int levelNumber)
        {
            if (levelNumber > 0 && levelNumber <= mLevelFactory.LastLevelNumber)
            {
                userSettings.LevelNumber = levelNumber;

                ClearStuff();

                level = mLevelFactory.GetLevel(levelNumber);
                chip = level.Chip;

                ShowLevelPassword = true;
                PlayBackgroundMusic();

                IsStarted = false;
                IsPaused = false;
            }
        }

        public void PlayBackgroundMusic()
        {
            if (IsEvenNumber(CurrentLevelNumber))
            {
                SoundPlayer.Instance.Play(Sound.BackgroundMusic1);
            }
            else
            {
                SoundPlayer.Instance.Play(Sound.BackgroundMusic2);
            }
        }

        private bool IsEvenNumber(int value)
        {
            return value % 2 == 0;
        }

        public virtual void Restart()
        {
            dead = false;
            GoToLevel(CurrentLevelNumber);
        }

        public virtual void SetLevelComplete()
        {
            levelCompleteRenamed = true;
        }

        private void LevelComplete()
        {
            levelCompleteRenamed = false;
            NextLevel();
        }

        public virtual void StartOrResumeGame()
        {
            ShowLevelPassword = false;
            IsStarted = true;
            IsPaused = false;

            if (Level.NumSeconds > 0)
            {
                secondsTimer.Change(TimeSpan.Zero, new TimeSpan(0, 0, SecondsTickInterval));
            }
        }

        public virtual void Stop()
        {
            IsStarted = false;
            secondsTimer.Change(Timeout.Infinite, Timeout.Infinite);
        }

        public virtual void Pause()
        {
            if (IsStarted)
            {
                Stop();
                IsPaused = true;
            }
        }

        private void UpdateTime(object o)
        {
            Level.TimeLeft--;

            if (Level.TimeLeft >= 0 && Level.TimeLeft <= 15)
            {
                SoundPlayer.Instance.Play(Sound.Tick);
            }

            if (Level.TimeLeft == 0)
            {
                Die("Ooops! Out of time!", Sound.TimeOver);
            }
        }

        public virtual void AddToSlipList(Block b, Moves m)
        {
            if (b.Chip)
            {
                chipForced = new BlockMove(b, m);
            }
            else
            {
                slipList.Put(b, m);
            }
        }

        public virtual void RemoveFromSlipList(Block b)
        {
            slipList.Remove(b);
        }

        private void AddClonesQueued()
        {
            IList<Block> blocksToAdd;
            addBlockAtTick.TryGetValue(tickCount, out blocksToAdd);
            if (blocksToAdd != null)
            {
                foreach (Block b in blocksToAdd)
                {
                    Point p = addBlocks[b];
                    try
                    {
                        if (level.GetBlockContainer(p.X, p.Y).CanMoveTo(b))
                        {
                            level.AddBlock(p.X, p.Y, b, 2);
                            if (b.Creature)
                            {
                                Creatures.AddCreature(b);
                            }
                        }
                    }
                    catch (BlockContainerFullException)
                    {
                        // Perhaps save cloning for later
                    }

                    addBlocks.Remove(b);
                }

                addBlockAtTick.Remove(tickCount);
            }
        }

        private void ForceCreatures()
        {
            for (int i = 0; i < slipList.Size(); i++)
            {
                BlockMove bm = slipList.Get(i);
                if (bm.Block.Creature || bm.Block.IsBlock())
                {
                    ForceMove(bm.Block, bm.Move);
                }
            }
        }

        private void ForceMove(Block b, Moves m)
        {
            if (!level.MoveBlock(b, m, !b.OnTrap, false))
            {
                if (!b.OnTrap)
                {
                    // Bounce
                    b.Facing = Move.Reverse(m);
                    if (!b.Chip)
                    {
                        RemoveFromSlipList(b);
                    }

                    b.Forced = false;
                    level.MoveBlock(b, null, true, true);
                }
            }
        }

        public virtual bool ForceChip()
        {
            if (chipForced != null)
            {
                BlockMove cm = chipForced.Clone();
                chipForced = null;
                ForceMove(cm.Block, cm.Move);
                level.Chip.Forced = true;
                return true;
            }

            return false;
        }

        // Main "loop"
        public virtual void Tick()
        {
            if (dead || !IsStarted)
            {
                return;
            }

            var didTick = false;
            if (ForceChip())
            {
                chip.Tick();
                didTick = true;
            }

            tickCount++;

            if (!didTick)
            {
                chip.Tick();
                if (dead)
                {
                    return;
                }
            }

            Creatures.Tick();

            Buttons.UpdateGreenandBlueButtons();

            ForceCreatures();
            AddClonesQueued();

            if (levelCompleteRenamed)
            {
                LevelComplete();
            }
        }

        public virtual void Die(string message, Sound sound)
        {
            if (!dead)
            {
                dead = true;
                secondsTimer?.Change(Timeout.InfiniteTimeSpan, Timeout.InfiniteTimeSpan);

                EventHandler<MessageEventArgs> handler = ChipsDied;
                handler?.Invoke(this, new MessageEventArgs(message));

                SoundPlayer.Instance.Play(sound);
            }
        }

        public virtual GameLevel Level
        {
            get
            {
                return level;
            }

            set
            {
                level = value;
            }
        }

        public virtual Inventory Inventory => inventory;

        public virtual BlockFactory BlockFactory { get; set; }

        public virtual LevelFactory LevelFactory
        {
            set
            {
                mLevelFactory = value;
            }
        }

        public virtual void MoveOccured(Point from)
        {
            movesToCheck.Add(from);
        }

        public bool IsChipOnHintField { get; set; }

        public virtual void AddBlockDelay(Block b, Point p, int ticks)
        {
            long addWhen = tickCount + ticks;

            IList<Block> blocks;
            addBlockAtTick.TryGetValue(addWhen, out blocks);
            if (blocks == null)
            {
                blocks = new List<Block>();
                addBlockAtTick[addWhen] = blocks;
            }

            blocks.Add(b);
            addBlocks[b] = p;
        }

        public virtual void TakeChip()
        {
            level.ChipTaken();
        }

        public virtual Block Chip
        {
            set
            {
                chip = value;
            }
        }
    }
}