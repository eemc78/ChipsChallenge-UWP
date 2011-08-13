/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.gui;

import chipschallenge.Game;
import chipschallenge.GameLevel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;

/**
 *
 * @author patrik
 */
class PlayField extends Panel {

    private int mWidth;
    private int mHeight;
    private Image offscreen;
    private Insets insets = new Insets(4,4,4,4);

    public PlayField(int width, int height) {
        mWidth = width;
        mHeight = height;
        setPreferredSize(new Dimension(width*32,height*32));
        setVisible(true);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if(offscreen == null) {
               offscreen = createImage(getSize().width, getSize().height);
        }
        Graphics og = offscreen.getGraphics();
        GameLevel gl = Game.getInstance().getLevel();
        for(int x = 0; x < mWidth; x++)
            for(int y = 0; y < mHeight; y++)
                og.drawImage(gl.getBlockContainer(x, y).getImage(), x*32, y*32, null);
        super.paint(og);
        g.drawImage(offscreen, 0, 0, null);
        og.dispose();
    }

}
