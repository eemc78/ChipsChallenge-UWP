package chipschallenge;

import chipschallenge.Block.Type;
import chipschallenge.Move.Moves;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author patrik
 */
public abstract class BlockFactory {
    private Map<Type, Map<Moves, Block>> loadedBlocks = new WeakHashMap<Type, Map<Moves, Block>>();
    
    public final Block get(Type type) {
        return get(type, Moves.DOWN);
    }

    public final Block get(Type type, Moves direction) {
        Map<Moves, Block> movesBlocks = loadedBlocks.get(type);
        if(movesBlocks == null) {
            movesBlocks = new WeakHashMap<Moves, Block>();
            loadedBlocks.put(type, movesBlocks);
        }
        Block b;
        try {
            b = movesBlocks.get(direction).clone();
        } catch (Exception ex) { // b == null or clone not supported
            b = createBlock(type, direction);
            movesBlocks.put(direction, b);
        }
        return b;
    }

    protected abstract Block createBlock(Type type, Moves direction);
}
