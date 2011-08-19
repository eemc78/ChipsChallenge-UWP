
package chipschallenge.gui;

import chipschallenge.Options;
import java.awt.CheckboxMenuItem;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GUIMenu extends MenuBar implements ActionListener, ItemListener {

    private Menu game;
    private Menu options;
    private Menu level;
    private Menu help;
    private MenuItem newGame;
    private MenuItem pause;
    private MenuItem bestTimes;
    private MenuItem exit;
    private CheckboxMenuItem backgroundMusic;
    private CheckboxMenuItem soundEffects;
    private CheckboxMenuItem color;
    private MenuItem restart;
    private MenuItem next;
    private MenuItem previous;
    private MenuItem goTo;
    private MenuItem contents;
    private MenuItem howToPlay;
    private MenuItem commands;
    private MenuItem howToUseHelp;
    private MenuItem aboutChipsChallenge;

    private GUIMenu(){
        
        setFont(new Font("Arial", Font.PLAIN, 11));

        // Game menu
        game      = new Menu("Game");
        newGame   = new MenuItem("New Game");
        pause     = new MenuItem("Pause");
        bestTimes = new MenuItem("Best Times");
        exit      = new MenuItem("Exit");
        newGame.addActionListener(this);
        pause.addActionListener(this);
        bestTimes.addActionListener(this);
        exit.addActionListener(this);
        game.add(newGame);
        game.add(pause);
        game.add(bestTimes);
        game.addSeparator();
        game.add(exit);
        add(game);

        // Options menu
        options         = new Menu("Options");
        backgroundMusic = new CheckboxMenuItem("Background Music");
        soundEffects    = new CheckboxMenuItem("Sound Effects");
        color           = new CheckboxMenuItem("Color");
        Options opts = Options.getInstance();
        backgroundMusic.setState(opts.isBackgroundMusic());
        soundEffects.setState(opts.isSoundEffects());
        color.setState(opts.isColor());
        backgroundMusic.addItemListener(this);
        soundEffects.addItemListener(this);
        color.addItemListener(this);
        options.add(backgroundMusic);
        options.add(soundEffects);
        options.add(color);
        add(options);

        // Level menu
        level    = new Menu("Level");
        restart  = new MenuItem("Restart");
        next     = new MenuItem("Next");
        previous = new MenuItem("Previous");
        goTo     = new MenuItem("Go To");
        restart.addActionListener(this);
        next.addActionListener(this);
        previous.addActionListener(this);
        goTo.addActionListener(this);
        level.add(restart);
        level.add(next);
        level.add(previous);
        level.add(goTo);
        add(level);


        // Help menu
        help                = new Menu("Help");
        contents            = new MenuItem("Contents");
        howToPlay           = new MenuItem("How to Play");
        commands            = new MenuItem("Comands");
        howToUseHelp        = new MenuItem("How to Use Help");
        aboutChipsChallenge = new MenuItem("About Chip's Challenge...");
        contents.addActionListener(this);
        howToPlay.addActionListener(this);
        commands.addActionListener(this);
        howToUseHelp.addActionListener(this);
        aboutChipsChallenge.addActionListener(this);
        help.add(contents);
        help.add(howToPlay);
        help.add(commands);
        help.add(howToUseHelp);
        help.addSeparator();
        help.add(aboutChipsChallenge);
        add(help);
    }

    private static GUIMenu mInstance = null;
    public static synchronized GUIMenu getInstance() {
        if(mInstance == null)
            mInstance = new GUIMenu();
        return mInstance;
    }

    public void actionPerformed(ActionEvent ae) {
        Object src = ae.getSource();
        if(src == newGame) {
            newGame();
        } else if(src == pause) {
            pause();
        } else if(src == bestTimes) {
            bestTimes();
        } else if(src == exit) {
            exit();
        } else if(src == restart) {
            restart();
        } else if(src == next) {
            next();
        } else if(src == previous) {
            previous();
        } else if(src == goTo) {
            goTo();
        } else if(src == contents) {
            contents();
        } else if(src == howToPlay) {
            howToPlay();
        } else if(src == commands) {
            commands();
        } else if(src == howToUseHelp) {
            howToUseHelp();
        } else if(src == aboutChipsChallenge) {
            aboutChipsChallenge();
        }
    }

    private void newGame() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void pause() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void bestTimes() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void exit() {
        System.exit(0);
    }

    private void backgroundMusic() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void soundEffects() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void color() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void restart() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void next() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void previous() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void goTo() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void contents() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void howToPlay() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void commands() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void howToUseHelp() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void aboutChipsChallenge() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void setPreviousPossible(Boolean p) {
        previous.setEnabled(p);
    }

    public void itemStateChanged(ItemEvent ie) {
        Object src = ie.getItem();
        boolean state = ie.getStateChange() == ItemEvent.SELECTED;
        Options opts = Options.getInstance();
        if(src == backgroundMusic) {
            opts.setBackgroundMusic(state);
        } else if(src == soundEffects) {
            opts.setSoundEffects(state);
        } else if(src == color) {
            opts.setColor(state);
        }
    }
}
