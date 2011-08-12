/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.gui;

import chipschallenge.Game;
import chipschallenge.GameListener;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author patrik
 */
public class GUI extends Frame implements GameListener, KeyListener {

    private GUI() {
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

    private static GUI mInstance = null;
    public static synchronized GUI getInstance() {
        if(mInstance == null)
            mInstance = new GUI();
        return mInstance;
    }

    public static void main(String[] args) {
        GUI.getInstance();
    }

    public void msgDialog(String msg) {
        MsgBox msgbox = new MsgBox(this, msg, false);
    }

    public boolean confirmDialog(String msg) {
        MsgBox msgbox = new MsgBox(this, msg, true);
        return msgbox.isOk;
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
