package wasd;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.BlockFactory;
import chipschallenge.GameLevel;
import chipschallenge.LevelFactory;
import chipschallenge.MicrosoftBlockFactory;

/**
 *
 * @author wasd
 */
public class WasdLevelFactory extends LevelFactory{
    
    private BlockFactory bf = null;
    
    private static WasdLevelFactory mInstance = null;

    private WasdLevelFactory(){
        bf = MicrosoftBlockFactory.getInstance();
    }

    public static synchronized WasdLevelFactory getInstance() {
        if(mInstance == null)
            mInstance = new WasdLevelFactory();
        return mInstance;
    }

    

    @Override
    public GameLevel getLevel(int n) {
        switch (n) {
            case 1:  return getLevel1();
        }
        return null;
    }

    private GameLevel getLevel1() {
        GameLevel ret = new GameLevel(9,9);
        try {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    ret.addBlock(x, y, bf.get(Block.Type.FLOOR));
                }
            }

            int xoffs = 3;
            int yoffs = 1;
            Block.Type block = Block.Type.WALL;

            ret.addBlock(xoffs, yoffs, bf.get(block));
            ret.addBlock(xoffs, yoffs+1, bf.get(block));
            ret.addBlock(xoffs, yoffs+2, bf.get(block));
            ret.addBlock(xoffs+1, yoffs+2, bf.get(block));
            ret.addBlock(xoffs+2, yoffs+2, bf.get(block));
            ret.addBlock(xoffs+3, yoffs+2, bf.get(block));
            ret.addBlock(xoffs+4, yoffs+2, bf.get(block));
            ret.addBlock(xoffs+4, yoffs+3, bf.get(block));
            ret.addBlock(xoffs+4, yoffs+4, bf.get(block));
            ret.addBlock(xoffs, yoffs+4, bf.get(block));
            ret.addBlock(xoffs+1, yoffs+4, bf.get(block));
            ret.addBlock(xoffs+2, yoffs+4, bf.get(block));
            ret.addBlock(xoffs+2, yoffs+3, bf.get(block));
            ret.addBlock(xoffs+2, yoffs+1, bf.get(block));
            ret.addBlock(xoffs+2, yoffs, bf.get(block));
            ret.addBlock(xoffs+3, yoffs, bf.get(block));
            ret.addBlock(xoffs+4, yoffs, bf.get(block));
            ret.addBlock(xoffs+3, yoffs+1, bf.get(Block.Type.REDKEY));
            ret.addBlock(xoffs+1, yoffs+1, bf.get(Block.Type.BLUEKEY));
            ret.addBlock(xoffs+3, yoffs+3, bf.get(Block.Type.YELLOWKEY));
            ret.addBlock(xoffs+1, yoffs+3, bf.get(Block.Type.GREENKEY));

            ret.addBlock(xoffs+1, yoffs, bf.get(Block.Type.BUG));
            ret.addBlock(xoffs+3, yoffs+4, bf.get(Block.Type.BUG));

            for(int x = 1; x<9;x++){
                ret.addBlock(x, 7, bf.get(Block.Type.WATER));
            }

            ret.addBlock(0, 7, bf.get(Block.Type.GREENLOCK));
            ret.addBlock(2, 8, bf.get(Block.Type.YELLOWLOCK));
            ret.addBlock(4, 8, bf.get(Block.Type.BLUELOCK));
            ret.addBlock(6, 8, bf.get(Block.Type.REDLOCK));

            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(8, 8, bf.get(Block.Type.EXIT));

        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        }
        return ret;
    }

    @Override
    public int getLastLevelNumber() {
        return -1;
    }

    @Override
    public int getLevelNumberByPassword(String pass) {
        return -1;
    }

}
