package chipschallenge.gui;

import chipschallenge.Block.Type;
import chipschallenge.BlockFactory;
import chipschallenge.BlockImageFactory;
import chipschallenge.ChipListener;
import chipschallenge.Game;
import chipschallenge.GameLevel;
import chipschallenge.Inventory;
import chipschallenge.InventoryListener;
import chipschallenge.Move.Moves;
import chipschallenge.NextLevelListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

/**
 *
 * @author wasd
 */
class Hud extends Panel implements ChipListener, NextLevelListener, InventoryListener, HintListener, TimeListener {

    private int level = 0;
    private int time = 0;
    private int chipsLeft = 0;
    private Inventory inventory = Game.getInstance().getInventory();
    private boolean levelNeedsRepaint = true;
    private boolean timeNeedsRepaint = true;
    private boolean chipsLeftNeedsRepaint = true;
    private boolean backgroundNeedsRepaint = true;
    private boolean inventoryNeedsRepaint = true;

    public Hud() {
        setPreferredSize(new Dimension(154, 300));
        setVisible(true);

        // Listen for collected chips
        Game.getInstance().addChipListener(this);

        // Listen for level change
        Game.getInstance().addNextLevelListener(this);

        // Listen for inventory change
        Game.getInstance().getInventory().addInventoryListener(this);

        // Listen for hints
        Game.getInstance().addHintListener(this);

        // Listen for time
        Game.getInstance().addTimeListener(this);

        //for debugging:
        setChipsLeft(7);
        setTime(123);
        setLevel(14);
    }

    @Override
    public void paint(Graphics g) {
        if (backgroundNeedsRepaint) {
            g.drawImage(HudImageFactory.getInstance().getHudBackground(), 0, 0, null);
            backgroundNeedsRepaint = false;
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
        if(inventoryNeedsRepaint) {
            BlockFactory bf = Game.getInstance().getBlockFactory();
            BlockImageFactory bif = BlockImageFactory.getInstance();
            int x = 13;
            int y = 221;
            Image im = null;
            Image empty = bif.get(Type.FLOOR, Moves.UP, false);
            im = empty;
            if(inventory.hasKey(Inventory.Key.RED))
                im = bif.get(Type.REDKEY, Moves.UP, false);
            g.drawImage(im, x, y, null);
            im = empty;
            if(inventory.hasKey(Inventory.Key.BLUE))
                im = bif.get(Type.BLUEKEY, Moves.UP, false);
            g.drawImage(im, x+32, y, null);
            im = empty;
            if(inventory.hasKey(Inventory.Key.YELLOW))
                im = bif.get(Type.YELLOWKEY, Moves.UP, false);
            g.drawImage(im, x+2*32, y, null);
            im = empty;
            if(inventory.hasKey(Inventory.Key.GREEN))
                im = bif.get(Type.GREENKEY, Moves.UP, false);
            g.drawImage(im, x+3*32, y, null);
            y+=32;
            im = empty;
            if(inventory.hasBoots(Inventory.Boots.ICESKATES))
                im = bif.get(Type.ICESKATES, Moves.UP, false);
            g.drawImage(im, x, y, null);
            im = empty;
            if(inventory.hasBoots(Inventory.Boots.SUCTIONBOOTS))
                im = bif.get(Type.SUCTIONBOOTS, Moves.UP, false);
            g.drawImage(im, x+32, y, null);
            im = empty;
            if(inventory.hasBoots(Inventory.Boots.FIREBOOTS))
                im = bif.get(Type.FIREBOOTS, Moves.UP, false);
            g.drawImage(im, x+2*32, y, null);
            im = empty;
            if(inventory.hasBoots(Inventory.Boots.FLIPPERS))
                im = bif.get(Type.FLIPPERS, Moves.UP, false);
            g.drawImage(im, x+3*32, y, null);
        }
        g.dispose();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
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

    public void repaintEverything(){
        levelNeedsRepaint = true;
        timeNeedsRepaint = true;
        chipsLeftNeedsRepaint = true;
        backgroundNeedsRepaint = true;
        repaint();
    }

    public void chipTaken() {
        setChipsLeft(chipsLeft-1);
    }

    public void nextLevel(GameLevel level) {
        setChipsLeft(level.getNumChipsNeeded());
        setLevel(level.getLevelNumber());
        setTime(level.getNumSeconds());        
    }

    public void inventoryChange(Inventory i) {
        inventory = i;
        inventoryNeedsRepaint = true;
        repaint();
        // TODO: Repaint keys and boots
    }

    public void showHint(String txt) {
        // TODO: Show hint in Hud
        System.out.println(txt);
    }

    public void hideHint() {
        // TODO: Hide hint
    }

    public void timeLeft(int seconds) {
        setTime(seconds);
    }
}
