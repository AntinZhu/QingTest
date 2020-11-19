package com.qingqing.test.learn.collection;

import java.util.TreeMap;

/**
 * Created by zhujianxing on 2020/11/17.
 */
public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<Integer, String> headMap = new TreeMap();
        headMap.put(1, "A");
        headMap.put(2, "B");
        headMap.put(3, "C");
        headMap.put(4, "D");
        headMap.put(6, "E");
        headMap.put(10, "F");

        System.out.println("headMap.entrySet()=>" + headMap.entrySet());
        System.out.println("headMap.ceilingEntry(5)=>" + headMap.ceilingEntry(5));
        System.out.println("headMap.ceilingKey(5)=>" + headMap.ceilingKey(5));
        System.out.println("headMap.clone()=>" + headMap.clone());
        System.out.println("headMap.comparator()=>" + headMap.comparator());
        System.out.println("headMap.containsKey(4)=>" + headMap.containsKey(4));
        System.out.println("headMap.containsValue(\"C\")=>" + headMap.containsValue("C"));
        System.out.println("headMap.descendingKeySet()=>" + headMap.descendingKeySet());
        System.out.println("headMap.descendingMap()=>" + headMap.descendingMap());
        System.out.println("headMap.firstEntry()=>" + headMap.firstEntry());
        System.out.println("headMap.firstKey()=>" + headMap.firstKey());
        System.out.println("headMap.floorEntry(4)=>" + headMap.floorEntry(4));
        System.out.println("headMap.floorKey(5)=>" + headMap.floorKey(5));
        System.out.println("headMap.headMap(6)=>" + headMap.headMap(6));
        System.out.println("headMap.headMap(6, true)=>" + headMap.headMap(6, true));
        System.out.println("headMap.higherEntry(6)=>" + headMap.higherEntry(6));
        System.out.println("headMap.higherKey(6)=>" + headMap.higherKey(6));
        System.out.println("headMap.lastEntry()=>" + headMap.lastEntry());
        System.out.println("headMap.lastKey()=>" + headMap.lastKey());
        System.out.println("headMap.pollFirstEntry()=>" + headMap.pollFirstEntry());
        System.out.println("headMap.lowerEntry(6)=>" + headMap.lowerEntry(6));
        System.out.println("headMap.lowerKey(6)=>" + headMap.lowerKey(6));
        System.out.println("headMap.pollLastEntry()=>" + headMap.pollLastEntry());
        System.out.println("headMap.replace(3, \"M\")=>" + headMap.replace(3, "M"));
        System.out.println("headMap.replace(3, \"N\", \"M\")=>" + headMap.replace(3, "N", "M"));
        System.out.println("headMap.replace(3, \"M\", \"N\")=>" + headMap.replace(3, "M", "N"));
        System.out.println("headMap.subMap(1, 3)=>" + headMap.subMap(1, 3));
        System.out.println("headMap.headMap.subMap(1, true, 3, true)=>" + headMap.subMap(1, true, 3, true));
        System.out.println("headMap.tailMap(3)=>" + headMap.tailMap(3));
        System.out.println("headMap.headMap.tailMap(4, false)=>" + headMap.tailMap(4, false));


    }
}
