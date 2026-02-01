import java.util.PriorityQueue;

public class OptimizedSolutionWithDS {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        ListNode result = new ListNode();
        ListNode p = result;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> {
            if (a == null) {
                return 1;
            }

            if (b == null) {
                return -1;
            }

            return Integer.compare(a.val, b.val);
        });

        for (int i = 0; i < n; i++) {
            if (lists[i] != null) {
                queue.add(lists[i]);
            }
        }

        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            p = p.next;
            node = node.next;
            if (node != null) {
                queue.add(node);
            }
        }

        return result.next;
    }
}
