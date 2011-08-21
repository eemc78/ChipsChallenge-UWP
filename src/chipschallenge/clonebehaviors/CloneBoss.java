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
        Block boss = Creatures.getBoss();
        if (boss == null) {
            throw new CloneNotSupportedException("No boss");
        } else {
            return cloneTo(original, boss.getFacing());
        }
    }
}
