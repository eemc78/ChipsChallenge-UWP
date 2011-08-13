
import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.GameLevel;
import chipschallenge.MicrosoftBlockFactory;
import chipschallenge.gui.GUI;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patrik
 */
public class mainTest {
    public static void main(String[] args) {
        Game g = Game.getInstance();
        g.setLevel(getTestLevel());
        g.setBlockFactory(MicrosoftBlockFactory.getInstance());
        GUI Gui = GUI.getInstance();
        new Thread(g).start();
    }

    public static GameLevel getTestLevel() {
        Random r = new Random();
        GameLevel ret = new GameLevel(10,10);
        try {
        boolean chipPlaced = false;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {

                    ret.getBlockContainer(i, j).push(MicrosoftBlockFactory.getInstance().get(Block.Type.FLOOR));
                    if (r.nextFloat() > 0.7f) {
                        ret.getBlockContainer(i, j).push(MicrosoftBlockFactory.getInstance().get(Block.Type.BLOCK));
                    } else {
                        if (!chipPlaced) {
                            chipPlaced = true;
                            ret.getBlockContainer(i, j).push(MicrosoftBlockFactory.getInstance().get(Block.Type.CHIP));
                        }
                    }

            }
        }
        } catch (BlockContainerFullException ex) {
            System.out.println("OH NOS");
        }
        return ret;
    }
}
