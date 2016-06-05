namespace ChipsChallenge.Shared
{
    public abstract class LevelFactory
    {
        public abstract int LastLevelNumber { get; }

        public GameLevel GetLevelByPassword(string password)
        {
            int n = GetLevelNumberByPassword(password);
            if (n == -1)
            {
                return null;
            }

            return GetLevel(n);
        }

        public abstract GameLevel GetLevel(int n);

        protected internal abstract int GetLevelNumberByPassword(string pass);
    }
}