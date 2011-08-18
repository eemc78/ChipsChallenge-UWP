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
        int startLevel = 1;
        if (args.length > 0) {
            try {
                startLevel = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                startLevel = 1;
            }
        }
        Game g = Game.getInstance();
        g.setLevelFactory(MicrosoftLevelFactory.getInstance());
        g.setBlockFactory(MicrosoftBlockFactory.getInstance());
        GUI Gui = GUI.getInstance();
        Gui.addKeyListener(ChipTickBehavior.getInstance());
        g.nextLevel(startLevel);
    }
}
