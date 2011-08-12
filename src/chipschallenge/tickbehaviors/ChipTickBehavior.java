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
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author patrik
 */
public class ChipTickBehavior extends KeyAdapter implements BlockTickBehavior{

    private Queue<Moves> proposedMoves = new LinkedList<Moves>();

    public void tick(Block caller) throws BlockContainerFullException {
        if(!proposedMoves.isEmpty()) {
            caller.move(proposedMoves.poll());
        }
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
                proposedMoves.offer(Moves.UP);
                break;
            case KeyEvent.VK_LEFT:
                proposedMoves.clear();
                proposedMoves.offer(Moves.UP);
                break;
            case KeyEvent.VK_RIGHT:
                proposedMoves.clear();
                proposedMoves.offer(Moves.UP);
                break;
        }
    }

}
