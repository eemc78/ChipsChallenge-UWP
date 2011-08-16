
package chipschallenge;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;


public class Music {
    private static List<Sequence> loadedSongs = new ArrayList<Sequence>();
    private static Sequencer sequencer = null;
    private static int songIndex = 0;

    private static Sequence loadMidi(String filename) throws InvalidMidiDataException {
        try {
            return MidiSystem.getSequence(new FileInputStream(filename));
        } catch (IOException ex) {
            // File not found
            return null;
        }
    }
    
    static {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addMetaEventListener(new MetaEventListener() {
                public void meta(MetaMessage mm) {
                    if (mm.getType() == 47) { // End of track
                        if (sequencer.isOpen()) {
                            sequencer.start();
                        }
                    }
                }
            });
            Sequence sq = null;
            for (int i = 1; i <= 99; i++) {
                String num = (i < 10) ? "0" + i : String.valueOf(i);
                sq = loadMidi("CHIP" + num + ".MID");
                if (sq == null) {
                    break;
                } else {
                    loadedSongs.add(sq);
                }
            }
            sq = loadMidi("CANYON.MID");
            if (sq != null) {
                loadedSongs.add(sq);
            }
        } catch (InvalidMidiDataException ex) {
            // No music today
        } catch (MidiUnavailableException ex) {
            // No music today
        }
    }

    public static void playNextSong() {
        if (sequencer != null) {
            int size = loadedSongs.size();
            if (size > 0) {
                try {                    
                    songIndex++;
                    songIndex = songIndex > (size-1) ? 0 : songIndex;
                    sequencer.stop();
                    sequencer.setSequence(loadedSongs.get(songIndex));
                    sequencer.start();
                } catch (InvalidMidiDataException ex) {
                } 
            }
        }
    }

}
