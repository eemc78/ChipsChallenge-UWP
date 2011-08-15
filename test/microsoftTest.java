
import chipschallenge.Game;
import chipschallenge.MicrosoftBlockFactory;
import chipschallenge.MicrosoftLevelFactory;
import chipschallenge.gui.GUI;
import chipschallenge.tickbehaviors.ChipTickBehavior;

/**
 *
 * @author patrik
 */
public class microsoftTest {

    public static void main(String[] args) {
        Game g = Game.getInstance();
        g.setLevelFactory(MicrosoftLevelFactory.getInstance());
        g.setBlockFactory(MicrosoftBlockFactory.getInstance());
        GUI Gui = GUI.getInstance();
        Gui.addKeyListener(ChipTickBehavior.getInstance());
        g.nextLevel(1); 
    }
}
