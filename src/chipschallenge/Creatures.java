package chipschallenge;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class Creatures {

    private static Collection<Block> creatures = new CopyOnWriteArrayList<Block>();
    private static int creatureTicks = 0;
    private static boolean blobMove = false;

    public static Collection<Block> getCreatures() {
        return creatures;
    }

    public static void clear() {
        creatures.clear();
    }

    public static void addCreature(Block b) {
        creatures.add(b);
    }

    public static void removeCreature(Block b) {
        creatures.remove(b);
    }

    public static void tick() throws BlockContainerFullException {
        creatureTicks = (creatureTicks + 1) % Game.SPEED_FRAC;
        if (creatureTicks == 0) {
            blobMove = !blobMove;
            for (Block b : creatures) {
                if (blobMove || !(b.isA(Block.Type.BLOB) || b.isA(Block.Type.TEETH))) {
                        b.tick();
                }
            }
        }
    }
}
