package chipschallenge.tickbehaviors;

import chipschallenge.Block;
import chipschallenge.BlockContainerFullException;
import chipschallenge.Game;
import chipschallenge.Inventory.Boots;
import chipschallenge.Move;
import chipschallenge.Move.Moves;
import chipschallenge.SoundPlayer;
import chipschallenge.SoundPlayer.sounds;
import chipschallenge.gui.GUI;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

public class ChipTickBehavior extends KeyAdapter implements BlockTickBehavior {

    private ChipTickBehavior() {
    }
    private static ChipTickBehavior mInstance = null;

    public static synchronized ChipTickBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new ChipTickBehavior();
        }
        return mInstance;
    }
    private final Queue<Moves> proposedMoves = new LinkedList<Moves>();
    private int mTicksBeforeTurn;
    private int chipTicks = 0;

    public void tick(Block caller) throws BlockContainerFullException {
        chipTicks = (chipTicks + 1) % Game.SPEED_FRAC;
        if (chipTicks == 0 || caller.wasForced()) {
            synchronized (proposedMoves) {
                if (!proposedMoves.isEmpty()) {
                    if (caller.isOnForceFloor()) {
                        Game.getInstance().removeForcedMove(caller);                        
                    }
                    if (caller.isOnIce() && !Game.getInstance().getInventory().hasBoots(Boots.ICESKATES)) {
                        proposedMoves.poll(); // Ignore proposed move
                    } else if (!caller.move(proposedMoves.poll())) {
                        if (caller.isOnForceFloor()) {//caller.setForced(false);
                            Game.getInstance().getLevel().getBlockContainer(caller).moveTo(caller);
                        }
                        proposedMoves.clear();
                        SoundPlayer.getInstance().playSound(sounds.CHIPHUM);
                    } else {
                        caller.setForced(false);
                    }
                    mTicksBeforeTurn = 12;
                }
            }
        }

        if (!caller.isOnIce() && mTicksBeforeTurn > 0) {
            mTicksBeforeTurn--;
            if (mTicksBeforeTurn == 0) {
                caller.setFacing(Moves.DOWN);
                GUI.getInstance().repaint();
            }
        }
    }

    //TODO: Handle mouse clicks
    public void moveTo(Point goal) {
        synchronized (proposedMoves) {
            proposedMoves.clear();
            Point start = Game.getInstance().getLevel().findChip();
            while (!start.equals(goal)) {
                int dx = goal.x - start.x;
                int dy = goal.y - start.y;
                int pdx = Math.abs(dx);
                int pdy = Math.abs(dy);
                Moves proposedMove = null;
                if (pdx > pdy) {
                    if (dx > 0) {
                        proposedMove = Moves.RIGHT;
                    } else {
                        proposedMove = Moves.LEFT;
                    }
                } else {
                    if (dy > 0) {
                        proposedMove = Moves.DOWN;
                    } else {
                        proposedMove = Moves.UP;
                    }
                }
                Move.updatePoint(start, proposedMove);
                proposedMoves.offer(proposedMove);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        synchronized (proposedMoves) {
            switch (e.getKeyCode()) {
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
}
