package lrucache;

import java.util.HashMap;
import java.util.Map;

import lrucache.entities.DoublyLinkedList;
import lrucache.entities.Node;

public class LRUCache<K, V> {
    private DoublyLinkedList<K, V> linkedList = new DoublyLinkedList<>();
    private int capacity;
    private Map<K, Node<K, V>> map = new HashMap<>();

    public LRUCache(int cap) {
        this.capacity = cap;
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        V value = map.get(key).value;
        linkedList.removeKey(map.get(key));
        linkedList.addKey(key, value);
        return value;
    }

    public void put(K key, V value) {
        if (!map.containsKey(key) && map.size() == capacity) {
            // I need to remove LRU
            linkedList.removeLRU();
        }
        // Remove the key if exist as we have to add at top
        if (map.containsKey(key)) {
            linkedList.removeKey(map.get(key));
        }
        linkedList.addKey(key, value);
    }

    public void displayCacheAndValue() {
        linkedList.displayCacheAndValue();
    }
}
