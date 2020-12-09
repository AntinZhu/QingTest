package com.qingqing.test.learn.link;

/**
 * Created by zhujianxing on 2020/11/10.
 */
public class LinkUtils {

    public static class ListNode {
        public int val;
        public ListNode next;
        ListNode(int val) { this.val = val; }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void print(ListNode head){
        while (head != null){
            System.out.print(head.val);
            if(head.next != null){
                System.out.print("->");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode build(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }

        ListNode result = new ListNode(-1);
        ListNode link = result;
        for (int i : arr) {
            link.next = new ListNode(i);
            link = link.next;
        }

        return result.next;
    }
}
