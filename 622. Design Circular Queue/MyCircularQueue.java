class MyCircularQueue {
    private static final int DEFAULT_SIZE = 10;

    private int[] data;
    private int frontIdx;
    private int rearIdx;
    private int size;

    public MyCircularQueue(int k) {
        if (k <= 0) {
            size = DEFAULT_SIZE;
        } else {
            size = k + 1;
        }

        data = new int[size];
        frontIdx = 0;
        rearIdx = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        data[rearIdx] = value;
        rearIdx = (rearIdx + 1) % size;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        frontIdx = (frontIdx + 1) % size;

        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }

        return data[frontIdx];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }

        return data[((rearIdx - 1) + size) % size];
    }

    public boolean isEmpty() {
        return frontIdx == rearIdx;
    }

    public boolean isFull() {
        return ((rearIdx + 1) % size) == frontIdx;
    }

    public static void main(String[] args) {
        System.out.println(-1 % 3);
    }

}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */