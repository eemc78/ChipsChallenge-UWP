
package chipschallenge;

import chipschallenge.Move.Moves;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


public class SlipList implements Iterable<BlockMove> {

    private final LinkedList<BlockMove> slipList = new LinkedList<BlockMove>();
    private final Map<Block, BlockMove> blocksInSlipList = new HashMap<Block, BlockMove>();
    Block last = null;

    public void add(Block b, Moves m) {
            BlockMove bm = new BlockMove(b, m);
            BlockMove already = blocksInSlipList.get(b);
            if(already != null) {
                blocksInSlipList.put(b, bm);
                int index = slipList.indexOf(already);
                slipList.remove(already);
                slipList.add(index, bm);
            } else {
            slipList.addLast(bm);
            blocksInSlipList.put(b, bm);}
    }

    public BlockMove get(int i) {
        return slipList.get(i);
    }

    public void remove(Block b) {
        BlockMove bm = blocksInSlipList.get(b);
        blocksInSlipList.remove(b);
        slipList.remove(bm);
    }

    public int size() {
        return slipList.size();
    }

    public void clear() {
        slipList.clear();
        blocksInSlipList.clear();
    }

    public Iterator<BlockMove> iterator() {
        return slipList.iterator();
    }

    public boolean contains(Block b) {
        return blocksInSlipList.containsKey(b);
    }
    
}

