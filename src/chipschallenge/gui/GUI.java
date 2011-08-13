/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.gui;

import chipschallenge.Game;
import chipschallenge.GameListener;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author patrik
 */
public class GUI extends Frame implements GameListener {

    private Image mBackground;
    private Panel mPlayField;
    private Panel mHud;

    private GUI() {
        super("Chip's Challenge");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        Game.getInstance().addGameListener(this);
        setSize (520,400);
        try {
            mBackground = ImageIO.read(new File("background.bmp"));
        } catch (IOException ex) {
            msgDialog("Couldn't load background.bmp");
            System.exit(-1);
        }
        FlowLayout layout = new FlowLayout();
        layout.setHgap(25);
        layout.setVgap(30);
        setLayout(layout);
        mPlayField = new PlayField(9,9);
        mHud = new Hud();
        add(mPlayField);
        add(mHud);
        //pack();
        this.setResizable(false);
        setVisible(true);
    }

    @Override
    public void addKeyListener(KeyListener l) {
        super.addKeyListener(l);
        mPlayField.addKeyListener(l);
        mHud.addKeyListener(l);
    }

    private static GUI mInstance = null;
    public static synchronized GUI getInstance() {
        if(mInstance == null)
            mInstance = new GUI();
        return mInstance;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int iWidth = mBackground.getWidth(null);
        int iHeight = mBackground.getHeight(null);
        int wWidth = getWidth();
        int wHeight = getHeight();
        for(int x = 0; x < wWidth; x += iWidth)
            for(int y = 0; y < wHeight; y += iHeight)
                g.drawImage(mBackground, x, y, null);
    }

    public static void main(String[] args) {
        GUI.getInstance();
    }

    public void msgDialog(String msg) {
        mPlayField.repaint();
        MsgBox msgbox = new MsgBox(this, msg, false);
    }

    public boolean confirmDialog(String msg) {
        mPlayField.repaint();
        MsgBox msgbox = new MsgBox(this, msg, true);
        return msgbox.isOk;
    }

    public void tick() {
        mPlayField.repaint();
    }

    public void setChipsLeft(int n) {}
    public void setTimeLeft(int t) {}
    
}
