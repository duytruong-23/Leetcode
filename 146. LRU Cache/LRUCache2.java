import java.util.LinkedHashMap;

public class LRUCache2 extends LinkedHashMap<Integer, Integer> {
    private final int capacity;

    public LRUCache2(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

}
