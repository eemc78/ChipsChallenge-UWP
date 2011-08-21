package chipschallenge;

import chipschallenge.Move.Moves;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlipList implements Iterable<BlockMove>, Iterator<BlockMove> {

    private Map<Block,Moves> map = new ConcurrentHashMap<Block,Moves>();
    private Iterator<Block> iterator = null;
    private Block current = null;

    public void put(Block b, Moves m) {
        map.put(b,m);
    }

    public void remove(Block b) {
        map.remove(b);
    }

    public void clear() {
        map.clear();
        iterator = null;
        current = null;
    }

    public Iterator<BlockMove> iterator() {
        iterator = map.keySet().iterator();
        return this;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public BlockMove next() {
        current = iterator.next();
        return new BlockMove(current, map.get(current));
    }

    public void remove() {
        map.remove(current);
    }
}

