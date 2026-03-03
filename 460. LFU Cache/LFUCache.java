import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    int capacity;
    int minFrequency;
    Map<Integer, Node> keyValueMap = new HashMap<>();
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    Map<Integer, DLList> bucketMap = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!keyValueMap.containsKey(key)) {
            return -1;
        }

        Node node = keyValueMap.get(key);
        increaseNodeFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (keyValueMap.containsKey(key)) {
            Node node = keyValueMap.get(key);
            node.value = value;
            this.increaseNodeFrequency(node);
            return;
        }

        if (keyValueMap.size() >= capacity) {
            Node evictNode = bucketMap.get(minFrequency).removeTail();
            frequencyMap.remove(evictNode.key);
            keyValueMap.remove(evictNode.key);
        }

        minFrequency = 1;
        Node node = new Node(key, value);
        keyValueMap.put(key, node);
        frequencyMap.put(key, 1);
        bucketMap.computeIfAbsent(1, k -> new DLList()).addFirst(node);
    }

    private void increaseNodeFrequency(Node node) {
        int frequency = frequencyMap.get(node.key);
        DLList oldFrequencyList = bucketMap.get(frequency);
        oldFrequencyList.remove(node);
        bucketMap.computeIfAbsent(frequency + 1, k -> new DLList()).addFirst(node);
        frequencyMap.put(node.key, frequency + 1);
        if (bucketMap.get(minFrequency).isEmpty()) {
            minFrequency = frequency + 1;
        }
    }
}

class DLList {
    Node head = new Node();
    Node tail = new Node();

    public DLList() {
        head.next = tail;
        tail.previous = head;
    }

    public void addFirst(Node node) {
        node.next = head.next;
        head.next = node;
        node.previous = head;
    }

    public void remove(Node node) {
        Node prevNode = node.previous;
        Node nextNode = node.next;
        prevNode.next = node.next;
        nextNode.previous = prevNode;
    }

    public Node removeTail() {
        Node lastNode = tail.previous;
        tail.previous = lastNode.previous;
        lastNode.previous.next = tail;

        lastNode.next = null;
        lastNode.previous = null;
        return lastNode;
    }

    public boolean isEmpty() {
        return head.next == null || head.next == tail;
    }
}

class Node {
    int key;
    int value;
    Node previous;
    Node next;

    public Node() {
    }

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}