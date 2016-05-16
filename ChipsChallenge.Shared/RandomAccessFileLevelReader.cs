using System.IO;

namespace ChipsChallenge.Shared
{
    public class RandomAccessFileLevelReader : ILevelFileReader
    {
        private readonly BinaryReader binaryReader;

        private RandomAccessFileLevelReader(Stream levelFileStream)
        {
            binaryReader = new BinaryReader(levelFileStream);
        }

        public static RandomAccessFileLevelReader Create(Stream levelFileStream)
        {
            try
            {
                return new RandomAccessFileLevelReader(levelFileStream);
            }
            catch (FileNotFoundException)
            {
                return null;
            }
        }

        public virtual int ReadUnsignedDWord()
        {
            return binaryReader.ReadInt32();
        }

        public virtual int ReadUnsignedWord()
        {
            return binaryReader.ReadInt16();
        }

        public virtual int ReadUnsignedByte()
        {
            return binaryReader.ReadByte();
        }

        public virtual void Seek(long offset)
        {
            binaryReader.BaseStream.Seek(offset, SeekOrigin.Begin);
        }

        public virtual void SkipBytes(int bytes)
        {
            for (int i = 0; i < bytes; i++)
            {
                binaryReader.ReadByte();
            }
        }

        public virtual void ReadFully(sbyte[] arr)
        {
            for (int i = 0; i < arr.Length; i++)
            {
                arr[i] = binaryReader.ReadSByte();
            }
        }
    }
}