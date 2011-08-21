package chipschallenge;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Creatures {

    private static List<Block> creatures = new CopyOnWriteArrayList<Block>();
    private static int creatureTicks = 0;
    private static boolean blobMove = false;
    private static Block boss = null;

    public static List<Block> getCreatures() {
        return creatures;
    }

    public static Block getBoss() {
        return boss;
    }

    public static Block getController(Block b) {
        int index = creatures.indexOf(b);
        if (index == 0) {
            return null;
        } else {
            return creatures.get(creatures.indexOf(b) - 1);
        }
    }

    public static void clear() {
        creatures.clear();
    }

    public static void addCreature(Block b) {
        creatures.add(b);
        //boss = b;
    }

    public static void setBoss(Block b) {
        boss = b;
    }

    public static void removeCreature(Block b) {
        creatures.remove(b);
    }

    public static void tick() throws BlockContainerFullException {
        creatureTicks = (creatureTicks + 1) % Game.SPEED_FRAC;
        if (creatureTicks == 0) {
            blobMove = !blobMove;
            for (Block b : creatures) {
                if (!b.isTrapped() && (blobMove || !(b.isA(Block.Type.BLOB) || b.isA(Block.Type.TEETH)))) {
                    b.tick();
                }
            }
        }
    }
}
