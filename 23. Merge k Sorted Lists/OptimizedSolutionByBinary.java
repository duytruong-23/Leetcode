public class OptimizedSolutionByBinary {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int left, int right) {
        if (left >= right) {
            return lists[left];
        }

        if (left + 1 == right) {
            return merge2List(lists[left], lists[right]);
        }

        int mid = left - (left - right) / 2;
        ListNode mergedLeft = mergeKListsHelper(lists, left, mid);
        ListNode mergedRight = mergeKListsHelper(lists, mid + 1, right);

        return merge2List(mergedLeft, mergedRight);
    }

    private ListNode merge2List(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode p = result;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        p.next = l1 != null ? l1 : l2;

        return result.next;
    }
}
