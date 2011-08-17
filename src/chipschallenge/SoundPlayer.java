
package chipschallenge;

import java.io.File;
import java.io.IOException;
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

    public static enum sounds {
        TAKEITEM, BOMB, CHIPHUM, DOOR, EXIT, TELEPORT, WATER, DIE, TAKECHIP,
        BUTTON, THIEF, TICK, TIMEOVER, SOCKET
    }

    private void loadSoundAs(String filename, sounds s) {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(filename));
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
        loadSoundAs("BLIP2.WAV", sounds.TAKEITEM);
        loadSoundAs("HIT3.WAV", sounds.BOMB);
        loadSoundAs("OOF3.WAV", sounds.CHIPHUM);
        loadSoundAs("DOOR.WAV", sounds.DOOR);
        loadSoundAs("DITTY1.WAV", sounds.EXIT);
        loadSoundAs("TELEPORT.WAV", sounds.TELEPORT);
        loadSoundAs("WATER2.WAV", sounds.WATER);
        loadSoundAs("BUMMER.WAV", sounds.DIE);
        loadSoundAs("CLICK3.WAV", sounds.TAKECHIP);
        loadSoundAs("CLICK1.WAV", sounds.TICK);
        loadSoundAs("POP2.WAV", sounds.BUTTON);
        loadSoundAs("STRIKE.WAV", sounds.THIEF);
        loadSoundAs("BELL.WAV", sounds.TIMEOVER);
        loadSoundAs("CHIMES.WAV", sounds.SOCKET);
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
            long time = System.currentTimeMillis();
            if (time > canPlayNextTime || s == sounds.EXIT) {
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
