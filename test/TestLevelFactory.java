
import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.BlockFactory;
import chipschallenge.GameLevel;
import chipschallenge.LevelFactory;
import chipschallenge.MicrosoftBlockFactory;
import chipschallenge.Move.Moves;
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
    private BlockFactory bf = null;
    private TestLevelFactory() {
        bf = MicrosoftBlockFactory.getInstance();
    }
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
            case 6:
                return getLevel6();
            case 7:
                return getLevel7();
            case 8:
                return getLevel8();
            case 9:
                return getLevel9();
            case 10:
                return getLevel10();
            case 11:
                return getLevel11();
            case 12:
                return getLevel12();
            case 13:
                return getLevel13();
            case 14:
                return getLevel14();
            case 15:
                return getLevel15();
            case 16:
                return getLevel16();
            case 17:
                return getLevel17();
        }
        return null;
    }

    private GameLevel getFloors(int width, int height) {
        GameLevel ret = null;
        try {
            ret = new GameLevel(width, height);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    ret.addBlock(i, j, bf.get(Block.Type.FLOOR));
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
        boolean exitPlaced = false;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {

                    ret.addBlock(i, j, bf.get(Block.Type.FLOOR));
                    if (r.nextFloat() > 0.9f) {
                        ret.addBlock(i, j, bf.get(Block.Type.BLOCK));
                    } else {
                        if (!chipPlaced) {
                            chipPlaced = true;
                            ret.addBlock(i, j, bf.get(Block.Type.CHIP));
                        } else
                        if (!exitPlaced) {
                            exitPlaced = true;
                            ret.addBlock(i, j, bf.get(Block.Type.EXIT));
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
        boolean exitPlaced = false;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                    if(r.nextFloat() > 0.8f) {
                        ret.addBlock(i, j, bf.get(Block.Type.WATER));
                    } else {
                        ret.addBlock(i, j, bf.get(Block.Type.FLOOR));
                    
                    if (r.nextFloat() > 0.8f) {
                        if(r.nextBoolean())
                            ret.addBlock(i, j, bf.get(Block.Type.BLOCK));
                    } else {
                        if (!chipPlaced) {
                            chipPlaced = true;
                            ret.addBlock(i, j, bf.get(Block.Type.CHIP));
                        } else {
                            if (!flippersPlaced) {
                            flippersPlaced = true;
                            ret.addBlock(i, j, bf.get(Block.Type.FLIPPERS));
                            } else
                                if (!exitPlaced) {
                            exitPlaced = true;
                            ret.addBlock(i, j, bf.get(Block.Type.EXIT));
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
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(8, 8, bf.get(Block.Type.EXIT));
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
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(8, 8, bf.get(Block.Type.TEETH));
            ret.addBlock(8, 7, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel5() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(8, 8, bf.get(Block.Type.PINKBALL, Moves.LEFT));
            ret.addBlock(7, 7, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel6() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(8, 8, bf.get(Block.Type.BUG, Moves.LEFT));
            ret.addBlock(7, 7, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel7() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(8, 8, bf.get(Block.Type.PARAMECIUM, Moves.LEFT));
            ret.addBlock(7, 7, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel8() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(8, 8, bf.get(Block.Type.BLOB));
            ret.addBlock(7, 7, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel9() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(8, 8, bf.get(Block.Type.FIREBALL));
            ret.addBlock(7, 7, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel10() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(8, 8, bf.get(Block.Type.GLIDER));
            ret.addBlock(7, 7, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel11() {
        Random r = new Random();
        GameLevel ret = new GameLevel(32,32);
        try {
        boolean chipPlaced = false;
        boolean flippersPlaced = false;
        boolean exitPlaced = false;
        for(int i = 0; i < ret.getWidth(); i++) {
            for(int j = 0; j < ret.getHeight(); j++) {
                    if(r.nextFloat() > 0.95f) {
                        ret.addBlock(i, j, bf.get(Block.Type.WATER));
                    } else {
                        ret.addBlock(i, j, bf.get(Block.Type.FLOOR));

                    if (r.nextFloat() > 0.9f) {
                        if(r.nextBoolean())
                            ret.addBlock(i, j, bf.get(Block.Type.BLOCK));
                    } else {
                        if (!chipPlaced) {
                            chipPlaced = true;
                            ret.addBlock(i, j, bf.get(Block.Type.CHIP));
                        } else {
                            if (!flippersPlaced) {
                            flippersPlaced = true;
                            ret.addBlock(i, j, bf.get(Block.Type.FLIPPERS));
                            } else
                              if (!exitPlaced) {
                            exitPlaced = true;
                            ret.addBlock(i, j, bf.get(Block.Type.EXIT));
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

    public GameLevel getLevel12() {
        GameLevel ret = getFloors(32, 32);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(8, 8, bf.get(Block.Type.GLIDER));
            ret.addBlock(7, 8, bf.get(Block.Type.GLIDER));
            ret.addBlock(7, 20, bf.get(Block.Type.FIREBALL));
            ret.addBlock(20, 7, bf.get(Block.Type.BUG));
            ret.addBlock(10, 10, bf.get(Block.Type.BLOB));
            ret.addBlock(10, 11, bf.get(Block.Type.BLOB));
            ret.addBlock(10, 12, bf.get(Block.Type.TEETH));
            ret.addBlock(10, 12, bf.get(Block.Type.PINKBALL));
            ret.addBlock(31, 31, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel13() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(4, 4, bf.get(Block.Type.GREENBUTTON));
            ret.addBlock(0, 6, bf.get(Block.Type.TOGGLEWALLCLOSED));
            ret.addBlock(1, 6, bf.get(Block.Type.TOGGLEWALLCLOSED));
            ret.addBlock(2, 6, bf.get(Block.Type.TOGGLEWALLCLOSED));
            ret.addBlock(3, 6, bf.get(Block.Type.TOGGLEWALLCLOSED));
            ret.addBlock(4, 6, bf.get(Block.Type.TOGGLEWALLCLOSED));
            ret.addBlock(5, 6, bf.get(Block.Type.TOGGLEWALLCLOSED));
            ret.addBlock(6, 6, bf.get(Block.Type.TOGGLEWALLCLOSED));
            ret.addBlock(7, 6, bf.get(Block.Type.TOGGLEWALLCLOSED));
            ret.addBlock(8, 6, bf.get(Block.Type.TOGGLEWALLCLOSED));
            ret.addBlock(8, 8, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel14() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(4, 4, bf.get(Block.Type.BLUEBUTTON));
            ret.addBlock(8, 7, bf.get(Block.Type.TANK, Moves.DOWN));
            ret.addBlock(7, 8, bf.get(Block.Type.TANK, Moves.RIGHT));
            ret.addBlock(8, 8, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel15() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(4, 4, bf.get(Block.Type.WALKER));
            ret.addBlock(5, 5, bf.get(Block.Type.WALKER));
            ret.addBlock(7, 7, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel16() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(0, 5, bf.get(Block.Type.WALL));
            ret.addBlock(1, 5, bf.get(Block.Type.WALL));
            ret.addBlock(2, 5, bf.get(Block.Type.WALL));
            ret.addBlock(3, 5, bf.get(Block.Type.WALL));
            ret.addBlock(4, 5, bf.get(Block.Type.TOGGLEWALLCLOSED));
            ret.addBlock(5, 5, bf.get(Block.Type.WALL));
            ret.addBlock(6, 5, bf.get(Block.Type.WALL));
            ret.addBlock(7, 5, bf.get(Block.Type.WALL));
            ret.addBlock(8, 5, bf.get(Block.Type.WALL));
            ret.addBlock(0, 8, bf.get(Block.Type.PINKBALL,Moves.UP));
            ret.addBlock(0, 6, bf.get(Block.Type.GREENBUTTON));
            ret.addBlock(8, 8, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
    }

    public GameLevel getLevel17() {
        GameLevel ret = getFloors(9, 9);
        try {
            ret.addBlock(0, 0, bf.get(Block.Type.CHIP));
            ret.addBlock(7, 8, bf.get(Block.Type.WALL));
            ret.addBlock(7, 7, bf.get(Block.Type.WALL));
            ret.addBlock(7, 6, bf.get(Block.Type.WALL));
            ret.addBlock(7, 5, bf.get(Block.Type.WALL));
            ret.addBlock(7, 4, bf.get(Block.Type.WALL));
            ret.addBlock(7, 3, bf.get(Block.Type.WALL));
            ret.addBlock(7, 2, bf.get(Block.Type.WALL));
            ret.addBlock(7, 1, bf.get(Block.Type.WALL));

            ret.addBlock(2, 2, bf.get(Block.Type.BLUEKEY));
            ret.addBlock(4, 5, bf.get(Block.Type.REDKEY));
            ret.addBlock(5, 3, bf.get(Block.Type.BLUEKEY));
            ret.addBlock(6, 2, bf.get(Block.Type.YELLOWKEY));
            ret.addBlock(3, 3, bf.get(Block.Type.YELLOWKEY));
            ret.addBlock(4, 4, bf.get(Block.Type.GREENKEY));

            ret.addBlock(8, 7, bf.get(Block.Type.REDLOCK));
            ret.addBlock(8, 6, bf.get(Block.Type.BLUELOCK));
            ret.addBlock(8, 5, bf.get(Block.Type.YELLOWLOCK));
            ret.addBlock(8, 4, bf.get(Block.Type.GREENLOCK));
            ret.addBlock(8, 3, bf.get(Block.Type.GREENLOCK));
            ret.addBlock(8, 2, bf.get(Block.Type.YELLOWLOCK));
            ret.addBlock(8, 1, bf.get(Block.Type.BLUELOCK));
            ret.addBlock(8, 8, bf.get(Block.Type.EXIT));
            return ret;
        } catch (BlockContainerFullException ex) {
            System.out.println("Couldn't create level");
        } finally {
            return ret;
        }
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
