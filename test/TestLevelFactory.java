
import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.GameLevel;
import chipschallenge.LevelFactory;
import chipschallenge.MicrosoftBlockFactory;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patrik
 */
public class TestLevelFactory extends LevelFactory {

    private static TestLevelFactory mInstance = null;
    public static synchronized TestLevelFactory getInstance() {
        if(mInstance == null)
            mInstance = new TestLevelFactory();
        return mInstance;
    }

    public GameLevel getLevel(int n) {
        switch (n) {
            case 1:
                return getLevel1();
            case 2:
                return getLevel2();
            case 3:
                return getLevel3();
            case 4:
                return getLevel4();
            case 5:
                return getLevel5();
        }
        return null;
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

    public GameLevel getLevel1() {
        Random r = new Random();
        GameLevel ret = new GameLevel(9,9);
        try {
        boolean chipPlaced = false;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {

                    ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.FLOOR));
                    if (r.nextFloat() > 0.9f) {
                        ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.BLOCK));
                    } else {
                        if (!chipPlaced) {
                            chipPlaced = true;
                            ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.CHIP));
                        }
                    }

            }
        }
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        }
        return ret;
    }

    public GameLevel getLevel2() {
        Random r = new Random();
        GameLevel ret = new GameLevel(9,9);
        try {
        boolean chipPlaced = false;
        boolean flippersPlaced = false;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                    if(r.nextFloat() > 0.8f) {
                        ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.WATER));
                    } else {
                        ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.FLOOR));
                    
                    if (r.nextFloat() > 0.8f) {
                        if(r.nextBoolean())
                            ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.BLOCK));                       
                    } else {
                        if (!chipPlaced) {
                            chipPlaced = true;
                            ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.CHIP));
                        } else {
                            if (!flippersPlaced) {
                            flippersPlaced = true;
                            ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.FLIPPERS));
                            }
                        }
                    }
                        }

            }
        }
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        }
        return ret;
    }

    public GameLevel getLevel3() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, MicrosoftBlockFactory.getInstance().get(Block.Type.CHIP));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel4() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, MicrosoftBlockFactory.getInstance().get(Block.Type.CHIP));
            ret.addBlock(8, 8, MicrosoftBlockFactory.getInstance().get(Block.Type.TEETH));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel5() {
        Random r = new Random();
        GameLevel ret = new GameLevel(32,32);
        try {
        boolean chipPlaced = false;
        boolean flippersPlaced = false;
        for(int i = 0; i < ret.getWidth(); i++) {
            for(int j = 0; j < ret.getHeight(); j++) {
                    if(r.nextFloat() > 0.95f) {
                        ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.WATER));
                    } else {
                        ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.FLOOR));

                    if (r.nextFloat() > 0.9f) {
                        if(r.nextBoolean())
                            ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.BLOCK));
                    } else {
                        if (!chipPlaced) {
                            chipPlaced = true;
                            ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.CHIP));
                        } else {
                            if (!flippersPlaced) {
                            flippersPlaced = true;
                            ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.FLIPPERS));
                            }
                        }
                    }
                        }

            }
        }
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        }
        return ret;
    }

    @Override
    public int getLastLevelNumber() {
        return 2;
    }

    @Override
    public int getLevelNumberByPassword(String pass) {
        if(pass.equals("level1"))
            return 1;
        if(pass.equals("level2"))
            return 2;
        return -1;
    }

}
