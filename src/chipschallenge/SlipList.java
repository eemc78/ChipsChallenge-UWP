
package chipschallenge;

import chipschallenge.Move.Moves;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


public class SlipList implements Iterable<BlockMove>, Iterator<BlockMove> {

    private LinkedList<BlockMove> slipList = new LinkedList<BlockMove>();
    private Map<Block, BlockMove> blocksInSlipList = new HashMap<Block, BlockMove>();
    private Iterator<BlockMove> iterator = slipList.iterator();
    Block last = null;

    public void add(Block b, Moves m) {
            BlockMove bm = new BlockMove(b, m);
            slipList.addLast(bm);
            blocksInSlipList.put(b, bm);
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

    public void remove() {
        iterator.remove();
        if(last != null) {
            BlockMove bm = blocksInSlipList.get(last);
            blocksInSlipList.remove(last);
            last = null;
        } else {
            throw new IllegalStateException();
        }
    }

    public Iterator<BlockMove> iterator() {
        return this;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public BlockMove next() {
        BlockMove bm = iterator.next();
        last = bm.block;
        return bm;
    }

    public boolean contains(Block b) {
        return blocksInSlipList.containsKey(b);
    }

}

