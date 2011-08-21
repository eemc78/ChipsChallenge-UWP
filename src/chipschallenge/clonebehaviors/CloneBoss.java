package chipschallenge.clonebehaviors;

import chipschallenge.Block;
import chipschallenge.Creatures;

public class CloneBoss extends CloneBehavior {

    private CloneBoss() {
    }
    private static CloneBoss mInstance = null;

    public static synchronized CloneBoss getInstance() {
        if (mInstance == null) {
            mInstance = new CloneBoss();
        }
        return mInstance;
    }

    public Block cloneIt(Block original) throws CloneNotSupportedException {
        System.out.println("Clone Irene Boss");
        Block boss = Creatures.getBoss();
        if (boss == null) {
            throw new CloneNotSupportedException("No controller");
        } else {
            System.out.println(boss + " " + boss.getFacing());
            return cloneTo(original, boss.getFacing());
        }
    }
}
