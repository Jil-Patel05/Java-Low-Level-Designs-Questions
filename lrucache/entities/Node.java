package lrucache.entities;

public class Node<K, V> {
    public Node<K, V> next;
    public Node<K, V> prev;
    public K key;
    public V value;

    public Node() {
    }

    public void setNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
