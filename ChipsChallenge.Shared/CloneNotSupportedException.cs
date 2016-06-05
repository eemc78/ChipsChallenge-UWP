namespace ChipsChallenge.Shared
{
    using System;

    public class CloneNotSupportedException : Exception
    {
          public CloneNotSupportedException()
          {
          }

          public CloneNotSupportedException(string message) : base(message)
          {
          }
    }
}