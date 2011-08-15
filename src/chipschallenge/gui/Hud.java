package chipschallenge.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

/**
 *
 * @author wasd
 */
class Hud extends Panel {

    private int level = 0;
    private int time = 0;
    private int chipsLeft = 0;
    private boolean levelNeedsRepaint = false;
    private boolean timeNeedsRepaint = false;
    private boolean chipsLeftNeedsRepaint = false;
    private boolean backgroundPainted = false;

    public Hud() {
        setPreferredSize(new Dimension(154, 300));
        setVisible(true);

        //for debugging:
        setChipsLeft(7);
        setTime(123);
        setLevel(14);
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        if (!backgroundPainted) {
            g.drawImage(HudImageFactory.getInstance().getHudBackground(), 0, 0, null);
            backgroundPainted = true;
        }

        if (levelNeedsRepaint) {
            String s = intToPaintableString(level);
            int x=83;
            int y=38;
            for (int i = s.length()-1; i >=0; i--) {
                Image img = HudImageFactory.getInstance().getNumber(s.charAt(i), false);
                g.drawImage(img, x, y, null);
                x-=17;
            }
            levelNeedsRepaint = false;
        }
        if (timeNeedsRepaint) {
            String s = intToPaintableString(time);
            int x=83;
            int y=100;
            for (int i = s.length()-1; i >=0; i--) {
                Image img = HudImageFactory.getInstance().getNumber(s.charAt(i), false);
                g.drawImage(img, x, y, null);
                x-=17;
            }
            timeNeedsRepaint = false;
        }
        if (chipsLeftNeedsRepaint) {
            String s = intToPaintableString(chipsLeft);
            int x=83;
            int y=190;
            for (int i = s.length()-1; i >=0; i--) {
                Image img = HudImageFactory.getInstance().getNumber(s.charAt(i), true);
                g.drawImage(img, x, y, null);
                x-=17;
            }
            chipsLeftNeedsRepaint = false;
        }
    }

    public void setChipsLeft(int chipsLeft) {
        if (this.chipsLeft == chipsLeft) {
            return;
        }
        this.chipsLeft = chipsLeft;
        chipsLeftNeedsRepaint = true;
        repaint();
    }

    public void setLevel(int level) {
        if (this.level == level) {
            return;
        }
        this.level = level;
        levelNeedsRepaint = true;
        repaint();
    }

    public void setTime(int time) {
        if (this.time == time) {
            return;
        }
        this.time = time;
        timeNeedsRepaint = true;
        repaint();
    }

    /**
     * Makes an int into a 3-character String that covers the entire textfield when painted
     * @param s The string to fix
     * @return A string that is 3 characters of length
     */
    private String intToPaintableString(int n){
        String s = String.valueOf(n);
        switch(s.length()){
            case 3: return s;
            case 2: return "x"+s;
            case 1: return "xx"+s;
            case 0: return "xxx";
        }
        return "999";
    }
}
