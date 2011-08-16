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
 * @author patrik
 */
public class MicrosoftLevelFactory extends LevelFactory {

    private Map<Block.Type, Byte> msType = new HashMap<Block.Type, Byte>();

    private int levelCount = -1;

    private Block getBlock(int objCode) {
        MicrosoftBlockFactory f = MicrosoftBlockFactory.getInstance();
        switch(objCode) {
            case 0x00: return f.get(Type.FLOOR);
            case 0x01: return f.get(Type.WALL);
            case 0x02: return f.get(Type.COMPUTERCHIP);
            case 0x03: return f.get(Type.WATER);
            case 0x04: return f.get(Type.FIRE);
            case 0x05: return f.get(Type.INVISIBLEWALL);
            case 0x06: return f.get(Type.THINWALL, Moves.UP);
            case 0x07: return f.get(Type.THINWALL, Moves.LEFT);
            case 0x08: return f.get(Type.THINWALL, Moves.DOWN);
            case 0x09: return f.get(Type.THINWALL, Moves.RIGHT);
            case 0x0A: return f.get(Type.BLOCK);
            case 0x0B: return f.get(Type.DIRT);
            case 0x0C: return f.get(Type.ICE);
            case 0x0D: return f.get(Type.FORCEFLOOR, Moves.DOWN);
            case 0x0E: // Cloning block UP
            case 0x0F: // Cloning block LEFT
            case 0x10: // Cloning block DOWN
            case 0x11: // Cloning block RIGHT
                return f.get(Type.BLOCK); // For now
            case 0x12: return f.get(Type.FORCEFLOOR, Moves.UP);
            case 0x13: return f.get(Type.FORCEFLOOR, Moves.RIGHT);
            case 0x14: return f.get(Type.FORCEFLOOR, Moves.LEFT);
            case 0x15: return f.get(Type.EXIT);
            case 0x16: return f.get(Type.BLUELOCK);
            case 0x17: return f.get(Type.REDLOCK);
            case 0x18: return f.get(Type.GREENLOCK);
            case 0x19: return f.get(Type.YELLOWLOCK);
            case 0x1A: return f.get(Type.ICECORNER, Moves.UP);
            case 0x1B: return f.get(Type.ICECORNER, Moves.LEFT);
            case 0x1C: return f.get(Type.ICECORNER, Moves.DOWN);
            case 0x1D: return f.get(Type.ICECORNER, Moves.RIGHT);
            case 0x1E: return f.get(Type.BLUEWALLREAL);
            case 0x1F: return f.get(Type.BLUEWALLFAKE);
            case 0x20: return null; // NOT USED
            case 0x21: return f.get(Type.THIEF);
            case 0x22: return f.get(Type.SOCKET);
            case 0x23: return f.get(Type.GREENBUTTON);
            case 0x24: return f.get(Type.REDBUTTON);
            case 0x25: return f.get(Type.TOGGLEWALLCLOSED);
            case 0x26: return f.get(Type.TOGGLEWALLOPEN);
            case 0x27: return f.get(Type.BROWNBUTTON);
            case 0x28: return f.get(Type.BLUEBUTTON);
            case 0x29: return f.get(Type.TELEPORT);
            case 0x2A: return f.get(Type.BOMB);
            case 0x2B: return f.get(Type.TRAP);
            case 0x2C: return f.get(Type.HIDDENWALL);
            case 0x2D: return f.get(Type.GRAVEL);
            case 0x2E: return f.get(Type.RECESSEDWALL);
            case 0x2F: return f.get(Type.HINT);
            case 0x30: return f.get(Type.THINWALLSE);
            case 0x31: return f.get(Type.CLONEMACHINE);
            case 0x32: return f.get(Type.RANDOMFORCEFLOOR);
            case 0x33: return f.get(Type.DROWNEDCHIP);
            case 0x34: return f.get(Type.BURNEDCHIP);
            case 0x35: return f.get(Type.BURNEDCHIP); // Overlay
            case 0x36: return null; // Not used
            case 0x37: return null; // Not used
            case 0x38: return null; // Not used
            case 0x39: return null; // Chip in Exit
            case 0x3A: return f.get(Type.EXIT); // Exit-End Game
            case 0x3B: return f.get(Type.EXIT); // Exit-End Game
            case 0x3C: return f.get(Type.SWIMMINGCHIP, Moves.UP);
            case 0x3D: return f.get(Type.SWIMMINGCHIP, Moves.LEFT);
            case 0x3E: return f.get(Type.SWIMMINGCHIP, Moves.DOWN);
            case 0x3F: return f.get(Type.SWIMMINGCHIP, Moves.RIGHT);
            case 0x40: return f.get(Type.BUG, Moves.UP);
            case 0x41: return f.get(Type.BUG, Moves.LEFT);
            case 0x42: return f.get(Type.BUG, Moves.DOWN);
            case 0x43: return f.get(Type.BUG, Moves.RIGHT);
            case 0x44: return f.get(Type.FIREBALL, Moves.UP);
            case 0x45: return f.get(Type.FIREBALL, Moves.LEFT);
            case 0x46: return f.get(Type.FIREBALL, Moves.DOWN);
            case 0x47: return f.get(Type.FIREBALL, Moves.RIGHT);
            case 0x48: return f.get(Type.PINKBALL, Moves.UP);
            case 0x49: return f.get(Type.PINKBALL, Moves.LEFT);
            case 0x4A: return f.get(Type.PINKBALL, Moves.DOWN);
            case 0x4B: return f.get(Type.PINKBALL, Moves.RIGHT);
            case 0x4C: return f.get(Type.TANK, Moves.UP);
            case 0x4D: return f.get(Type.TANK, Moves.LEFT);
            case 0x4E: return f.get(Type.TANK, Moves.DOWN);
            case 0x4F: return f.get(Type.TANK, Moves.RIGHT);
            case 0x50: return f.get(Type.GLIDER, Moves.UP);
            case 0x51: return f.get(Type.GLIDER, Moves.LEFT);
            case 0x52: return f.get(Type.GLIDER, Moves.DOWN);
            case 0x53: return f.get(Type.GLIDER, Moves.RIGHT);
            case 0x54: return f.get(Type.TEETH, Moves.UP);
            case 0x55: return f.get(Type.TEETH, Moves.LEFT);
            case 0x56: return f.get(Type.TEETH, Moves.DOWN);
            case 0x57: return f.get(Type.TEETH, Moves.RIGHT);
            case 0x58: return f.get(Type.WALKER, Moves.UP);
            case 0x59: return f.get(Type.WALKER, Moves.LEFT);
            case 0x5A: return f.get(Type.WALKER, Moves.DOWN);
            case 0x5B: return f.get(Type.WALKER, Moves.RIGHT);
            case 0x5C: return f.get(Type.BLOB, Moves.UP);
            case 0x5D: return f.get(Type.BLOB, Moves.LEFT);
            case 0x5E: return f.get(Type.BLOB, Moves.DOWN);
            case 0x5F: return f.get(Type.BLOB, Moves.RIGHT);
            case 0x60: return f.get(Type.PARAMECIUM, Moves.UP);
            case 0x61: return f.get(Type.PARAMECIUM, Moves.LEFT);
            case 0x62: return f.get(Type.PARAMECIUM, Moves.DOWN);
            case 0x63: return f.get(Type.PARAMECIUM, Moves.RIGHT);
            case 0x64: return f.get(Type.BLUEKEY);
            case 0x65: return f.get(Type.REDKEY);
            case 0x66: return f.get(Type.GREENKEY);
            case 0x67: return f.get(Type.YELLOWKEY);
            case 0x68: return f.get(Type.FLIPPERS);
            case 0x69: return f.get(Type.FIREBOOTS);
            case 0x6A: return f.get(Type.ICESKATES);
            case 0x6B: return f.get(Type.SUCTIONBOOTS);
            case 0x6C: return f.get(Type.CHIP, Moves.UP);
            case 0x6D: return f.get(Type.CHIP, Moves.LEFT);
            case 0x6E: return f.get(Type.CHIP, Moves.DOWN);
            case 0x6F: return f.get(Type.CHIP, Moves.RIGHT);
        }
        return null;
    }

    private RandomAccessFile chipDat = null;
    
    private MicrosoftLevelFactory() {
        try {
            chipDat = new RandomAccessFile("CHIPS.DAT","r");
            int magicNumber = ByteSwapper.swap(chipDat.readInt());
            if(magicNumber != 0x0002AAAC)
                throw new Exception("Couldn't parse file");
            short numLevels = ByteSwapper.swap(chipDat.readShort());
            levelCount = (int)numLevels;
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
        } catch(Exception e) {
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

    private GameLevel getFloors(int width, int height) {
        GameLevel ret = null;
        try {
            ret = new GameLevel(width, height);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.FLOOR));
                }
            }
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel(int n) {
        int width = 32;
        int height = 32;
        GameLevel ret = new GameLevel(width,height); //getFloors(width,height); //
        try {
            long offset = 6;
            chipDat.seek(offset);
            if (n > levelCount) {
                throw new IllegalArgumentException();
            }
            int level = 1;

            // Skip over the levels we don't want
            while(level < n) {
                short numBytesLevel = ByteSwapper.swap(chipDat.readShort());
                short levelNumber   = ByteSwapper.swap(chipDat.readShort());
                short time          = ByteSwapper.swap(chipDat.readShort());
                short numberOfChips = ByteSwapper.swap(chipDat.readShort());
                level++;
            }
            // The level we want
            short numBytesLevel = ByteSwapper.swap(chipDat.readShort());
            short levelNumber   = ByteSwapper.swap(chipDat.readShort());
            short time          = ByteSwapper.swap(chipDat.readShort());
            short numberOfChips = ByteSwapper.swap(chipDat.readShort());            
            short mapDetail     = ByteSwapper.swap(chipDat.readShort());

            // Read layer 1
            readLayer(ret);

            // Read layer 2
            readLayer(ret);
            
        } catch (IOException ex) {
        } finally {
            return ret;
        }
    }

    public void readLayer(GameLevel ret) throws IOException, BlockContainerFullException {
        int width = ret.getWidth();
        int height = ret.getHeight();

        // Number of bytes for this layer data
        short numberOfBytes = ByteSwapper.swap(chipDat.readShort());
        int bytesRead = 0;
        int objectsPlaced = 0;
        while(bytesRead < numberOfBytes) {
            int read = chipDat.readByte() & 0xFF;
            if(read >= 0x00 && read <= 0x6F) {
                ret.addBlock(objectsPlaced % width, objectsPlaced / width, getBlock(read));
                objectsPlaced++;
            } else if(read == 0xFF) {
                int numRepeats = chipDat.readByte() & 0xFF;
                int data = chipDat.readByte() & 0xFF;
                while(numRepeats-- > 0) {
                    ret.addBlock(objectsPlaced % width, objectsPlaced / width, getBlock(data));
                    objectsPlaced++;
                }
            }
        }
    }


    @Override
    public int getLastLevelNumber() {
        return levelCount;
    }

    @Override
    protected int getLevelNumberByPassword(String pass) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void addBlock(GameLevel ret, int blocksAdded, Block block) throws BlockContainerFullException {
        int width = ret.getWidth();
        int height = ret.getHeight();
        int x = blocksAdded % width;
        int y = blocksAdded / width;
        ret.addBlock(x, y, block);
    }



}
