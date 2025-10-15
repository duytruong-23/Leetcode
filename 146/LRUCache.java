import java.util.HashMap;
import java.util.Map;

class LRUCache {
    Integer capacity;
    // Double-Linked List
    Node head;
    Node tail;
    // Map for interacting
    Map<Integer, Node> map;

    class Node {
        private Integer key;
        private Integer value;
        private Node previous;
        private Node next;

        public Node(Integer key, Integer value, Node previous, Node next) {
            this.key = key;
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1, null, null);
        this.tail = new Node(-1, -1, head, null);
        head.next = tail;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        markAsRecentlyUsed(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            markAsRecentlyUsed(node);
            map.put(key, node);
        } else {
            if (map.size() == capacity) {
                Node lruNode = tail.previous;
                remove(lruNode);
                map.remove(lruNode.key);
            }
            Node newNode = new Node(key, value, null, null);
            add(newNode);
            map.put(key, newNode);
        }

    }

    private void markAsRecentlyUsed(Node node) {
        remove(node);
        add(node);
    }

    private void add(Node node) {
        Node nextNode = head.next;
        node.next = nextNode;
        nextNode.previous = node;
        head.next = node;
        node.previous = head;
    }

    private void remove(Node node) {
        Node prevNode = node.previous;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.previous = prevNode;
        node.previous = null;
        node.next = null;
    }

    public void printCache() {
        Node current = tail.previous;
        while (current != head) {
            System.out.print(current.value + " ");
            current = current.previous;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lruCache.get(1)); // return 1
        lruCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lruCache.printCache(); // prints 3 1
        System.out.println(lruCache.get(2)); // returns -1 (not found)
        lruCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lruCache.get(1)); // return -1 (not found)
        System.out.println(lruCache.get(3)); // return 3
        System.out.println(lruCache.get(4)); // return 4
    }
}
