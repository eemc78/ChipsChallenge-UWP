package chipschallenge;

import chipschallenge.gui.GUI;
import chipschallenge.tickbehaviors.ChipTickBehavior;

/**
 *
 * @author patrik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game g = Game.getInstance();
        g.setLevelFactory(MicrosoftLevelFactory.getInstance());
        g.setBlockFactory(MicrosoftBlockFactory.getInstance());
        GUI Gui = GUI.getInstance();
        Gui.addKeyListener(ChipTickBehavior.getInstance());
        g.nextLevel(3);
    }

}
