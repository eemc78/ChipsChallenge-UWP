using System;

namespace ChipsChallenge.Shared
{
    public class CloneNotSupportedException : Exception
    {
          public CloneNotSupportedException()
          {
          }

          public CloneNotSupportedException(String message) : base(message)
          {
          }
    }
}