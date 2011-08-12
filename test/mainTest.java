
import chipschallenge.Game;
import chipschallenge.MicrosoftBlockFactory;
import chipschallenge.gui.GUI;

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
        g.setBlockFactory(MicrosoftBlockFactory.getInstance());
        GUI Gui = GUI.getInstance();
    }
}
