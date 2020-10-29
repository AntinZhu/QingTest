package com.qingqing.test.learn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujianxing on 2020/10/15.
 */
public class LRUTest {

    private Map<Integer, Node> nodeMap;
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);
    private int totalLen;

    public static void main(String[] args) {
        int[][] arr = new int[6][3];
        arr[0] = new int[]{1,1,1};
        arr[1] = new int[]{1,2,2};
        arr[2] = new int[]{1,3,2};
        arr[3] = new int[]{2,1};
        arr[4] = new int[]{1,4,4};
        arr[5] = new int[]{2,2};

        int[] result = new LRUTest().LRU(arr, 3);
        System.out.println(Arrays.toString(result));
    }

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        nodeMap = new HashMap<>();
        head.next = tail;
        tail.pre = head;
        totalLen = k;

        int resultCount = (int)Arrays.stream(operators).filter(e -> e[0] == 2).count();
        int[] result = new int[resultCount];

        int resultIdx = 0;
        for (int[] operator : operators) {
            if(operator[0] == 1){
                set(operator[1], operator[2]);
            }else{
                result[resultIdx++] = get(operator[1]);
            }
        }

        return result;
    }

    private void set(int key, int value){
        Node node = innerGet(key);
        if(node != null){
            node.value = value;
        }else{
            // 容量达标时，移出最后一个节点的Node
            if(nodeMap.size() == totalLen){
                Node removeNode = tail.pre;
                tail.pre.pre.next = tail;
                tail.pre = tail.pre.pre;

                nodeMap.remove(removeNode.key);
            }
            // 增加新的节点并插入链表头部
            Node newNode = new Node(key, value);
            nodeMap.put(key, newNode);
            moveToHead(newNode);
        }
    }

    private int get(int key){
        Node node = innerGet(key);
        if(node != null){
            return node.value;
        }

        return -1;
    }

    Node innerGet(int key){
        Node node = nodeMap.get(key);
        if(node != null){
            // 该节点从链表中断开
            node.pre.next = node.next;
            node.next.pre = node.pre;
            // 该节点移到头结点之后
            moveToHead(node);
            return node;
        }

        return null;
    }

    private void moveToHead(Node node){
        node.next = head.next;
        head.next.pre = node;
        node.pre = head;
        head.next = node;
    }

    static class Node{
        Node pre;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
