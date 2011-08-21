package chipschallenge;

import chipschallenge.Block.Type;
import chipschallenge.Move.Moves;
import chipschallenge.gui.GUI;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/**
 * Level factory which uses CHIPS.DAT to create levels.
 */
public class MicrosoftLevelFactory extends LevelFactory {

    private Map<Block.Type, Byte> msType = new HashMap<Block.Type, Byte>();
    private int levelCount = -1;
    private Map<Integer, Integer> levelOffsets = new HashMap<Integer, Integer>();
    private Map<String, Integer> passwordToLevel = new HashMap<String, Integer>();
    private Map<Integer, String> levelToPassword = new HashMap<Integer, String>();

    // Get block by object code
    private Block getBlock(int objCode) {
        MicrosoftBlockFactory f = MicrosoftBlockFactory.getInstance();
        switch (objCode) {
            case 0x00:
                return f.get(Type.FLOOR);
            case 0x01:
                return f.get(Type.WALL);
            case 0x02:
                return f.get(Type.COMPUTERCHIP);
            case 0x03:
                return f.get(Type.WATER);
            case 0x04:
                return f.get(Type.FIRE);
            case 0x05:
                return f.get(Type.INVISIBLEWALL);
            case 0x06:
                return f.get(Type.THINWALL, Moves.UP);
            case 0x07:
                return f.get(Type.THINWALL, Moves.LEFT);
            case 0x08:
                return f.get(Type.THINWALL, Moves.DOWN);
            case 0x09:
                return f.get(Type.THINWALL, Moves.RIGHT);
            case 0x0A:
                return f.get(Type.BLOCK);
            case 0x0B:
                return f.get(Type.DIRT);
            case 0x0C:
                return f.get(Type.ICE);
            case 0x0D:
                return f.get(Type.FORCEFLOOR, Moves.DOWN);
            case 0x0E:
                return f.get(Type.BLOCK, Moves.UP); // Cloning block UP
            case 0x0F:
                return f.get(Type.BLOCK, Moves.LEFT); // Cloning block LEFT
            case 0x10:
                return f.get(Type.BLOCK, Moves.DOWN); // Cloning block DOWN
            case 0x11:
                return f.get(Type.BLOCK, Moves.RIGHT); // Cloning block RIGHT
            case 0x12:
                return f.get(Type.FORCEFLOOR, Moves.UP);
            case 0x13:
                return f.get(Type.FORCEFLOOR, Moves.RIGHT);
            case 0x14:
                return f.get(Type.FORCEFLOOR, Moves.LEFT);
            case 0x15:
                return f.get(Type.EXIT);
            case 0x16:
                return f.get(Type.BLUELOCK);
            case 0x17:
                return f.get(Type.REDLOCK);
            case 0x18:
                return f.get(Type.GREENLOCK);
            case 0x19:
                return f.get(Type.YELLOWLOCK);
            case 0x1A:
                return f.get(Type.ICECORNER, Moves.UP);
            case 0x1B:
                return f.get(Type.ICECORNER, Moves.LEFT);
            case 0x1C:
                return f.get(Type.ICECORNER, Moves.DOWN);
            case 0x1D:
                return f.get(Type.ICECORNER, Moves.RIGHT);
            case 0x1E:
                return f.get(Type.BLUEWALLFAKE);
            case 0x1F:
                return f.get(Type.BLUEWALLREAL);
            case 0x20:
                return null; // NOT USED
            case 0x21:
                return f.get(Type.THIEF);
            case 0x22:
                return f.get(Type.SOCKET);
            case 0x23:
                return f.get(Type.GREENBUTTON);
            case 0x24:
                return f.get(Type.REDBUTTON);
            case 0x25:
                return f.get(Type.TOGGLEWALLCLOSED);
            case 0x26:
                return f.get(Type.TOGGLEWALLOPEN);
            case 0x27:
                return f.get(Type.BROWNBUTTON);
            case 0x28:
                return f.get(Type.BLUEBUTTON);
            case 0x29:
                return f.get(Type.TELEPORT);
            case 0x2A:
                return f.get(Type.BOMB);
            case 0x2B:
                return f.get(Type.TRAP);
            case 0x2C:
                return f.get(Type.HIDDENWALL);
            case 0x2D:
                return f.get(Type.GRAVEL);
            case 0x2E:
                return f.get(Type.RECESSEDWALL);
            case 0x2F:
                return f.get(Type.HINT);
            case 0x30:
                return f.get(Type.THINWALLSE);
            case 0x31:
                return f.get(Type.CLONEMACHINE);
            case 0x32:
                return f.get(Type.RANDOMFORCEFLOOR);
            case 0x33:
                return f.get(Type.DROWNEDCHIP);
            case 0x34:
                return f.get(Type.BURNEDCHIP);
            case 0x35:
                return f.get(Type.BURNEDCHIP); // Overlay
            case 0x36:
                return null; // Not used
            case 0x37:
                return null; // Not used
            case 0x38:
                return null; // Not used
            case 0x39:
                return null; // Chip in Exit
            case 0x3A:
                return f.get(Type.EXIT); // Exit-End Game
            case 0x3B:
                return f.get(Type.EXIT); // Exit-End Game
            case 0x3C:
                return f.get(Type.SWIMMINGCHIP, Moves.UP);
            case 0x3D:
                return f.get(Type.SWIMMINGCHIP, Moves.LEFT);
            case 0x3E:
                return f.get(Type.SWIMMINGCHIP, Moves.DOWN);
            case 0x3F:
                return f.get(Type.SWIMMINGCHIP, Moves.RIGHT);
            case 0x40:
                return f.get(Type.BUG, Moves.UP);
            case 0x41:
                return f.get(Type.BUG, Moves.LEFT);
            case 0x42:
                return f.get(Type.BUG, Moves.DOWN);
            case 0x43:
                return f.get(Type.BUG, Moves.RIGHT);
            case 0x44:
                return f.get(Type.FIREBALL, Moves.UP);
            case 0x45:
                return f.get(Type.FIREBALL, Moves.LEFT);
            case 0x46:
                return f.get(Type.FIREBALL, Moves.DOWN);
            case 0x47:
                return f.get(Type.FIREBALL, Moves.RIGHT);
            case 0x48:
                return f.get(Type.PINKBALL, Moves.UP);
            case 0x49:
                return f.get(Type.PINKBALL, Moves.LEFT);
            case 0x4A:
                return f.get(Type.PINKBALL, Moves.DOWN);
            case 0x4B:
                return f.get(Type.PINKBALL, Moves.RIGHT);
            case 0x4C:
                return f.get(Type.TANK, Moves.UP);
            case 0x4D:
                return f.get(Type.TANK, Moves.LEFT);
            case 0x4E:
                return f.get(Type.TANK, Moves.DOWN);
            case 0x4F:
                return f.get(Type.TANK, Moves.RIGHT);
            case 0x50:
                return f.get(Type.GLIDER, Moves.UP);
            case 0x51:
                return f.get(Type.GLIDER, Moves.LEFT);
            case 0x52:
                return f.get(Type.GLIDER, Moves.DOWN);
            case 0x53:
                return f.get(Type.GLIDER, Moves.RIGHT);
            case 0x54:
                return f.get(Type.TEETH, Moves.UP);
            case 0x55:
                return f.get(Type.TEETH, Moves.LEFT);
            case 0x56:
                return f.get(Type.TEETH, Moves.DOWN);
            case 0x57:
                return f.get(Type.TEETH, Moves.RIGHT);
            case 0x58:
                return f.get(Type.WALKER, Moves.UP);
            case 0x59:
                return f.get(Type.WALKER, Moves.LEFT);
            case 0x5A:
                return f.get(Type.WALKER, Moves.DOWN);
            case 0x5B:
                return f.get(Type.WALKER, Moves.RIGHT);
            case 0x5C:
                return f.get(Type.BLOB, Moves.UP);
            case 0x5D:
                return f.get(Type.BLOB, Moves.LEFT);
            case 0x5E:
                return f.get(Type.BLOB, Moves.DOWN);
            case 0x5F:
                return f.get(Type.BLOB, Moves.RIGHT);
            case 0x60:
                return f.get(Type.PARAMECIUM, Moves.UP);
            case 0x61:
                return f.get(Type.PARAMECIUM, Moves.LEFT);
            case 0x62:
                return f.get(Type.PARAMECIUM, Moves.DOWN);
            case 0x63:
                return f.get(Type.PARAMECIUM, Moves.RIGHT);
            case 0x64:
                return f.get(Type.BLUEKEY);
            case 0x65:
                return f.get(Type.REDKEY);
            case 0x66:
                return f.get(Type.GREENKEY);
            case 0x67:
                return f.get(Type.YELLOWKEY);
            case 0x68:
                return f.get(Type.FLIPPERS);
            case 0x69:
                return f.get(Type.FIREBOOTS);
            case 0x6A:
                return f.get(Type.ICESKATES);
            case 0x6B:
                return f.get(Type.SUCTIONBOOTS);
            case 0x6C:
                return f.get(Type.CHIP, Moves.UP);
            case 0x6D:
                return f.get(Type.CHIP, Moves.LEFT);
            case 0x6E:
                return f.get(Type.CHIP, Moves.DOWN);
            case 0x6F:
                return f.get(Type.CHIP, Moves.RIGHT);
        }
        return null;
    }
    private RandomAccessFile chipDat = null;

    public int readUnsignedDWord() throws IOException {
        return ByteSwapper.swap(chipDat.readInt()) & 0xFFFFFFFF;
    }

    public int readUnsignedWord() throws IOException {
        return ByteSwapper.swap(chipDat.readShort()) & 0xFFFF;
    }

    public int readUnsignedByte() throws IOException {
        return chipDat.readByte() & 0xFF;
    }

    private MicrosoftLevelFactory() {
        try {
            chipDat = new RandomAccessFile("CHIPS.DAT", "r");
            int magicNumber = readUnsignedDWord();
            if (magicNumber != 0x0002AAAC) {
                throw new Exception("Couldn't parse file");
            }
            levelCount = readUnsignedWord();
            levelOffsets.put(1, 6);
            //chipDat.close();
        } catch (Exception ex) {
            GUI.getInstance().msgDialog(ex.getMessage());
        } finally {
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            chipDat.close();
        } catch (Exception e) {
        } finally {
            super.finalize();
        }
    }
    private static MicrosoftLevelFactory mInstance = null;

    public static synchronized MicrosoftLevelFactory getInstance() {
        if (mInstance == null) {
            mInstance = new MicrosoftLevelFactory();
        }
        return mInstance;
    }

    public GameLevel getLevel(int n) {
        if (n < 1 || n > levelCount) {
            throw new IllegalArgumentException("Level outside of range");
        }
        int width = 32;
        int height = 32;
        GameLevel ret = null;
        try {
            int offset = getLevelOffset(n);
            chipDat.seek(offset);

            int numBytesLevel = readUnsignedWord();

            // So we don't have to skip over the same level again later
            levelOffsets.put(n + 1, offset + numBytesLevel + 2);

            int levelNumber = readUnsignedWord();
            int numSeconds = readUnsignedWord();
            int numChipsNeeded = readUnsignedWord();
            int mapDetail = readUnsignedWord();
            
            ret = new GameLevel(32, 32, numChipsNeeded, numSeconds, levelNumber);

            int numberOfBytesLayer1 = readUnsignedWord();
            readLayer(ret, numberOfBytesLayer1, 1); // Layer 1, upper

            int numberOfBytesLayer2 = readUnsignedWord();
            readLayer(ret, numberOfBytesLayer2, 0); // Layer 2, lower
            int numBytesOptional = readUnsignedWord();
            int numOptionalBytesRead = 0;
            
            while (numOptionalBytesRead < numBytesOptional) {

                int fieldType = readUnsignedByte();
                int sizeOfField = readUnsignedByte();
                numOptionalBytesRead += 2;

                switch (fieldType) {

                    case 0x03: // Map title
                        byte[] ASCIITitle = new byte[sizeOfField - 1];
                        chipDat.readFully(ASCIITitle);
                        String title = new String(ASCIITitle);
                        ret.setMapTitle(title);
                        chipDat.skipBytes(1);
                        break;

                    case 0x04: // Brown buttons to traps
                        for (int i = 0; i < sizeOfField / 10; i++) {
                            int buttonX = readUnsignedWord();
                            int buttonY = readUnsignedWord();
                            int trapX = readUnsignedWord();
                            int trapY = readUnsignedWord();

                            // Locate button
                            BlockContainer bc = ret.getBlockContainer(buttonX, buttonY);
                            Block button = bc.find(Type.BROWNBUTTON);

                            // Locate trap
                            bc = ret.getBlockContainer(trapX, trapY);
                            Block trap = bc.find(Type.TRAP);

                            if (button != null && trap != null) // Perhaps throw an exception otherwise                            
                            {
                                Buttons.addBrownButtonListener(button, trap);
                            }

                            chipDat.skipBytes(2);
                        }
                        break;

                    case 0x05:
                        for (int i = 0; i < sizeOfField / 8; i++) {
                            int buttonX = readUnsignedWord();
                            int buttonY = readUnsignedWord();
                            int clonerX = readUnsignedWord();
                            int clonerY = readUnsignedWord();

                            // Locate button
                            BlockContainer bc = ret.getBlockContainer(buttonX, buttonY);
                            Block button = bc.find(Type.REDBUTTON);

                            // Locate cloner
                            bc = ret.getBlockContainer(clonerX, clonerY);
                            Block cloner = bc.find(Type.CLONEMACHINE);

                            if (button != null && cloner != null) // Perhaps throw an exception otherwise
                            {
                                Buttons.addRedButtonListener(button, cloner);
                            }
                        }
                        break;

                    case 0x06: // Password
                        String password = readPassword(sizeOfField);
                        ret.setPassword(password);
                        passwordToLevel.put(password, n);
                        levelToPassword.put(n, password);
                        chipDat.skipBytes(1);
                        break;

                    case 0x07: // Hint
                        byte[] ASCIIHint = new byte[sizeOfField - 1];
                        chipDat.readFully(ASCIIHint);
                        String hint = new String(ASCIIHint);
                        ret.setHint(hint);
                        chipDat.skipBytes(1);
                        break;

                    case 0x0A: // Movement
                        for (int i = 0; i < sizeOfField / 2; i++) {
                            int creatureX = readUnsignedByte();
                            int creatureY = readUnsignedByte();
                            Block creature = null;
                            BlockContainer bc;
                            Block upper;

                            // Locate creature
                            bc = ret.getBlockContainer(creatureX, creatureY);
                            upper = bc.getUpper();
                            if (upper.isCreature()) {
                                creature = upper;
                            }

                            if (creature != null) // Perhaps throw an exception otherwise
                            {
                                Creatures.addCreature(creature);
                            }

                        }
                        break;
                    default:
                        chipDat.skipBytes(sizeOfField);
                }
                numOptionalBytesRead += sizeOfField;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("While loading level: " + ex.getMessage());
        } finally {
            return ret;
        }
    }

    private String readPassword(int length) throws IOException {
        byte[] ASCIIPassword = new byte[length - 1];
        chipDat.readFully(ASCIIPassword);
        for (int i = 0; i < ASCIIPassword.length; i++) {
            ASCIIPassword[i] ^= 0x99;
        }
        String password = new String(ASCIIPassword);
        return password;
    }

    public void readLayer(GameLevel ret, int numberOfBytes, int layer) throws IOException, BlockContainerFullException {
        int width = ret.getWidth();
        int height = ret.getHeight();
        int bytesRead = 0;
        int objectsPlaced = 0;
        while (bytesRead < numberOfBytes) {
            int read = readUnsignedByte();
            bytesRead++;
            if (read >= 0x00 && read <= 0x6F) {
                ret.addBlock(objectsPlaced % width, objectsPlaced / width, getBlock(read), layer);
                objectsPlaced++;
            } else if (read == 0xFF) {
                int numRepeats = readUnsignedByte();
                int data = readUnsignedByte();
                bytesRead += 2;
                while (numRepeats-- > 0) {
                    ret.addBlock(objectsPlaced % width, objectsPlaced / width, getBlock(data), layer);
                    objectsPlaced++;
                }
            } else {
                System.out.println("Object I couldn't parse: " + read);
            }
        }
    }

    @Override
    public int getLastLevelNumber() {
        return levelCount;
    }

    public int getLevelOffset(int n) throws IOException {
        Integer offset = levelOffsets.get(n);
        if (offset != null) {
            return offset;
        }
        int level = n - 1;
        while (level >= 1) {
            offset = levelOffsets.get(level);
            if (offset != null) {
                break;
            }
            level--;
        }
        chipDat.seek(offset);
        while (level < n) {
            int numBytesInLevel = readUnsignedWord();
            offset = offset + numBytesInLevel + 2;
            levelOffsets.put(level + 1, offset);
            level++;
            if (level == n) {
                break;
            }
            chipDat.skipBytes(numBytesInLevel);
        }
        return offset;
    }

    public String getLevelPassword(int n) throws IOException {
        String pass = levelToPassword.get(n);
        if (pass != null) {
            return pass;
        }
        chipDat.seek(getLevelOffset(n));

        chipDat.skipBytes(10);
        int numBytesFirstLayer = readUnsignedWord();
        chipDat.skipBytes(numBytesFirstLayer);
        int numBytesSecondLayer = readUnsignedWord();
        chipDat.skipBytes(numBytesSecondLayer);
        int numBytesOptional = readUnsignedWord();
        int readOptional = 0;
        while (readOptional < numBytesOptional) {
            int fieldType = readUnsignedByte();
            int fieldLength = readUnsignedByte();
            readOptional += 2;
            if (fieldType == 0x06) {
                String password = readPassword(fieldLength);
                levelToPassword.put(n, password);
                passwordToLevel.put(password, n);
                return password;
            } else {
                chipDat.skipBytes(fieldLength);
            }
            readOptional += fieldLength;
        }
        return null;
    }

    @Override
    protected int getLevelNumberByPassword(String pass) {
        try {
            Integer level = null;

            // Check if password has already been seen
            level = passwordToLevel.get(pass);
            if (level != null) {
                return level;
            }

            // Password has not yet been seen, check all levels we have not yet seen
            for (int i = 1; i <= levelCount; i++) {
                String password = getLevelPassword(i);
                if (password != null && password.equals(pass)) {
                    return i;
                }
            }
        } catch (IOException ex) {
            System.out.println("While getting level by password: " + pass + ":" + ex.getMessage());
        }
        return -1;
    }
}
