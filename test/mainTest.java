
import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.GameLevel;
import chipschallenge.MicrosoftBlockFactory;
import chipschallenge.gamestates.GameState;
import chipschallenge.gui.GUI;
import chipschallenge.tickbehaviors.ChipTickBehavior;
import java.awt.event.KeyEvent;
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
        Gui.addKeyListener(ChipTickBehavior.getInstance());
        new Thread(g).start();
    }

    public static GameLevel getTestLevel() {
        Random r = new Random();
        GameLevel ret = new GameLevel(10,10);
        try {
        boolean chipPlaced = false;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {

                    ret.addBlock(i, j, MicrosoftBlockFactory.getInstance().get(Block.Type.FLOOR));
                    if (r.nextFloat() > 0.7f) {
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
            System.out.println("OH NOS");
        }
        return ret;
    }
}
