/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author patrik
 */
public class GUI extends Frame implements GameListener, KeyListener {

    public GUI() {
        super("Chip's Challenge");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setSize (520,400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    public void tick() {
        this.repaint();
    }

    public void keyTyped(KeyEvent ke) {}

    public void keyPressed(KeyEvent ke) {
        Game.getInstance().keyPressed(ke);
    }

    public void keyReleased(KeyEvent ke) {}

}
