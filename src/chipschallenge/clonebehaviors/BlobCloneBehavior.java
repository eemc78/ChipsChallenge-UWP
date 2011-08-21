
package chipschallenge.clonebehaviors;

import chipschallenge.Block;


public class BlobCloneBehavior implements CloneBehavior {

    private BlobCloneBehavior() {
    }
    private static BlobCloneBehavior mInstance = null;

    public static synchronized BlobCloneBehavior getInstance() {
        if (mInstance == null) {
            mInstance = new BlobCloneBehavior();
        }
        return mInstance;
    }

    public Block cloneIt(Block original) throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
