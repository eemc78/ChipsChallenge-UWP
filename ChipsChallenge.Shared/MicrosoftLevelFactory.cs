using System;
using System.Collections.Generic;

namespace ChipsChallenge.Shared
{
    using System.Diagnostics;

    using Type = Block.Type;
    using Moves = Move.Moves;

    /// <summary>
    /// Level factory which uses CHIPS.DAT to create levels.
    /// </summary>
    public class MicrosoftLevelFactory : LevelFactory
    {
        private readonly int levelCount = -1;
        private readonly IDictionary<int?, int?> levelOffsets = new Dictionary<int?, int?>();
        private readonly IDictionary<string, int?> passwordToLevel = new Dictionary<string, int?>();
        private readonly IDictionary<int?, string> levelToPassword = new Dictionary<int?, string>();
        private readonly ILevelFileReader chipDat;

        private Block GetBlockByObjectCode(int objectCode)
        {
            MicrosoftBlockFactory f = MicrosoftBlockFactory.Instance;
            switch (objectCode)
            {
                case 0x00:
                    return f.Get(Type.FLOOR);
                case 0x01:
                    return f.Get(Type.WALL);
                case 0x02:
                    return f.Get(Type.COMPUTERCHIP);
                case 0x03:
                    return f.Get(Type.WATER);
                case 0x04:
                    return f.Get(Type.FIRE);
                case 0x05:
                    return f.Get(Type.INVISIBLEWALL);
                case 0x06:
                    return f.Get(Type.THINWALL, Moves.UP);
                case 0x07:
                    return f.Get(Type.THINWALL, Moves.LEFT);
                case 0x08:
                    return f.Get(Type.THINWALL, Moves.DOWN);
                case 0x09:
                    return f.Get(Type.THINWALL, Moves.RIGHT);
                case 0x0A:
                    return f.Get(Type.BLOCK);
                case 0x0B:
                    return f.Get(Type.DIRT);
                case 0x0C:
                    return f.Get(Type.ICE);
                case 0x0D:
                    return f.Get(Type.FORCEFLOOR, Moves.DOWN);
                case 0x0E:
                    return f.Get(Type.BLOCK, Moves.UP); // Cloning block UP
                case 0x0F:
                    return f.Get(Type.BLOCK, Moves.LEFT); // Cloning block LEFT
                case 0x10:
                    return f.Get(Type.BLOCK, Moves.DOWN); // Cloning block DOWN
                case 0x11:
                    return f.Get(Type.BLOCK, Moves.RIGHT); // Cloning block RIGHT
                case 0x12:
                    return f.Get(Type.FORCEFLOOR, Moves.UP);
                case 0x13:
                    return f.Get(Type.FORCEFLOOR, Moves.RIGHT);
                case 0x14:
                    return f.Get(Type.FORCEFLOOR, Moves.LEFT);
                case 0x15:
                    return f.Get(Type.EXIT);
                case 0x16:
                    return f.Get(Type.BLUELOCK);
                case 0x17:
                    return f.Get(Type.REDLOCK);
                case 0x18:
                    return f.Get(Type.GREENLOCK);
                case 0x19:
                    return f.Get(Type.YELLOWLOCK);
                case 0x1A:
                    return f.Get(Type.ICECORNER, Moves.UP);
                case 0x1B:
                    return f.Get(Type.ICECORNER, Moves.LEFT);
                case 0x1C:
                    return f.Get(Type.ICECORNER, Moves.DOWN);
                case 0x1D:
                    return f.Get(Type.ICECORNER, Moves.RIGHT);
                case 0x1E:
                    return f.Get(Type.BLUEWALLFAKE);
                case 0x1F:
                    return f.Get(Type.BLUEWALLREAL);
                case 0x20:
                    return null; // NOT USED
                case 0x21:
                    return f.Get(Type.THIEF);
                case 0x22:
                    return f.Get(Type.SOCKET);
                case 0x23:
                    return f.Get(Type.GREENBUTTON);
                case 0x24:
                    return f.Get(Type.REDBUTTON);
                case 0x25:
                    return f.Get(Type.TOGGLEWALLCLOSED);
                case 0x26:
                    return f.Get(Type.TOGGLEWALLOPEN);
                case 0x27:
                    return f.Get(Type.BROWNBUTTON);
                case 0x28:
                    return f.Get(Type.BLUEBUTTON);
                case 0x29:
                    return f.Get(Type.TELEPORT);
                case 0x2A:
                    return f.Get(Type.BOMB);
                case 0x2B:
                    return f.Get(Type.TRAP);
                case 0x2C:
                    return f.Get(Type.HIDDENWALL);
                case 0x2D:
                    return f.Get(Type.GRAVEL);
                case 0x2E:
                    return f.Get(Type.RECESSEDWALL);
                case 0x2F:
                    return f.Get(Type.HINT);
                case 0x30:
                    return f.Get(Type.THINWALLSE);
                case 0x31:
                    return f.Get(Type.CLONEMACHINE);
                case 0x32:
                    return f.Get(Type.RANDOMFORCEFLOOR);
                case 0x33:
                    return f.Get(Type.DROWNEDCHIP);
                case 0x34:
                    return f.Get(Type.BURNEDCHIP);
                case 0x35:
                    return f.Get(Type.BURNEDCHIP); // Overlay
                case 0x36:
                    return null; // Not used
                case 0x37:
                    return null; // Not used
                case 0x38:
                    return null; // Not used
                case 0x39:
                    return null; // Chip in Exit
                case 0x3A:
                    return f.Get(Type.EXIT); // Exit-End Game
                case 0x3B:
                    return f.Get(Type.EXIT); // Exit-End Game
                case 0x3C:
                    return f.Get(Type.SWIMMINGCHIP, Moves.UP);
                case 0x3D:
                    return f.Get(Type.SWIMMINGCHIP, Moves.LEFT);
                case 0x3E:
                    return f.Get(Type.SWIMMINGCHIP, Moves.DOWN);
                case 0x3F:
                    return f.Get(Type.SWIMMINGCHIP, Moves.RIGHT);
                case 0x40:
                    return f.Get(Type.BUG, Moves.UP);
                case 0x41:
                    return f.Get(Type.BUG, Moves.LEFT);
                case 0x42:
                    return f.Get(Type.BUG, Moves.DOWN);
                case 0x43:
                    return f.Get(Type.BUG, Moves.RIGHT);
                case 0x44:
                    return f.Get(Type.FIREBALL, Moves.UP);
                case 0x45:
                    return f.Get(Type.FIREBALL, Moves.LEFT);
                case 0x46:
                    return f.Get(Type.FIREBALL, Moves.DOWN);
                case 0x47:
                    return f.Get(Type.FIREBALL, Moves.RIGHT);
                case 0x48:
                    return f.Get(Type.PINKBALL, Moves.UP);
                case 0x49:
                    return f.Get(Type.PINKBALL, Moves.LEFT);
                case 0x4A:
                    return f.Get(Type.PINKBALL, Moves.DOWN);
                case 0x4B:
                    return f.Get(Type.PINKBALL, Moves.RIGHT);
                case 0x4C:
                    return f.Get(Type.TANK, Moves.UP);
                case 0x4D:
                    return f.Get(Type.TANK, Moves.LEFT);
                case 0x4E:
                    return f.Get(Type.TANK, Moves.DOWN);
                case 0x4F:
                    return f.Get(Type.TANK, Moves.RIGHT);
                case 0x50:
                    return f.Get(Type.GLIDER, Moves.UP);
                case 0x51:
                    return f.Get(Type.GLIDER, Moves.LEFT);
                case 0x52:
                    return f.Get(Type.GLIDER, Moves.DOWN);
                case 0x53:
                    return f.Get(Type.GLIDER, Moves.RIGHT);
                case 0x54:
                    return f.Get(Type.TEETH, Moves.UP);
                case 0x55:
                    return f.Get(Type.TEETH, Moves.LEFT);
                case 0x56:
                    return f.Get(Type.TEETH, Moves.DOWN);
                case 0x57:
                    return f.Get(Type.TEETH, Moves.RIGHT);
                case 0x58:
                    return f.Get(Type.WALKER, Moves.UP);
                case 0x59:
                    return f.Get(Type.WALKER, Moves.LEFT);
                case 0x5A:
                    return f.Get(Type.WALKER, Moves.DOWN);
                case 0x5B:
                    return f.Get(Type.WALKER, Moves.RIGHT);
                case 0x5C:
                    return f.Get(Type.BLOB, Moves.UP);
                case 0x5D:
                    return f.Get(Type.BLOB, Moves.LEFT);
                case 0x5E:
                    return f.Get(Type.BLOB, Moves.DOWN);
                case 0x5F:
                    return f.Get(Type.BLOB, Moves.RIGHT);
                case 0x60:
                    return f.Get(Type.PARAMECIUM, Moves.UP);
                case 0x61:
                    return f.Get(Type.PARAMECIUM, Moves.LEFT);
                case 0x62:
                    return f.Get(Type.PARAMECIUM, Moves.DOWN);
                case 0x63:
                    return f.Get(Type.PARAMECIUM, Moves.RIGHT);
                case 0x64:
                    return f.Get(Type.BLUEKEY);
                case 0x65:
                    return f.Get(Type.REDKEY);
                case 0x66:
                    return f.Get(Type.GREENKEY);
                case 0x67:
                    return f.Get(Type.YELLOWKEY);
                case 0x68:
                    return f.Get(Type.FLIPPERS);
                case 0x69:
                    return f.Get(Type.FIREBOOTS);
                case 0x6A:
                    return f.Get(Type.ICESKATES);
                case 0x6B:
                    return f.Get(Type.SUCTIONBOOTS);
                case 0x6C:
                    return f.Get(Type.CHIP, Moves.UP);
                case 0x6D:
                    return f.Get(Type.CHIP, Moves.LEFT);
                case 0x6E:
                    return f.Get(Type.CHIP, Moves.DOWN);
                case 0x6F:
                    return f.Get(Type.CHIP, Moves.RIGHT);
            }

            return null;
        }

        public MicrosoftLevelFactory(ILevelFileReader lfr)
        {
            chipDat = lfr;
            int magicNumber = chipDat.ReadUnsignedDWord();
            if (magicNumber != 0x0002AAAC)
            {
                throw new Exception("Couldn't parse file");
            }
                
            levelCount = chipDat.ReadUnsignedWord();
            levelOffsets[1] = 6;
        }

        public override GameLevel GetLevel(int n)
        {
            if (n < 1 || n > levelCount)
            {
                throw new ArgumentException("Level outside of range");
            }

            GameLevel gameLevel = null;
            try
            {
                int offset = GetLevelOffset(n);
                chipDat.Seek(offset);
                int numBytesLevel = chipDat.ReadUnsignedWord();

                // So we don't have to skip over the same level again later
                levelOffsets[n + 1] = offset + numBytesLevel + 2;

                int levelNumber = chipDat.ReadUnsignedWord();
                int numSeconds = chipDat.ReadUnsignedWord();
                int numChipsNeeded = chipDat.ReadUnsignedWord();
                int mapDetail = chipDat.ReadUnsignedWord();

                gameLevel = new GameLevel(32, 32, numChipsNeeded, numSeconds, levelNumber);

                int numberOfBytesLayer1 = chipDat.ReadUnsignedWord();
                ReadLayer(gameLevel, numberOfBytesLayer1, 1); // Layer 1, upper

                int numberOfBytesLayer2 = chipDat.ReadUnsignedWord();
                ReadLayer(gameLevel, numberOfBytesLayer2, 0); // Layer 2, lower
                int numBytesOptional = chipDat.ReadUnsignedWord();
                int numOptionalBytesRead = 0;

                while (numOptionalBytesRead < numBytesOptional)
                {
                    int fieldType = chipDat.ReadUnsignedByte();
                    int sizeOfField = chipDat.ReadUnsignedByte();
                    numOptionalBytesRead += 2;

                    switch (fieldType)
                    {
                        case 0x03: // Map title
                            sbyte[] ASCIITitle = new sbyte[sizeOfField - 1];
                            chipDat.ReadFully(ASCIITitle);
                            string title = StringHelperClass.NewString(ASCIITitle, "ASCII");
                            gameLevel.MapTitle = title;
                            chipDat.SkipBytes(1);
                            break;

                        case 0x04: // Brown buttons to traps
                            for (int i = 0; i < sizeOfField / 10; i++)
                            {
                                int buttonX = chipDat.ReadUnsignedWord();
                                int buttonY = chipDat.ReadUnsignedWord();
                                int trapX = chipDat.ReadUnsignedWord();
                                int trapY = chipDat.ReadUnsignedWord();

                                // Locate button
                                BlockContainer bc = gameLevel.GetBlockContainer(buttonX, buttonY);
                                Block button = bc.Find(Type.BROWNBUTTON);

                                // Locate trap
                                bc = gameLevel.GetBlockContainer(trapX, trapY);
                                Block trap = bc.Find(Type.TRAP);

                                if (button != null && trap != null) // Perhaps throw an exception otherwise
                                {
                                    Buttons.AddBrownButtonListener(button, trap);
                                }

                                chipDat.SkipBytes(2);
                            }
                            break;

                        case 0x05:
                            for (int i = 0; i < sizeOfField / 8; i++)
                            {
                                int buttonX = chipDat.ReadUnsignedWord();
                                int buttonY = chipDat.ReadUnsignedWord();
                                int clonerX = chipDat.ReadUnsignedWord();
                                int clonerY = chipDat.ReadUnsignedWord();

                                // Locate button
                                BlockContainer bc = gameLevel.GetBlockContainer(buttonX, buttonY);
                                Block button = bc.Find(Type.REDBUTTON);

                                // Locate cloner
                                bc = gameLevel.GetBlockContainer(clonerX, clonerY);
                                Block cloner = bc.Find(Type.CLONEMACHINE);

                                if (button != null && cloner != null) // Perhaps throw an exception otherwise
                                {
                                    Buttons.AddRedButtonListener(button, cloner);
                                }
                            }
                            break;

                        case 0x06: // Password
                            string password = ReadPassword(sizeOfField);
                            gameLevel.Password = password;
                            passwordToLevel[password] = n;
                            levelToPassword[n] = password;
                            chipDat.SkipBytes(1);
                            break;

                        case 0x07: // Hint
                            sbyte[] ASCIIHint = new sbyte[sizeOfField - 1];
                            chipDat.ReadFully(ASCIIHint);
                            string hint = StringHelperClass.NewString(ASCIIHint, "ASCII");
                            gameLevel.Hint = hint;
                            chipDat.SkipBytes(1);
                            break;

                        case 0x0A: // Movement
                            for (int i = 0; i < sizeOfField / 2; i++)
                            {
                                int creatureX = chipDat.ReadUnsignedByte();
                                int creatureY = chipDat.ReadUnsignedByte();
                                Block creature = null;
                                BlockContainer bc;
                                Block upper;

                                // Locate creature
                                bc = gameLevel.GetBlockContainer(creatureX, creatureY);
                                upper = bc.Upper;
                                if (upper.Creature)
                                {
                                    creature = upper;
                                }

                                if (creature != null) // Perhaps throw an exception otherwise
                                {
                                    Creatures.AddCreature(creature);
                                }

                            }
                            break;
                        default:
                            chipDat.SkipBytes(sizeOfField);
                        break;
                    }

                    numOptionalBytesRead += sizeOfField;
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex.ToString());
                Debug.Write(ex.StackTrace);
                Debug.WriteLine("While loading level: " + ex.Message);
            }

            return gameLevel;
        }

        private string ReadPassword(int length)
        {
            sbyte[] ASCIIPassword = new sbyte[length - 1];
            chipDat.ReadFully(ASCIIPassword);
            for (int i = 0; i < ASCIIPassword.Length; i++)
            {
                ASCIIPassword[i] ^= unchecked((sbyte)0x99);
            }
            string password = StringHelperClass.NewString(ASCIIPassword, "ASCII");
            return password;
        }

        public virtual void ReadLayer(GameLevel ret, int numberOfBytes, int layer)
        {
            int width = ret.Width;
            int height = ret.Height;
            int bytesRead = 0;
            int objectsPlaced = 0;
            while (bytesRead < numberOfBytes)
            {
                int read = chipDat.ReadUnsignedByte();
                bytesRead++;
                if (read >= 0x00 && read <= 0x6F)
                {
                    ret.AddBlock(objectsPlaced % width, objectsPlaced / width, GetBlockByObjectCode(read), layer);
                    objectsPlaced++;
                }
                else if (read == 0xFF)
                {
                    int numRepeats = chipDat.ReadUnsignedByte();
                    int data = chipDat.ReadUnsignedByte();
                    bytesRead += 2;
                    while (numRepeats-- > 0)
                    {
                        ret.AddBlock(objectsPlaced % width, objectsPlaced / width, GetBlockByObjectCode(data), layer);
                        objectsPlaced++;
                    }
                }
                else
                {
                    Debug.WriteLine("Object I couldn't parse: " + read);
                }
            }
        }

        public override int LastLevelNumber => levelCount;

        public virtual int GetLevelOffset(int n)
        {
            int? offset;
            levelOffsets.TryGetValue(n, out offset);
            if (offset != null)
            {
                return offset.Value;
            }
            int level = n - 1;
            while (level >= 1)
            {
                levelOffsets.TryGetValue(level, out offset);
                if (offset != null)
                {
                    break;
                }
                level--;
            }
            chipDat.Seek(Convert.ToInt64(offset));
            while (level < n)
            {
                int numBytesInLevel = chipDat.ReadUnsignedWord();
                offset = offset + numBytesInLevel + 2;
                levelOffsets[level + 1] = offset;
                level++;
                if (level == n)
                {
                    break;
                }
                chipDat.SkipBytes(numBytesInLevel);
            }
            return offset.Value;
        }

        public virtual string GetLevelPassword(int n)
        {
            string pass = levelToPassword[n];
            if (pass != null)
            {
                return pass;
            }
            chipDat.Seek(GetLevelOffset(n));

            chipDat.SkipBytes(10);
            int numBytesFirstLayer = chipDat.ReadUnsignedWord();
            chipDat.SkipBytes(numBytesFirstLayer);
            int numBytesSecondLayer = chipDat.ReadUnsignedWord();
            chipDat.SkipBytes(numBytesSecondLayer);
            int numBytesOptional = chipDat.ReadUnsignedWord();
            int readOptional = 0;
            while (readOptional < numBytesOptional)
            {
                int fieldType = chipDat.ReadUnsignedByte();
                int fieldLength = chipDat.ReadUnsignedByte();
                readOptional += 2;
                if (fieldType == 0x06)
                {
                    string password = ReadPassword(fieldLength);
                    levelToPassword[n] = password;
                    passwordToLevel[password] = n;
                    return password;
                }
                else
                {
                    chipDat.SkipBytes(fieldLength);
                }
                readOptional += fieldLength;
            }
            return null;
        }

        protected internal override int GetLevelNumberByPassword(string pass)
        {
            try
            {
                int? level;

                // Check if password has already been seen
                level = passwordToLevel[pass];
                if (level != null)
                {
                    return level.Value;
                }

                // Password has not yet been seen, check all levels we have not yet seen
                for (int i = 1; i <= levelCount; i++)
                {
                    string password = GetLevelPassword(i);
                    if (password != null && password.Equals(pass))
                    {
                        return i;
                    }
                }
            }
            catch (Exception ex)
            {
                throw ex;
                // TODO UWP
                //Console.WriteLine("While getting level by password: " + pass + ":" + ex.Message);
            }
            return -1;
        }
    }
}