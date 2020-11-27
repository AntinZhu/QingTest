package com.qingqing.test.learn.integer;

import com.qingqing.test.learn.tree.TreeUtils.TreeNode;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhujianxing on 2020/11/12.
 */
public class CoinTest {

    public static void main(String[] args) {
        System.out.println(new CoinTest().ladderLength("hit","cog",Arrays.asList("hot","dot","dog","lot","log","cog")));
    }

    public int coinChange2(int[] coins, int amount) {
        if(amount == 0){
            return 0;
        }

        int[] resultArr = new int[amount + 1];
        Arrays.fill(resultArr, amount + 1);
        resultArr[0] = 0;
        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                if(coin <= i){
                    resultArr[i] = Math.min(resultArr[i], resultArr[i - coin] + 1);
                }
            }
        }

        return resultArr[amount] == amount + 1? -1 : resultArr[amount];
    }

    public int coinChange(int[] coins, int amount) {
        return innerCoinChange(coins, amount, new int[amount]);
    }

    private int innerCoinChange(int[] coins, int amount, int[] tmpResult){
        if(amount == 0){
            return 0;
        }

        if(tmpResult[amount - 1] != 0){
            return tmpResult[amount - 1];
        }

        int minCount = Integer.MAX_VALUE;
        for(int i = coins.length - 1; i >=0; i--){
            if(amount >= coins[i]){
                int count = 1 + innerCoinChange(coins, amount - coins[i], tmpResult);
                if(count >= 0 && minCount > count){
                    minCount = count;
                }
            }
        }

        tmpResult[amount - 1] = minCount == Integer.MAX_VALUE? -1 : minCount;

        return tmpResult[amount - 1];
    }

    private class PreNode{
        private String value;
        private List<PreNode> preList;

        public PreNode(String value) {
            this.value = value;
        }

        public void addPre(PreNode pre){
            if(preList == null){
                preList = new LinkedList<>();
            }
            preList.add(pre);
        }

        public List<PreNode> getPreList() {
            return preList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PreNode preNode = (PreNode) o;

            return value.equals(preNode.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    public List<List<String>> ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.isEmpty()){
            return Collections.emptyList();
        }

        Set<String> wordSet = new HashSet<>();
        for(String word : wordList){
            wordSet.add(word);
        }

        if(!wordSet.contains(endWord)){
            return Collections.emptyList();
        }
        wordSet.remove(beginWord);

        List<PreNode> resultNodeList = new LinkedList<>();
        Queue<PreNode> queue = new LinkedList();
        queue.offer(new PreNode(beginWord));

        Map<String, PreNode> preNodeMap = new HashMap();
        Set<String> removeSet = new HashSet<>();

        boolean finish = false;
        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            for(int i = 0; i < queueSize; i++){
                PreNode mathWord = queue.poll();
                Iterator<String> iter =  wordSet.iterator();
                while (iter.hasNext()){
                    String word = iter.next();
                    if(isSuit(mathWord.value, word)){
                        PreNode thisNode = preNodeMap.get(word);
                        if(thisNode == null){
                            thisNode = new PreNode(word);
                            preNodeMap.put(word, thisNode);

                            queue.offer(thisNode);
                            removeSet.add(word);
                        }
                        thisNode.addPre(mathWord);
                        if(endWord.equals(word)){
                            finish = true;
                            resultNodeList.add(thisNode);
                        }
                    }
                }
            }

            wordSet.removeAll(removeSet);
            if(finish){
                break;
            }
        }

        List<List<String>> resultList = new ArrayList<>(resultNodeList.size());
        toResult(resultNodeList, new LinkedList<>(), resultList);
        return resultList;
    }

    private void toResult(List<PreNode> resultNodeList, LinkedList<String> tmpList, List<List<String>> resultList){
        if(resultNodeList == null){
            resultList.add(new ArrayList<>(tmpList));
            return;
        }

        for(PreNode preNode : resultNodeList){
            tmpList.addFirst(preNode.value);
            toResult(preNode.getPreList(), tmpList, resultList);
            tmpList.removeFirst();
        }
    }

    private boolean isSuit(String s1, String s2){
        int diffCnt = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diffCnt++;
                if(diffCnt > 1){
                    return false;
                }
            }
        }

        return diffCnt == 1;
    }
}
