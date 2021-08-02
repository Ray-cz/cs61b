import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
            left = right = null;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }
    private V get(Node p, K key) {
        if (p == null) {
            return null;
        }
        if (key.equals(p.key)) {
            return p.value;
        } else if (key.compareTo(p.key) < 0) {
            return get(p.left, key);
        } else {
            return get(p.right, key);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        size += 1;
        root = put(root, key, value);
    }

    private Node put(Node p, K key, V value) {
        if (p == null) {
            return new Node(key, value);
        }
        if (key.equals(p.key)) {
            p.value = value;
        } else if (key.compareTo(p.key) < 0) {
            p.left = put(p.left, key, value);
        } else {
            p.right = put(p.right, key, value);
        }
        return p;
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator(){
        throw new UnsupportedOperationException();
    }


}
