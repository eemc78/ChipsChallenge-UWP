/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Move.Moves;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author patrik
 */
public class ChipTickBehavior extends KeyAdapter implements BlockTickBehavior{

    private ChipTickBehavior() {System.out.println("CREATED");}
    private static ChipTickBehavior mInstance = null;
    public static synchronized ChipTickBehavior getInstance() {
        if(mInstance == null)
            mInstance = new ChipTickBehavior();
        return mInstance;
    }

    private Queue<Moves> proposedMoves = new LinkedList<Moves>();

    public void tick(Block caller) throws BlockContainerFullException {
        if(!proposedMoves.isEmpty()) {
            caller.move(proposedMoves.poll());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { System.out.println("KEY HIT");
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
