/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chipschallenge;

import java.io.IOException;

/**
 *
 * @author patrik
 */
public interface LevelFileReader {

    public int readUnsignedDWord() throws IOException;

    public int readUnsignedWord() throws IOException;

    public int readUnsignedByte() throws IOException;

    public void seek(long offset) throws IOException;

    public void skipBytes(int bytes) throws IOException;

    public void close() throws IOException;

    public void readFully(byte[] arr) throws IOException;
}
