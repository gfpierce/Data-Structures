import java.util.LinkedList;
import java.util.Queue;

public class TwoProbeChainMap<Key, Value> implements Map<Key, Value> {

    private class Entry {
        public Key key;
        public Value value;
        public Entry (Key k, Value v) {
            key = k;
            value = v;
        }
    }

    private int N; // number of key-value pairs
    private int M; // hash table size

    private LinkedList<Entry>[] entries;

    public TwoProbeChainMap() {
        this(997);
    }
    public TwoProbeChainMap(int M) {
        this.N = 0;
        this.M = M;
        entries = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            entries[i] = new LinkedList<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff % M);
    }

    private int hash2(Key key) {
        return ((((key.hashCode() & 0x7fffffff) % M) * 31) % M);
    }

    /**
     * Gets the value paired with a key. If the key doesn't exist in map,
     * returns null.
     *
     * @param key Key to use.
     * @return Value associated with key.
     */
    public Value get(Key key) {
        for (Entry entry : entries[hash(key)]) {
            if (key.hashCode() == entry.key.hashCode()) {
                return entry.value;
            }
        }

        for (Entry entry : entries[hash2(key)]) {
            if (key.hashCode() == entry.key.hashCode()) {
                return entry.value;
            }
        }

        return null;
    }

    /**
     * Puts a key-value pair into the map.
     *
     * @param key Key to use.
     * @param val Value to associate.
     */
    public void put(Key key, Value val) {
        boolean added = false;

        for (Entry entry : entries[hash(key)]) {
            if (key.hashCode() == entry.key.hashCode()) {
                entry.value = val;
                added = true;
            }
        }

        for (Entry entry : entries[hash2(key)]) {
            if (key.hashCode() == entry.key.hashCode()) {
                entry.value = val;
                added = true;
            }
        }

        int size1 = entries[hash(key)].size();
        int size2 = entries[hash2(key)].size();

        if(!added) {
            if (size1 < size2) {
                entries[hash(key)].add(new Entry(key, val));
                N++;
            } else {
                entries[hash2(key)].add(new Entry(key, val));
                N++;
            }
        }
    }

    /**
     * Removes a key (and its associated value) from the map.
     *
     * @param key Key to use.
     */
    public void remove(Key key) {
        int size1 = entries[hash(key)].size();
        int size2 = entries[hash2(key)].size();

        if (contains(key)) {
            Entry target = null;
            for (Entry e : entries[hash(key)]) {
                if (e.key == key) {
                    target = e;
                }
            }
            if (target != null) {
                entries[hash(key)].remove(target);
                N--;
                return;
            }

            for (Entry e : entries[hash2(key)]) {
                if (e.key == key) {
                    target = e;
                }
            }
            if (target != null) {
                entries[hash2(key)].remove(target);
                N--;
                return;
            }
        }
    }

    /**
     * Checks if the map contains a particular key.
     *
     * @param key True if map contains key, false otherwise.
     * @return Result of check.
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * Returns true if there are no key-vale pairs stored in the map, and false
     * otherwise.
     *
     * @return True if map is empty, false otherwise.
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of key-value pairs in the map.
     *
     * @return Number of key-value pairs.
     */
    public int size() {
        return N;
    }

    /**
     * Returns an Iterable object containing all keys in the table. Keys not
     * guaranteed to be in any particular order.
     *
     * @return Iterable object containing all keys.
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            for (Entry e : entries[i]) {
                queue.add(e.key);
            }
        }
        return queue;
    }
}
