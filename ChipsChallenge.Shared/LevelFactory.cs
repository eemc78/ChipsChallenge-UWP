namespace ChipsChallenge.Shared
{
    public abstract class LevelFactory
    {
        public abstract GameLevel GetLevel(int n);

        public abstract int LastLevelNumber {get;}

        protected internal abstract int GetLevelNumberByPassword(string pass);

        public GameLevel GetLevelByPassword(string password)
        {
            int n = GetLevelNumberByPassword(password);
            if (n == -1)
            {
                return null;
            }
            return GetLevel(n);
        }
    }
}