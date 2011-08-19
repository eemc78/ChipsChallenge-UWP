package chipschallenge.gui;

import chipschallenge.Game;
import chipschallenge.GameLevel;
import chipschallenge.NextLevelListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Point;

class PlayField extends Panel implements NextLevelListener {

    private int mWidth;
    private int mHeight;
    private Image offscreen;
    private Insets insets = new Insets(4, 4, 4, 4);

    public PlayField(int width, int height) {
        mWidth = width;
        mHeight = height;
        setPreferredSize(new Dimension(width * 32, height * 32));
        Game.getInstance().addNextLevelListener(this);
        setVisible(true);
    }

    @Override
    public void update(Graphics g) {
        if (offscreen == null) {
            offscreen = createImage(getSize().width, getSize().height);
        }
        Graphics og = offscreen.getGraphics();
        GameLevel gl = Game.getInstance().getLevel();
        Point chip = gl.findChip();
        int top = getTop(gl, chip.y);
        int left = getLeft(gl, chip.x);
        for (int x = 0; x < mWidth; x++) {
            for (int y = 0; y < mHeight; y++) {
                og.drawImage(gl.getBlockContainer(x + left, y + top).getImage(), x * 32, y * 32, null);
            }
        }
        super.paint(og);
        g.drawImage(offscreen, 0, 0, null);
        og.dispose();
    }

    public int getTop(GameLevel gl, int chipY) {
        int top = chipY - 4;
        if (top < 0) {
            return 0;
        }
        if (top > (gl.getHeight() - mHeight)) {
            return (gl.getHeight() - mHeight);
        }
        return top;
    }

    public int getLeft(GameLevel gl, int chipX) {
        int left = chipX - 4;
        if (left < 0) {
            return 0;
        }
        if (left > (gl.getWidth() - mWidth)) {
            return (gl.getWidth() - mWidth);
        }
        return left;
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    // Determine whether repaint is needed
    public boolean repaintIfNecessary(Point from) {
        GameLevel gl = Game.getInstance().getLevel();
        Point chip = gl.findChip();
        int top = getTop(gl, chip.y);
        int left = getLeft(gl, chip.x);
        if (from.y >= top && from.y <= (top + mHeight) && from.x >= left && from.x <= (left + mWidth)) {
            repaint();
            return true;
        }
        return false;
    }

    public void nextLevel(GameLevel level) {
        // TODO: Show level name and password in the playfield
        System.out.println(level.getMapTitle());
    }
}
