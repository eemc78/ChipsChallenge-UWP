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
import java.awt.Panel;
import java.awt.image.BufferedImage;

/**
 *
 * @author patrik
 */
class PlayField extends Panel {

    private int mWidth;
    private int mHeight;

    public PlayField(int width, int height) {
        mWidth = width;
        mHeight = height;
        setPreferredSize(new Dimension(width*32,height*32));
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        GameLevel gl = Game.getInstance().getLevel();
        for(int x = 0; x < mWidth; x++)
            for(int y = 0; y < mHeight; y++)
                g.drawImage(gl.getBlockContainer(x, y).getImage(), x*32, y*32, null);
    }

}
