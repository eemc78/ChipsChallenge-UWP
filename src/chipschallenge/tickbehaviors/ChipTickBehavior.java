package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.Move.Moves;
import chipschallenge.gui.GUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author patrik
 */
public class ChipTickBehavior extends KeyAdapter implements BlockTickBehavior {

    private ChipTickBehavior() {}
    private static ChipTickBehavior mInstance = null;
    public static synchronized ChipTickBehavior getInstance() {
        if(mInstance == null)
            mInstance = new ChipTickBehavior();
        return mInstance;
    }

    private Queue<Moves> proposedMoves = new LinkedList<Moves>();
    private int mTicksBeforeTurn;

    public void tick(Block caller) throws BlockContainerFullException {
        if(!proposedMoves.isEmpty()) {
            if(caller.isOnForceFloor())
                Game.getInstance().removeForcedMove(caller);
            if(!caller.move(proposedMoves.poll())) {
                Game.getInstance().getLevel().getBlockContainer(caller).moveTo(caller);
            }      
            mTicksBeforeTurn = 12;
        }
        if(mTicksBeforeTurn > 0) {
            mTicksBeforeTurn--;
            if(mTicksBeforeTurn == 0) {
                caller.setFacing(Moves.DOWN);
                GUI.getInstance().repaint();
            }
        }
    }

    //TODO: Handle mouse clicks
    public void moveTo(int x, int y) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                proposedMoves.clear();
                proposedMoves.offer(Moves.UP);
                break;
            case KeyEvent.VK_DOWN:
                proposedMoves.clear();
                proposedMoves.offer(Moves.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                proposedMoves.clear();
                proposedMoves.offer(Moves.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                proposedMoves.clear();
                proposedMoves.offer(Moves.RIGHT);
                break;
        }
    }

}
