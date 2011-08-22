
package chipschallenge;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SoundPlayer {
    private Map<sounds, Clip> clips = new HashMap<sounds, Clip>();
    private long canPlayNextTime = 0;
    private boolean soundOn = true;

    public static enum sounds {
        TAKEITEM, BOMB, CHIPHUM, DOOR, EXIT, TELEPORT, WATER, DIE, TAKECHIP,
        BUTTON, THIEF, TICK, TIMEOVER, SOCKET
    }

    private void loadSoundAs(URL file, sounds s) {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
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
        loadSoundAs(getClass().getResource("/BLIP2.WAV"), sounds.TAKEITEM);
        loadSoundAs(getClass().getResource("/HIT3.WAV"), sounds.BOMB);
        loadSoundAs(getClass().getResource("/OOF3.WAV"), sounds.CHIPHUM);
        loadSoundAs(getClass().getResource("/DOOR.WAV"), sounds.DOOR);
        loadSoundAs(getClass().getResource("/DITTY1.WAV"), sounds.EXIT);
        loadSoundAs(getClass().getResource("/TELEPORT.WAV"), sounds.TELEPORT);
        loadSoundAs(getClass().getResource("/WATER2.WAV"), sounds.WATER);
        loadSoundAs(getClass().getResource("/BUMMER.WAV"), sounds.DIE);
        loadSoundAs(getClass().getResource("/CLICK3.WAV"), sounds.TAKECHIP);
        loadSoundAs(getClass().getResource("/CLICK1.WAV"), sounds.TICK);
        loadSoundAs(getClass().getResource("/POP2.WAV"), sounds.BUTTON);
        loadSoundAs(getClass().getResource("/STRIKE.WAV"), sounds.THIEF);
        loadSoundAs(getClass().getResource("/BELL.WAV"), sounds.TIMEOVER);
        loadSoundAs(getClass().getResource("/CHIMES.WAV"), sounds.SOCKET);
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
        if (soundOn) {
            Clip c = clips.get(s);
            if (c != null) {
                long time = System.currentTimeMillis();
                if (time > canPlayNextTime || (s != sounds.BOMB && s != sounds.BUTTON && s != sounds.CHIPHUM)) {
                    if (c.isRunning()) {
                        c.stop();
                    }
                    c.setFramePosition(0);
                    c.start();
                    canPlayNextTime = time + c.getMicrosecondLength();
                }
            }
        }
    }

    public void setSound(Boolean s) {
        soundOn = s;
    }

}
