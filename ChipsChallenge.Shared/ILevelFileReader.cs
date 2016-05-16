namespace ChipsChallenge.Shared
{
    public interface ILevelFileReader
    {
        int ReadUnsignedDWord();

        int ReadUnsignedWord();

        int ReadUnsignedByte();

        void Seek(long offset);

        void SkipBytes(int bytes);

        void ReadFully(sbyte[] arr);
    }
}