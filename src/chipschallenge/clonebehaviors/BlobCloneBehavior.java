
package chipschallenge.clonebehaviors;

import chipschallenge.Block;
import chipschallenge.Move.Moves;
import chipschallenge.Utils;


public class BlobCloneBehavior extends CloneBehavior {

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
        int dir = Utils.r.nextInt(4);
        Block clone = null;
        outer:
        for (int i = 0; i < 2; i++) {
            switch (dir) {
                case 0:
                    if ((clone = cloneTo(original, Moves.UP)) != null) {
                        break outer;
                    }
                case 1:
                    if ((clone = cloneTo(original, Moves.DOWN)) != null) {
                        break outer;
                    }
                case 2:
                    if ((clone = cloneTo(original, Moves.LEFT)) != null) {
                        break outer;
                    }
                case 3:
                    if ((clone = cloneTo(original, Moves.RIGHT)) != null) {
                        break outer;
                    } else {
                        dir = 0;
                    }
            }
        }
        return clone;
    }
}
