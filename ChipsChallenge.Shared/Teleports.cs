namespace ChipsChallenge.Shared
{
    using System.Collections.Generic;

    public class Teleports
    {
        private static readonly ICollection<Point> TeleportCollection = new List<Point>();

        public static void Clear()
        {
            TeleportCollection.Clear();
        }

        public static void AddTeleport(int x, int y)
        {
            TeleportCollection.Add(new Point(x, y));
        }

        public static Point Next(Point o)
        {
            int width = Game.Instance.Level.Width;
            int height = Game.Instance.Level.Height;
            int minDistance = -1;
            Point minPoint = o;
            foreach (Point p in TeleportCollection)
            {
                if (p.X == o.X && p.Y == o.Y)
                {
                    continue; // Same place
                }

                int dx = o.X - p.X;
                int dy = (o.Y - p.Y) * width;
                int distance = dx + dy;

                if (distance < 0)
                {
                    distance += width * height;
                }

                if (minDistance == -1 || distance < minDistance)
                {
                    minDistance = distance;
                    minPoint = p;
                }
            }

            return minPoint;
        }
    }
}