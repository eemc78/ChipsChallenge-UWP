
package chipschallenge;

import chipschallenge.Move.Moves;


public class BlockMove {

    public final Block block;
    public final Moves move;

    public BlockMove(Block b, Moves m) {
        block = b;
        move = m;
    }

    @Override
    public BlockMove clone() {
        return new BlockMove(block, move);
    }

}
