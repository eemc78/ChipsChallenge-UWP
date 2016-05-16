using System;
using System.Collections.Generic;
using System.Text;

namespace ChipsChallenge.Shared
{
    using Moves = Move.Moves;

    public class GameLevel
    {
        private volatile BlockContainer[][] mBoard;
        private volatile IDictionary<Block, Point> blocks = new Dictionary<Block, Point>();
        private volatile Block chip;
        private int numChipsNeeded;
        private int numSeconds;
        private int timeLeft;
        private int levelNumber;
        private int deaths;

        public virtual string Hint { get; set; } = "No hint";

        public GameLevel(int width, int height)
        {
            mBoard = RectangularArrays.ReturnRectangularBlockContainerArray(width, height);
            for (int i = 0; i < width; i++)
            {
                for (int j = 0; j < height; j++)
                {
                    mBoard[i][j] = new BlockContainer();
                }
            }
        }

        public GameLevel(int width, int height, int numChipsNeeded, int numSeconds, int levelNumber) : this(width, height)
        {
            this.numChipsNeeded = numChipsNeeded;
            this.numSeconds = numSeconds;
            TimeLeft = NumSeconds;
            this.levelNumber = levelNumber;
        }

        public virtual void AddBlock(int x, int y, Block b, int layer)
        {
            BlockContainer bc = GetBlockContainer(x, y);
            if (bc != null)
            {
                if (b.Chip)
                {
                    chip = b;
                }

                if (b.IsA(Block.Type.TELEPORT))
                {
                    Teleports.AddTeleport(x, y);
                }

                switch (layer)
                {
                    case 0:
                        bc.Lower = b;
                        break;
                    case 1:
                        bc.Upper = b;
                        break;
                    default:
                        bc.Add(b);
                        break;
                }

                blocks[b] = new Point(x, y);
            }
        }

        public virtual Point GetPoint(Block b)
        {
            var point = new Point(blocks[b].X, blocks[b].Y);
            return point;
        }

        public virtual BlockContainer GetBlockContainer(int x, int y)
        {
            if (x < 0 || x >= Width || y < 0 || y >= Height)
            {
                return null;
            }

            return mBoard[x][y];
        }

        public virtual BlockContainer GetBlockContainer(Block b)
        {
            var point = GetPoint(b);

            return GetBlockContainer(point.X, point.Y);
        }

        public virtual BlockContainer GetBlockContainer(Block b, Moves direction)
        {
            var point = new Point(blocks[b].X, blocks[b].Y);
            Move.UpdatePoint(ref point, direction);
            return GetBlockContainer(point.X, point.Y);
        }

        public virtual int Width => mBoard.Length;

        public virtual int Height => mBoard[0].Length;

        // Should be used for true teleportation ONLY
        public virtual bool Teleport(Block b, Point to)
        {
            Point from = blocks[b];
            if (mBoard[to.X][to.Y].CanMoveTo(b))
            {
                mBoard[from.X][from.Y].Remove(b);
                blocks[b] = to;
                mBoard[to.X][to.Y].Add(b);
                return true;
            }

            return false;
        }

        public virtual bool MoveBlock(Block b, Moves? direction, bool ignoreFrom, bool ignoreTo)
        {
            Game g = Game.Instance;
            Point from = blocks[b];
            Point to = new Point(from.X, from.Y);
            if (direction != null)
            {
                Move.UpdatePoint(ref to, (Moves)direction);
            }

            if (!b.Chip)
            {
                g.MoveOccured(from);
                g.MoveOccured(to);
            }

            if (direction != null && (ignoreFrom || (!b.OnIce || b.ChipWithIceSkates) && !b.OnCloner))
            {
                b.Facing = (Moves)direction;
            }

            if (to.X < 0 || to.X >= Width || to.Y < 0 || to.Y >= Height)
            {
                return false;
            }

            if (ignoreFrom || mBoard[from.X][from.Y].CanMoveFrom(b))
            {
                if (ignoreTo || mBoard[to.X][to.Y].CanMoveTo(b))
                {
                    // From reactions
                    mBoard[from.X][from.Y].MoveFrom(b);

                    // Actual movement
                    mBoard[from.X][from.Y].Remove(b);
                    blocks[b] = to;
                    mBoard[to.X][to.Y].Push(b);

                    // Add or remove from sliplist
                    Moves? m;
                    if ((m = mBoard[to.X][to.Y].CausesSlipTo(b)) != null)
                    {
                        g.AddToSlipList(b, (Moves)m);
                        b.Forced = true;
                    }
                    else
                    {
                        if (b.Forced && !b.OnTrap)
                        {
                            g.RemoveFromSlipList(b);
                            b.Forced = false;
                        }
                    }

                    // To reactions
                    mBoard[to.X][to.Y].MoveTo(b);

                    return true;
                }
            }

            return false;
        }

        public virtual void RemoveBlock(Block b)
        {
            GetBlockContainer(b).Remove(b);
            if (b.getType() != Block.Type.CHIP)
            {
                blocks.Remove(b);
            }
        }

        public virtual void ReplaceBlock(Block a, Block b)
        {
            var point = new Point(blocks[a].X, blocks[a].Y);
            GetBlockContainer(point.X, point.Y).ReplaceBlock(a, b);
            blocks[b] = point;
        }

        public virtual Point FindChip()
        {
            return blocks[chip];
        }

        public virtual bool Contains(Block b)
        {
            return blocks.ContainsKey(b);
        }

        public virtual void Die()
        {
            deaths++;
            timeLeft = NumSeconds;
        }

        public virtual int NumDeaths => deaths;

        //TODO: Store totalscore and best time, and output message.
        public virtual string Score
        {
            get
            {
                var sb = new StringBuilder();
                switch (deaths)
                {
                    case 0:
                        sb.Append("Yowser! First try!");
                        break;
                    case 1:
                    case 2:
                        sb.Append("Go Bit Buster!");
                        break;
                    case 3:
                    case 4:
                        sb.Append("Finished! Good Work!");
                        break;
                    default:
                        sb.Append("At last! You did it!");
                        break;
                }
                sb.Append("\n\n");
                double timeBonus = numSeconds * 10;
                sb.Append("Time Bonus: " + timeBonus);
                sb.Append("\n\n");
                double levelBonus = Math.Floor(levelNumber * 500 * Math.Pow(0.8, deaths));
                levelBonus = Math.Max(500, levelBonus);
                sb.Append("Level Bonus: " + levelBonus);
                sb.Append("\n\n");
                double levelScore = timeBonus + levelBonus;
                sb.Append("Level Score: " + levelScore);
                return sb.ToString();
            }
        }

        public virtual int LevelNumber
        {
            get
            {
                return levelNumber;
            }

            set
            {
                levelNumber = value;
            }
        }

        public virtual int NumChipsNeeded
        {
            get
            {
                return numChipsNeeded;
            }

            set
            {
                numChipsNeeded = value;
            }
        }

        public virtual int NumSeconds
        {
            get
            {
                return numSeconds;
            }

            set
            {
                numSeconds = value;
            }
        }

        public virtual string Password { get; set; } = "ABCD";

        public virtual int ChipsLeft
        {
            get
            {
                return numChipsNeeded;
            }

            set
            {
                numChipsNeeded = value;
            }
        }
        
        public virtual int TimeLeft
        {
            get
            {
                return timeLeft;
            }

            set
            {
                timeLeft = value;
            }
        }

        public virtual void ChipTaken()
        {
            numChipsNeeded = numChipsNeeded > 0 ? numChipsNeeded - 1 : 0;
        }

        public virtual int Deaths
        {
            set
            {
                deaths = value;
            }
        }

        public virtual string MapTitle { set; get; } = "Untitled";

        public virtual Block Chip => chip;
    }
}