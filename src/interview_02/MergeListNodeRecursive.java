package interview_02;

import org.junit.Test;

// 将两个有序链表合并为一个有序链表，递归
public class MergeListNodeRecursive {

    public ListNode merge(ListNode head1, ListNode head2) {

        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        if (head1.val < head2.val) {
            curr.next = head1;
            curr = curr.next;
            curr.next = merge(head1.next, head2);
        } else {
            curr.next = head2;
            curr = curr.next;
            curr.next = merge(head1, head2.next);
        }
        return dummy.next;
    }

    @Test
    public void testCase() {
        ListNode head1 = ListNode.buildList(3, 4, 7, 8);
        ListNode head2 = ListNode.buildList(1, 5, 7);
        System.out.println(merge(head1, head2));
    }
}
