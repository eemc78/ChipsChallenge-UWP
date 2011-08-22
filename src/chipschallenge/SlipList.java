package chipschallenge;

import chipschallenge.Move.Moves;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlipList {

    private Map<Block, Moves> map = new ConcurrentHashMap<Block, Moves>();
    private List<Block> order = new ArrayList<Block>();

    public void put(Block b, Moves m) {
        if (map.put(b, m) == null) {
            order.add(b);
        }
    }

    public void remove(Block b) {
        order.remove(b);
        map.remove(b);
    }

    public BlockMove get(int i) {
        Block b = order.get(i);
        return new BlockMove(b, map.get(b));
    }

    public int size() {
        return order.size();
    }

    public void clear() {
        map.clear();
        order.clear();
    }
}

