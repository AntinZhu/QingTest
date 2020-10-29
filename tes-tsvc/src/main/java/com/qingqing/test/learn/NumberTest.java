package com.qingqing.test.learn;

import com.google.common.collect.Maps;
import com.qingqing.common.util.StringUtils;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2020/10/14.
 */
public class NumberTest {

    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 10, 6 , 9 ,8 ,12, 11, 4, 4, 6};

//        List<List<Pair<Integer, Integer>>> resultList = find4(arr, 13);
//        resultList.forEach(e -> {
//            System.out.println("-----------------------------------");
//            e.forEach(ee -> {
//                System.out.print(ee.getKey() +"[" + ee.getValue() + "]" + " ");
//            });
//            System.out.println();
//        });

        find5(arr, 12);
    }

    private static List<List<Pair<Integer, Integer>>> find(int[] arr, int total){
        Map<Integer, Integer> mapping = Maps.newHashMapWithExpectedSize(arr.length);
        for(int i = 0; i < arr.length; i++){
            mapping.put(arr[i], i);
        }

        List<List<Pair<Integer, Integer>>> resultList = new ArrayList<>(arr.length/2 + 1);
        for(int i = 0; i < arr.length; i++){
            int thisNum = arr[i];
            int rest = total - thisNum;
            Integer restIdx = mapping.get(rest);
            if(restIdx != null && restIdx != i){
                resultList.add(Arrays.asList(new Pair<>(thisNum, i), new Pair<>(rest, restIdx)));
                mapping.remove(thisNum );
            }
        }

        return resultList;
    }

    private static List<List<Pair<Integer, Integer>>> find2(int[] arr, int total){
        Map<Integer, Integer> mapping = Maps.newHashMapWithExpectedSize(arr.length);
        List<List<Pair<Integer, Integer>>> resultList = new ArrayList<>(arr.length/2 + 1);
        for(int i = 0; i < arr.length; i++){
            int thisNum = arr[i];
            int rest = total - thisNum;
            Integer restIdx = mapping.get(rest);
            if(restIdx != null && restIdx != i){
                resultList.add(Arrays.asList(new Pair<>(thisNum, i), new Pair<>(rest, restIdx)));
            }else{
                mapping.put(thisNum, i);
            }
        }

        return resultList;
    }

    private static List<List<Pair<Integer, Integer>>> find3(int[] arr, int total){
        Arrays.sort(arr);

        List<List<Pair<Integer, Integer>>> resultList = new ArrayList<>(arr.length);
        for(int i = 0; i < arr.length - 2; i++){
            int pre = i + 1;
            int last = arr.length - 1;

            int rest = total - arr[i];
            while(pre < last){
                int plusResult = arr[pre] + arr[last];
                if(plusResult == rest){
                    resultList.add(Arrays.asList(new Pair<>(arr[i], i), new Pair<>(arr[pre], pre), new Pair<>(arr[last], last)));
                    pre++;
                    last--;
                }else if(plusResult < rest){
                    pre++;
                }else{
                    last--;
                }
            }
        }

        return resultList;
    }

    /**
     * 允许数字重复的情况 && 三个数字
     */
    private static List<List<Pair<Integer, Integer>>> find4(int[] arr, int total){
        Arrays.sort(arr);

        List<List<Pair<Integer, Integer>>> resultList = new ArrayList<>(arr.length);
        for(int i = 0; i < arr.length - 2; i++){
            int pre = i + 1;
            int last = arr.length - 1;

            int rest = total - arr[i];
            while(pre < last){
                int plusResult = arr[pre] + arr[last];
                if(plusResult == rest){
                    resultList.add(Arrays.asList(new Pair<>(arr[i], i), new Pair<>(arr[pre], pre), new Pair<>(arr[last], last)));

                    // 因为要同时要pre进一位和last退一位，所以要处理pre不变+last退一位 和 pre进一位+last不变时是否满足需求。同时该需要处理pre和next碰头的情况
                    if(pre < last -1 && rest == arr[pre] + arr[last - 1]){
                        resultList.add(Arrays.asList(new Pair<>(arr[i], i), new Pair<>(arr[pre], pre), new Pair<>(arr[last - 1], last - 1)));
                    }

                    if(pre + 1 < last && rest == arr[pre + 1] + arr[last]){
                        resultList.add(Arrays.asList(new Pair<>(arr[i], i), new Pair<>(arr[pre + 1], pre + 1), new Pair<>(arr[last], last)));
                    }
                    pre++;
                    last--;
                }else if(plusResult < rest){
                    pre++;
                }else{
                    last--;
                }
            }
        }

        return resultList;
    }

    /**
     * 允许数字重复的情况 && 不限制个数
     */
    private static void find5(int[] arr, int total){
        Arrays.sort(arr);

        innerFind5(arr, 1, total - arr[0], String.valueOf(arr[0]));
        innerFind5(arr, 1, total, "");
    }

    private static final void innerFind5(int[] arr, int startIdx, int rest, String result){
        if(startIdx >= arr.length){
            return;
        }

        if(arr[startIdx] == rest){
            System.out.println(fillMe(result, String.valueOf(arr[startIdx])));
            return;
        }

        if(arr[startIdx] > rest){
            return;
        }else{
            innerFind5(arr, startIdx + 1, rest - arr[startIdx], fillMe(result, String.valueOf(arr[startIdx])));
            innerFind5(arr, startIdx + 1, rest, result);
        }
    }

    private static String fillMe(String result, String me){
        if(StringUtils.isEmpty(result)){
            result = me;
        }else{
            result += " + " + me;
        }

        return result;
    }


 static class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;
 }

    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders (TreeNode root) {
        // write code here

        int[][] result = new int[3][];

        List<Integer> firstResult = new ArrayList<>();
        first(root, firstResult);

        List<Integer> centerResult = new ArrayList<>();
        center(root, centerResult);

        List<Integer> rightResult = new ArrayList<>();
        right(root, rightResult);

        result[0] = toArray(firstResult);
        result[1] = toArray(centerResult);
        result[2] = toArray(rightResult);

        return result;
    }

    private int[] toArray(List<Integer> resultList){
        int[] resultArr = new int[resultList.size()];

        for (int i =0; i < resultList.size(); i++) {
            resultArr[i] = resultList.get(i);
        }

        return resultArr;
    }

    private void first(TreeNode node, List<Integer> resultList){
        resultList.add(node.val);
        if(node.left != null){
            first(node.left, resultList);
        }

        if(node.right != null){
            first(node.right, resultList);
        }
    }

    private void center(TreeNode node, List<Integer> resultList){
        if(node.left != null){
            first(node.left, resultList);
        }

        resultList.add(node.val);

        if(node.right != null){
            first(node.right, resultList);
        }
    }

    private void right(TreeNode node, List<Integer> resultList){
        if(node.left != null){
            first(node.left, resultList);
        }

        if(node.right != null){
            first(node.right, resultList);
        }

        resultList.add(node.val);
    }

}
