package interview_02;

import org.junit.Test;

// 将两个有序链表合并为一个有序链表
class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return next != null ? val + "->" + next.toString() : val + "";
    }

    public static ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(-1), curr = dummy;
        for (int i : vals) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        return dummy.next;
    }
}

public class MergeListNode {

    public ListNode merge(ListNode head1, ListNode head2) {
        
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }

        curr.next = head1 == null ? head2 : head1;
        return dummy.next;
    }

    @Test
    public void testCase() {
        ListNode head1 = ListNode.buildList(3, 4, 7, 8);
        ListNode head2 = ListNode.buildList(1, 5, 7);
        System.out.println(merge(head1, head2));
    }
}
