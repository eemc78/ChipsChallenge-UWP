package chipschallenge.gui;

import chipschallenge.Game;
import chipschallenge.GameLevel;
import chipschallenge.NextLevelListener;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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
public class GUI extends Frame implements NextLevelListener {

    private Image mBackground;
    private PlayField mPlayField;
    private Hud mHud;
    private GUIMenu mMenu;

    private GUI() {
        super("Chip's Challenge");
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setSize(520, 400);
        try {
            mBackground = ImageIO.read(new File("background.bmp"));
        } catch (IOException ex) {
            msgDialog("Couldn't load background.bmp");
            System.exit(-1);
        }

        setLayout(null);
        mPlayField = new PlayField(9, 9);
        mPlayField.setBounds(37, 77, 288, 288);
        mHud = new Hud();
        mHud.setBounds(344, 70, 154, 300);
        add(mPlayField);
        add(mHud);
        setIconImage(HudImageFactory.getInstance().getIcon());
        setResizable(false);
        Game.getInstance().addNextLevelListener(this);
        mMenu = GUIMenu.getInstance();
        setMenuBar(mMenu);
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
        if (mInstance == null) {
            mInstance = new GUI();
        }
        return mInstance;
    }

    @Override
    public void repaint() {
        //super.repaint();
        mPlayField.repaint();
    }

    @Override
    public void paint(Graphics g) {
        Image im = HudImageFactory.getInstance().getWindowBackground();
        g.drawImage(im, 5, 45, this);
    }

    public static void main(String[] args) {
        GUI.getInstance();
    }

    public void msgDialog(String msg) {
        mPlayField.repaint();
        MsgBox msgbox = new MsgBox(this, msg, false);
        mHud.repaintEverything();
    }

    public boolean confirmDialog(String msg) {
        mPlayField.repaint();
        MsgBox msgbox = new MsgBox(this, msg, true);
        return msgbox.isOk;
    }
    
    public void setChipsLeft(int n) {
    }

    public void setTimeLeft(int t) {
    }

    public void scoreDialog(GameLevel mLevel) {
        msgDialog(mLevel.getScore());
    }

    public boolean repaintIfNecessary(Point from) {
        return mPlayField.repaintIfNecessary(from);
    }

    public void nextLevel(GameLevel level) {
        this.setTitle("Chips's Challenge: " + level.getMapTitle());
        mMenu.setPreviousPossible(level.getLevelNumber()>1);
    }

}
