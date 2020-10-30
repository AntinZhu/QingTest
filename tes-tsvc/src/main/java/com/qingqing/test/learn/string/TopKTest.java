package com.qingqing.test.learn.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2020/10/30.
 */
public class TopKTest {

    public static void main(String[] args) {
        String[] arr = new String[]{"1", "1", "2", "3"};

        String[][] result  = new TopKTest().topKstrings(arr, 2);
        String[] printArr = new String[result.length];
        int printIdx = 0;
        for (String[] strings : result) {
            printArr[printIdx++] = Arrays.toString(strings);
        }
        System.out.print(Arrays.toString(printArr));
    }

    static class StringCountNode{
        private String value;
        private int count;

        public StringCountNode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    /**
     * return topK string
     * @param strings string字符串一维数组 strings
     * @param k int整型 the k
     * @return string字符串二维数组
     */
    public String[][] topKstrings (String[] strings, int k) {
        // write code here
        Map<String, StringCountNode> stringCountMapping = new HashMap<>();
        for (String string : strings) {
            StringCountNode countNode = stringCountMapping.get(string);
            if(countNode == null){
                countNode = new StringCountNode(string);
                stringCountMapping.put(string, countNode);
            }
            countNode.setCount(countNode.getCount()  + 1);
        }

        List<StringCountNode> nodeList = new ArrayList<>(stringCountMapping.values());
        Collections.sort(nodeList, new Comparator<StringCountNode>() {
            @Override
            public int compare(StringCountNode o1, StringCountNode o2) {
                if(o1.getCount() == o2.getCount()){
                    return o1.getValue().compareTo(o2.getValue());
                }else{
                    return o2.getCount() - o1.getCount();
                }
            }
        });

        String[][] resultArr = new String[k][2];
        int idx = 0;
        for(StringCountNode node : nodeList){
            resultArr[idx++] = new String[]{node.getValue(), String.valueOf(node.getCount())};
            if(idx >= k){
                break;
            }
        }

        return resultArr;
    }
}
