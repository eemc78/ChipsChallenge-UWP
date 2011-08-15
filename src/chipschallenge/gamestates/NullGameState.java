package chipschallenge.gamestates;

import chipschallenge.Game;
import java.awt.event.KeyEvent;

/**
 *
 * @author patrik
 */
public class NullGameState implements GameState {

    private NullGameState() {
    }
    private static NullGameState mInstance = null;

    public static synchronized NullGameState getInstance() {
        if (mInstance == null) {
            mInstance = new NullGameState();
        }
        return mInstance;
    }

    public void keyPressed(Game g, KeyEvent ke) {
        //Do nothing
    }
}
