package chipschallenge.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
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
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        if (!backgroundPainted) {
            g.drawImage(HudImageFactory.getInstance().getHudBackground(), 0, 0, null);
            backgroundPainted = true;
        }

        if (levelNeedsRepaint) {
            //TODO paint only Level
            levelNeedsRepaint = false;
        }
        if (timeNeedsRepaint) {
            //TODO paint only Time
            timeNeedsRepaint = false;
        }
        if (chipsLeftNeedsRepaint) {
            //TODO paint only Chips left
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
}
