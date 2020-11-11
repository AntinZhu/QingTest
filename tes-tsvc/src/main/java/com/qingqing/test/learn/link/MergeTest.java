package com.qingqing.test.learn.link;

import com.qingqing.test.learn.link.LinkUtils.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by zhujianxing on 2020/11/11.
 */
public class MergeTest {

    public static void main(String[] args) {
        MergeTest test = new MergeTest();
        LinkUtils.print(test.merge(LinkUtils.build(new int[]{1, 2, 4,5,6, 10}), LinkUtils.build(new int[]{2, 2, 3,4,5,8,9})));
        LinkUtils.print(test.sort(LinkUtils.build(new int[]{2, 3, 5, 6, 9 , 0 , -1, 4, 8})));

        LinkUtils.print(test.addDot(LinkUtils.build(new int[]{9,9,9,9}), LinkUtils.build(new int[]{9, 9, 9,9,9,9,9})));

        LinkUtils.print(test.addDot(LinkUtils.build(new int[]{9,9,9,9, -1, 9, 9}), LinkUtils.build(new int[]{9, 9, 9,9,9,9,9, -1, 0, 1, 2, 3})));
    }

    /*
    合并K个链表，使用归并
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        return mergeInto(lists, 0, lists.length - 1);
    }

    private ListNode mergeInto(ListNode[] lists, int start, int end){
        if(start == end){
            return lists[start];
        }

        int mid = (start +end ) / 2;
        ListNode left = mergeInto(lists, start, mid);
        ListNode right = mergeInto(lists, mid + 1, end);

        return merge(left, right);
    }

    /**
     * 合并K和链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(-1);
        ListNode link = result;
        ListNode minNode = null;
        do{
            minNode = findMinNode(lists);
            if(minNode != null){
                link.next = minNode;
                link = link.next;
            }
        }while (minNode != null);

        return result;
    }

    private ListNode findMinNode(ListNode[] lists){
        ListNode minNode = null;
        int minNodeIdx = 0;
        for(int i = 0; i < lists.length; i++){
            ListNode node = lists[i];
            if(node != null){
                if(minNode == null){
                    minNode = node;
                    minNodeIdx = i;
                }else if(node.val < minNode.val){
                    minNode = node;
                    minNodeIdx = i;
                }
            }
        }

        if(minNode != null){
            lists[minNodeIdx] = minNode.next;
        }

        return minNode;
    }

    /**
     * 链表相交点
     * @return 返回的相交节点，没有返回null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }

        int aLen = 0;
        int bLen = 0;

        ListNode tailB = headB;
        while (tailB.next != null){
            bLen++;
            tailB = tailB.next;
        }

        ListNode pre = null;
        ListNode cur = headA;
        ListNode tmp = null;
        while (cur != null){
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;

            if(cur != null){
                aLen++;
            }
        }

        ListNode reverseA = pre;
        if(reverseA != tailB){
            return null;
        }

        int newBLen = 0;
        ListNode tailNewB = headB;
        while (tailNewB.next != null){
            newBLen++;
            tailNewB = tailNewB.next;
        }
        reverse(reverseA);

        int pos = bLen - (aLen + bLen - newBLen) / 2;

        ListNode result = headB;
        while (pos-- > 0){
            result = result.next;
        }

        return result;
    }



    /*
    找出开始入环的第一个节点
     */
    public ListNode detectCycle(ListNode head) {
        Map<ListNode, Integer> accessIdxMap = new HashMap<>();
        int idx = 0;
        while (head != null){
            Integer existIdx = accessIdxMap.get(head);
            if(existIdx != null){
                return head;
            }else {
                accessIdxMap.put(head, idx++);
            }

            head = head.next;
        }

        return null;
    }

    /*
  找出开始入环的第一个节点,空间O(1)
   */
    public ListNode detectCycle2(ListNode head) {
        if(head == null || head.next == head){
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){ // 快指针追上慢指针
                ListNode pot = head;
                while (pot != slow){
                    pot = pot.next;
                    slow = slow.next;
                }

                return pot;
            }
        }

        return null;
    }

    /*
    链表相加,支持小数,小数点以-1代替
     */
    public ListNode addDot(ListNode l1, ListNode l2){
        // 查询小数点前面一个节点
        ListNode l1DotPre = findDotPre(l1);
        ListNode l2DotPre = findDotPre(l2);

        // 保存小数相加结果
        ListNode dotResult;
        int dotExt = 0;
        if(l1DotPre != null && l2DotPre != null){
            dotResult = dotAdd(l1DotPre.next, l2DotPre.next);
            if(dotResult.val == -1){
                dotExt = 1;
            }else {
                dotResult.val = -1;
            }
        } else if(l1DotPre == null && l2DotPre == null){
            dotResult = null;
        }else{
            dotResult = l1DotPre == null? l2DotPre.next : l1DotPre.next;
        }

        // 按小数点拆分链表
        if(l1DotPre != null){
            l1DotPre.next = null;
        }
        if(l2DotPre != null){
            l2DotPre.next = null;
        }

        // 除去小数点相加
        ListNode noDotResult =  add(l1, l2, dotExt);

        return link(noDotResult, dotResult);
    }

    /*
    第一个节点为-1,所以不需要考虑第一个节点相加进位的问题
     */
    private ListNode dotAdd(ListNode l1, ListNode l2){
        Stack<Integer> stack = new Stack<>();
        while (l1 != null && l2 != null){
            stack.push(l1.val);
            stack.push(l2.val);

            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode result = new ListNode(-1);
        result.next = l1 == null? l2 : l1;

        int ext = 0;
        while (!stack.isEmpty()){
            int addValue = stack.pop() + stack.pop() + ext;
            insertHead(result, new ListNode(addValue % 10));
            ext = addValue / 10;
        }

        return result.next;
    }

    private ListNode link(ListNode l1, ListNode l2){
        if(l1 == null){
            return  l2;
        }

        if(l2 == null){
            return l1;
        }

        ListNode l1Tail = l1;
        while (l1Tail.next != null){
            l1Tail = l1Tail.next;
        }

        l1Tail.next = l2;

        return l1;
    }

    private ListNode findDotPre(ListNode head){
        if(head == null){
            return head;
        }

        while (head.next != null){
            if(head.next.val == -1){
                return head;
            }
            head = head.next;
        }

        return null;
    }

    /*
    链表相加
     */
    public ListNode add(ListNode l1, ListNode l2){
        return add(l1, l2, 0);
    }

    public ListNode add(ListNode l1, ListNode l2, int ext){
        l1 = reverse(l1);
        l2 = reverse(l2);

        return addAfterReverse(l1, l2, ext);
    }

    public ListNode addAfterReverse(ListNode l1, ListNode l2, int ext){
        ListNode result = new ListNode(-1);
        while (l1 != null && l2 != null){
            int addValue = l1.val + l2.val + ext;
            insertHead(result, new ListNode(addValue % 10));
            ext = addValue / 10;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode next = l1 == null? l2 :l1;
        while (next != null){
            int nodeValue = next.val;
            if(ext > 0){
                int addValue = nodeValue + ext;
                nodeValue = addValue % 10;
                ext = addValue / 10;
            }
            insertHead(result, new ListNode(nodeValue));
            next = next.next;
        }

        if(ext > 0){
            insertHead(result, new ListNode(ext));
        }

        return result.next;
    }

    private void insertHead(ListNode head, ListNode node){
        node.next = head.next;
        head.next = node;
    }

    /*
    反转链表
     */
    public ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            head = cur.next;
            cur.next = pre;
            pre = cur;
            cur = head;
        }

        return pre;
    }

    /*
    排序：归并
     */
    public ListNode sort(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode half = half(head);
        ListNode right = sort(half.next);
        half.next = null;
        ListNode left = sort(head);

        return merge(left, right);
    }

    private ListNode half(ListNode head){
        ListNode half = head;
        ListNode end = head.next;
        while (end != null && end.next != null){
            half = half.next;
            end = end.next.next;
        }

        return half;
    }

    /*
    合并两个已排序的链表
     */
    public ListNode merge(ListNode l1, ListNode l2){
        ListNode result = new ListNode(-1);
        ListNode link = result;
        while (l1 != null && l2 != null){
            ListNode next;
            if(l1.val > l2.val){
                next = l2;
                l2 = l2.next;
            }else{
                next = l1;
                l1 = l1.next;
            }

            link.next = next;
            link = link.next;
        }

        link.next = l1 == null? l2 : l1;

        return result.next;
    }
}
