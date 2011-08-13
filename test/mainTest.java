
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
        g.setLevelFactory(TestLevelFactory.getInstance());
        g.setBlockFactory(MicrosoftBlockFactory.getInstance());
        GUI Gui = GUI.getInstance();
        Gui.addKeyListener(ChipTickBehavior.getInstance());
        g.nextLevel(4);
    }

    
}
