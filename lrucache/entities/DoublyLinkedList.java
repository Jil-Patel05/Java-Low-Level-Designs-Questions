package lrucache.entities;

public class DoublyLinkedList<K, V> {
    private Node<K, V> head;
    private Node<K, V> tail;

    public DoublyLinkedList() {
        head = new Node<>();
        tail = new Node<>();
    }

    private void handleNodeRemoval(Node<K, V> nodeToRemNode) {
        Node<K, V> prevNode = nodeToRemNode.prev;
        nodeToRemNode.next.prev = prevNode;
        prevNode.next = nodeToRemNode.next;
        nodeToRemNode = null;
    }

    public void removeLRU() {
        Node<K, V> nodeToRemNode = head.next;
        this.handleNodeRemoval(nodeToRemNode);
    }

    public void removeKey(Node<K, V> nodeToRemNode) {
        this.handleNodeRemoval(nodeToRemNode);
    }

    public void addKey(K key, V value) {
        Node<K, V> node = new Node<>();
        node.setNode(key, value);
        Node<K, V> prevNode = tail.prev;
        node.prev = prevNode;
        node.next = tail;
        prevNode.next = node;
        tail.prev = node;
    }

    public void displayCacheAndValue() {
        Node<K, V> temp = head.next;

        while (temp != tail) {
            System.out.println(temp.key + " " + temp.value);
            temp = temp.next;
        }
    }

}
