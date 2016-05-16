package chipschallenge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileLevelReader implements LevelFileReader {

    private final RandomAccessFile raf;

    private RandomAccessFileLevelReader() {
        raf = null;
    }

    private RandomAccessFileLevelReader(String filename) throws FileNotFoundException {
        raf = new RandomAccessFile(filename, "r");
    }

    public static RandomAccessFileLevelReader create(String filename) {
        try {
            return new RandomAccessFileLevelReader(filename);
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            raf.close();
        } catch (Exception e) {
        } finally {
            super.finalize();
        }
    }

    public int readUnsignedDWord() throws IOException {
        return ByteSwapper.swap(raf.readInt()) & 0xFFFFFFFF;
    }

    public int readUnsignedWord() throws IOException {
        return ByteSwapper.swap(raf.readShort()) & 0xFFFF;
    }

    public int readUnsignedByte() throws IOException {
        return raf.readByte() & 0xFF;
    }

    public void seek(long offset) throws IOException {
        raf.seek(offset);
    }

    public void skipBytes(int bytes) throws IOException {
        raf.skipBytes(bytes);
    }

    public void close() throws IOException {
        raf.close();
    }

    public void readFully(byte[] arr) throws IOException {
        raf.readFully(arr);
    }
}
