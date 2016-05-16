//----------------------------------------------------------------------------------------
//  Copyright © 2007 - 2015 Tangible Software Solutions Inc.
//  This class can be used by anyone provided that the copyright notice remains intact.
//
//  This class provides the logic to simulate Java rectangular arrays, which are jagged
//  arrays with inner arrays of the same length. A size of -1 indicates unknown length.
//----------------------------------------------------------------------------------------

namespace ChipsChallenge.Shared
{
    internal static class RectangularArrays
    {
        internal static BlockContainer[][] ReturnRectangularBlockContainerArray(int size1, int size2)
        {
            BlockContainer[][] newArray;
            if (size1 > -1)
            {
                newArray = new BlockContainer[size1][];
                if (size2 > -1)
                {
                    for (int array1 = 0; array1 < size1; array1++)
                    {
                        newArray[array1] = new BlockContainer[size2];
                    }
                }
            }
            else
                newArray = null;

            return newArray;
        }
    }
}