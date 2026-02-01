public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        ListNode result = new ListNode();
        ListNode p = result;
        int minNodeIndex = -1;

        while (true) {
            for (int i = 0; i < n; i++) {
                if (lists[i] != null && (minNodeIndex == -1 || (lists[i].val < lists[minNodeIndex].val))) {
                    minNodeIndex = i;
                }
            }

            if (minNodeIndex == -1) {
                break;
            }

            p.next = lists[minNodeIndex];
            lists[minNodeIndex] = lists[minNodeIndex].next;
            p = p.next;
            minNodeIndex = -1;
        }

        return result.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}