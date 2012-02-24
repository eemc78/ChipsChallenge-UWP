package chipschallenge;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class MusicPlayer {

    private List<Sequence> loadedSongs = new ArrayList<Sequence>();
    private Sequencer sequencer = null;
    private int songIndex = 0;
    private boolean musicOn = true;

    private MusicPlayer() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence sq = null;
            for (int i = 1; i <= 99; i++) {
                String num = (i < 10) ? "0" + i : String.valueOf(i);
                sq = loadMidi(getClass().getResource("/CHIP" + num + ".MID"));
                if (sq == null) {
                    break;
                } else {
                    loadedSongs.add(sq);
                }
            }
            sq = loadMidi(getClass().getResource("/CANYON.MID"));
            if (sq != null) {
                loadedSongs.add(sq);
            }
        } catch (InvalidMidiDataException ex) {
            // No music today
        } catch (MidiUnavailableException ex) {
            // No music today
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            sequencer.close();
        } catch (Exception e) {
        } finally {
            super.finalize();
        }
    }
    private static MusicPlayer mInstance = null;

    public static synchronized MusicPlayer getInstance() {
        if (mInstance == null) {
            mInstance = new MusicPlayer();
        }
        return mInstance;
    }

    private static Sequence loadMidi(URL file) throws InvalidMidiDataException {
        try {
            Sequence sc = null;
            if (file != null) {
                sc = MidiSystem.getSequence(file);
            }
            return sc;
        } catch (IOException ex) {
            // File not found
            return null;
        }
    }

    public void playNextSong() {
        if (musicOn) {
            if (sequencer != null) {
                int size = loadedSongs.size();
                if (size > 0) {
                    try {
                        songIndex++;
                        songIndex = songIndex > (size - 1) ? 0 : songIndex;
                        sequencer.stop();
                        sequencer.setSequence(loadedSongs.get(songIndex));
                        sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
                        sequencer.start();
                    } catch (InvalidMidiDataException ex) {
                    }
                }
            }
        }
    }

    public void setMusic(boolean m) {
        musicOn = m;
        if (sequencer != null) {
            if (m) {
                if (sequencer.getSequence() != null) {
                    sequencer.start();
                }
            } else {
                if (sequencer.isRunning()) {
                    sequencer.stop();
                }
            }

        }
    }
}
