public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultList = new ListNode();
        ListNode tail = resultList;

        ListNode temp1 = l1;
        ListNode temp2 = l2;
        int remain = 0;
        while (temp1 != null || temp2 != null || remain > 0) {
            int operand1 = 0;
            int operand2 = 0;

            if (temp1 != null) {
                operand1 = temp1.val;
                temp1 = temp1.next;
            }

            if (temp2 != null) {
                operand2 = temp2.val;
                temp2 = temp2.next;
            }

            int sum = operand1 + operand2 + remain;
            int digit = sum % 10;
            remain = sum / 10;

            ListNode node = new ListNode(digit);
            tail.next = node;
            tail = node;
        }

        return resultList.next;
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