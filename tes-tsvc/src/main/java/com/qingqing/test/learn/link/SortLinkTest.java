package com.qingqing.test.learn.link;

import com.qingqing.test.learn.link.LinkUtils.ListNode;

import java.util.Arrays;

/**
 * Created by zhujianxing on 2020/11/10.
 */
public class SortLinkTest {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 1,3};
        ListNode head = null;
        ListNode link = null;
        for (int i : arr) {
            if(head == null){
                head = new ListNode(i);
                link = head;
            }else{
                link.next = new ListNode(i);
                link = link.next;
            }
        }

        ListNode result = new SortLinkTest().sort2(head);
        LinkUtils.print(result);
    }

    public ListNode sortList(ListNode head) {
        return sort(head)[0];
    }

    // 归并
    private ListNode sort2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode halfNode = halfNode(head);

        ListNode right = sort2(halfNode.next);
        halfNode.next = null;
        ListNode left = sort2(head);

        ListNode result = new ListNode(-1);
        ListNode link = result;
        while (left != null && right != null){
            if(left.val < right.val){
                link.next = left;
                left = left.next;
            }else{
                link.next = right;
                right = right.next;
            }
            link = link.next;
        }

        link.next = left == null? right :left;

        return result.next;
    }

    private ListNode halfNode(ListNode head){
        ListNode half = head;
        ListNode link = head.next;
        while (link != null && link.next != null){
            half = half.next;
            link = link.next.next;
        }

        return half;
    }


    // 快速排序
    private ListNode[] sort(ListNode head){
        if(head.next == null){
            return new ListNode[]{head, head};
        }

        int headVal = head.val;
        ListNode leftHead = null;
        ListNode leftNode = null;
        ListNode rightHead = null;
        ListNode rightNode = null;

        ListNode link = head.next;
        while (link != null){
            if(link.val > headVal){
                if(rightHead == null){
                    rightHead = link;
                    rightNode = rightHead;
                }else{
                    rightNode.next = link;
                    rightNode = rightNode.next;
                }
            }else{
                if(leftHead == null){
                    leftHead = link;
                    leftNode = leftHead;
                }else{
                    leftNode.next = link;
                    leftNode = leftNode.next;
                }
            }

            link = link.next;
        }
        head.next = null;
        if(leftNode != null){
            leftNode.next = null;
        }
        if(rightNode != null){
            rightNode.next = null;
        }

        ListNode resultHead;
        ListNode resultTail;
        if(leftHead != null){
            ListNode[] leftResult = sort(leftHead);
            resultHead = leftResult[0];
            leftResult[1].next = head;
            resultTail = head;
        }else{
            resultHead = head;
            resultTail = head;
        }

        head.next = null;
        if(rightHead != null) {
            ListNode[] rightResult = sort(rightHead);
            resultTail.next = rightResult[0];
            resultTail = rightResult[1];
        }

        return new ListNode[]{resultHead, resultTail};
    }

}
