
package chipschallenge;

import java.io.IOException;


public class Options {
    private static Options mInstance = null;
    public static synchronized Options getInstance() {
        if (mInstance == null) {
            mInstance = new Options();
        }
        return mInstance;
    }

    private boolean backgroundMusic;
    private boolean soundEffects;
    private boolean color;


    private Options() {
        try {
            // TODO: Load saved options
            throw new IOException("Loading settings from file isn't implemented yet");
        } catch(IOException ex) {
            backgroundMusic = true;
            soundEffects = true;
            color = true;
        }
    }

    public boolean isBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(boolean backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
        MusicPlayer.getInstance().setMusic(backgroundMusic);
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean isSoundEffects() {
        return soundEffects;
    }

    public void setSoundEffects(boolean soundEffects) {
        this.soundEffects = soundEffects;
        SoundPlayer.getInstance().setSound(soundEffects);
    }


}
