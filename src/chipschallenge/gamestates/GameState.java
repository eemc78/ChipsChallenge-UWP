package chipschallenge.gamestates;

import chipschallenge.Game;
import java.awt.event.KeyEvent;

/**
 *
 * @author patrik
 */
public interface GameState {

    public void keyPressed(Game g, KeyEvent ke);
}
