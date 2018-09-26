import java.util.LinkedList;
import java.util.Queue;

public class LinearProbingMap<Key, Value> implements Map<Key, Value> {
    private class Entry {
        public Key key;
        public Value value;
        public Entry (Key k, Value v) {
            key = k;
            value = v;
        }
    }

    private int N;
    private int M;

    private LinkedList<Entry>[] entries;

    public LinearProbingMap() {
        this(997);
    }

    public LinearProbingMap(int M) {
        this.N = 0;
        this.M = M;
        entries = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            entries[i] = new LinkedList<>();
        }
    }

    private int hash(Key key, int i) {
        return ((key.hashCode() & 0x7fffffff) + i) % M;
    }

    /**
     * Puts a key-value pair into the map.
     *
     * @param key Key to use.
     * @param val Value to associate.
     */
    public void put(Key key, Value val) {
        boolean added = false;
        int i = 0;

        for (Entry entry : entries[hash(key, i)]) {
            if (key.hashCode() == entry.key.hashCode()) {
                entry.value = val;
                added = true;
                i++;
            }
        }

        if (!added) {
            entries[hash(key, i)].add(new Entry(key, val));
            N++;
        }
    }

    /**
     * Gets the value paired with a key. If the key doesn't exist in map,
     * returns null.
     *
     * @param key Key to use.
     * @return Value associated with key.
     */
    public Value get(Key key) {
        int i = 0;
        for (Entry entry : entries[hash(key, i)]) {
            if (key.hashCode() == entry.key.hashCode()) {
                return entry.value;
            }
            i++;
        }

        return null;
    }

    /**
     * Removes a key (and its associated value) from the map.
     *
     * @param key Key to use.
     */
    public void remove(Key key) {
        if (contains(key)) {
            int i = 0;
            Entry target = null;
            for (Entry e : entries[hash(key, i)]) {
                if (e.key == key) {
                    target = e;
                    i++;
                }
            }
            entries[hash(key, i)].remove(target);
            N--;
        }
    }

    /**
     * Checks if the map contains a particular key.
     *
     * @param key True if map contains key, false otherwise.
     * @return Result of check.
     */
    public boolean contains(Key k) {
        return get(k) != null;
    }

    /**
     * Returns true if there are no key-vale pairs stored in the map, and false
     * otherwise.
     *
     * @return True if map is empty, false otherwise.
     */
    public boolean isEmpty() {
        return N==0;
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
