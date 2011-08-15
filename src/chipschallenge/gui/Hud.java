package chipschallenge.gui;

import chipschallenge.GameLevel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;

/**
 *
 * @author patrik
 */
class Hud extends Panel {

    private GameLevel mLevel;
    private int top = 0;
    private int left = 0;
    
    private int level = 0;
    private int time = 0;
    private int chipsLeft = 0;

    public Hud() {
        add(new Label("Hud"));
        setPreferredSize(new Dimension(9*16,9*32));
        setVisible(true);
    }

    public void setLevel(GameLevel gl) {
        mLevel = gl;
        //TODO: Adjust top and left to starting position
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public void setChipsLeft(int chipsLeft) {
        this.chipsLeft = chipsLeft;
        repaint();
    }

    public void setLevel(int level) {
        this.level = level;
        repaint();
    }

    public void setTime(int time) {
        this.time = time;
        repaint();
    }

}
