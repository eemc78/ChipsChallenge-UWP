package chipschallenge;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author patrik
 */
public class Teleports {

    private static Collection<Point> teleports = new ArrayList<Point>();
    private static int creatureTicks = 0;
    private static boolean blobMove = false;

    public static void clear() {
        teleports.clear();
    }

    public static void addTeleport(int x, int y) {
        teleports.add(new Point(x,y));
    }

    public static Point next(Point o) {
        int width = Game.getInstance().getLevel().getWidth();
        int height = Game.getInstance().getLevel().getHeight();
        int minDistance = -1;
        Point minPoint = o;
        for (Point p : teleports) {
            if (p.x == o.x && p.y == o.y) {
                continue; // Same place
            }
            int dx = o.x - p.x;
            int dy = (o.y - p.y) * width;
            int distance = dx + dy;
            if (distance < 0) {
                distance += width * height;
            }
            if (minDistance == -1) {
                minDistance = distance;
                minPoint = p;
            } else {
                if (distance < minDistance) {
                    minDistance = distance;
                    minPoint = p;
                }
            }
        }
        return minPoint;
    }

}
