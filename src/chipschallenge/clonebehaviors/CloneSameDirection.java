
package chipschallenge.clonebehaviors;

import chipschallenge.Block;


public class CloneSameDirection extends CloneBehavior {

    private CloneSameDirection() {
    }
    private static CloneSameDirection mInstance = null;

    public static synchronized CloneSameDirection getInstance() {
        if (mInstance == null) {
            mInstance = new CloneSameDirection();
        }
        return mInstance;
    }

    public Block cloneIt(Block original) throws CloneNotSupportedException {
        return cloneTo(original, original.getFacing());
    }
}
