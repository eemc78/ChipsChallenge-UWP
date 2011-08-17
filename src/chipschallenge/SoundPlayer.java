
package chipschallenge;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SoundPlayer {
    private Map<sounds, Clip> clips = new HashMap<sounds, Clip>();

    public static enum sounds {
        TAKEITEM, BOMB, CHIPHUM, DOOR, EXIT, TELEPORT, WATER, DIE, TAKECHIP,
        BUTTON, THIEF
    }

    private LineListener ll = new LineListener() {

        public void update(LineEvent le) {
            if(le.getType() == LineEvent.Type.STOP) {
            }
        }
    };

    private void loadSoundAs(String filename, sounds s) {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(filename));
            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(ll);
            clip.open(sound);
            clips.put(s, clip);
        } catch (LineUnavailableException ex) {
            // IGnore
        } catch (UnsupportedAudioFileException ex) {
            // Ignore
        } catch (IOException ex) {
            // Ignore
        }
    }

    private SoundPlayer() {
        loadSoundAs("BLIP2.WAV", sounds.TAKEITEM);
        loadSoundAs("HIT3.WAV", sounds.BOMB);
        loadSoundAs("OOF3.WAV", sounds.CHIPHUM);
        loadSoundAs("DOOR.WAV", sounds.DOOR);
        loadSoundAs("DITTY.WAV", sounds.EXIT);
        loadSoundAs("TELEPORT.WAV", sounds.EXIT);
        loadSoundAs("WATER2.WAV", sounds.EXIT);
        loadSoundAs("BUMMER.WAV", sounds.DIE);
        loadSoundAs("CLICK3.WAV", sounds.TAKECHIP);
        loadSoundAs("POP2.WAV", sounds.BUTTON);
        loadSoundAs("STRIKE.WAV", sounds.THIEF);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            for(Clip c : clips.values())
                c.close();
        } catch(Exception e) {
        } finally {
            super.finalize();
        }
    }


    private static SoundPlayer mInstance = null;
    public static synchronized SoundPlayer getInstance() {
        if (mInstance == null) {
            mInstance = new SoundPlayer();
        }
        return mInstance;
    }

    public void playSound(sounds s) {
        Clip c = clips.get(s);
        if (c != null) {
            if (c.isRunning()) {
                c.stop();
            }
            c.setFramePosition(0);
            c.start();
        }
    }

}
