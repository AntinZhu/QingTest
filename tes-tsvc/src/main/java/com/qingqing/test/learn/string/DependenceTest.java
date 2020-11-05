package com.qingqing.test.learn.string;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by zhujianxing on 2020/11/2.
 */
public class DependenceTest {

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> pairList = Arrays.asList(
            new Pair<>(1, 2),
            new Pair<>(3, 4),
            new Pair<>(2, 3),
            new Pair<>(4, 2)
        );

        System.out.println(new DependenceTest().solve(pairList));
    }

    public List<Integer> solve(List<Pair<Integer, Integer>> pairList){
        Set<Integer> nodeSet = new HashSet<>();
        Map<Integer, List<Pair<Integer, Integer>>> keyMap = new HashMap<>();
        for(Pair<Integer, Integer> pair : pairList){
            nodeSet.add(pair.getKey());
            nodeSet.add(pair.getValue());
            List<Pair<Integer, Integer>> keyList = keyMap.get(pair.getKey());
            if(keyList == null){
                keyList = new ArrayList<>();
                keyMap.put(pair.getKey(), keyList);
            }
            keyList.add(pair);
        }

        List<Integer> resultList = new ArrayList<>(nodeSet.size());
        while(!nodeSet.isEmpty()){
            Set<Integer> hasDependenceSet = getHasDependenceSet(keyMap);
            Iterator<Integer> restNodeIter = nodeSet.iterator();
            boolean findNoDependence = false;
            while (restNodeIter.hasNext()){
                Integer node = restNodeIter.next();
                if(!hasDependenceSet.contains(node)){
                    resultList.add(node);
                    restNodeIter.remove();
                    keyMap.remove(node);

                    findNoDependence = true;
                }
            }


            if(!findNoDependence){
                throw new RuntimeException("node dependence loop cycle");
            }
        }

        return resultList;
    }

    public Set<Integer> getHasDependenceSet(Map<Integer, List<Pair<Integer, Integer>>> keyMap){
        Set<Integer> resultSet = new HashSet<>();
        for (List<Pair<Integer, Integer>> pairs : keyMap.values()) {
            pairs.forEach(e -> resultSet.add(e.getValue()));
        }

        return resultSet;
    }

}
