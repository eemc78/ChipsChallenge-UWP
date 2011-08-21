
package chipschallenge.clonebehaviors;

import chipschallenge.Block;


public class NullCloneBehavior extends CloneBehavior {

    private NullCloneBehavior() {
    }
    private static NullCloneBehavior mInstance = null;

    public static synchronized NullCloneBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new NullCloneBehavior();
        }
        return mInstance;
    }

    public Block cloneIt(Block original) throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
