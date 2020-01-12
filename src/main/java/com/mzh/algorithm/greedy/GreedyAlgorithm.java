package com.mzh.algorithm.greedy;

import java.util.*;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        Map<String, Set<String>> broadcasts = new HashMap<String, Set<String>>();

        Set<String> hashset1 = new HashSet<String>();
        hashset1.add("北京");
        hashset1.add("上海");
        hashset1.add("天津");

        Set<String> hashset2 = new HashSet<String>();
        hashset2.add("广州");
        hashset2.add("北京");
        hashset2.add("深圳");

        Set<String> hashset3 = new HashSet<String>();
        hashset3.add("成都");
        hashset3.add("上海");
        hashset3.add("杭州");

        Set<String> hashset4 = new HashSet<String>();
        hashset4.add("上海");
        hashset4.add("天津");

        Set<String> hashset5 = new HashSet<String>();
        hashset5.add("杭州");
        hashset5.add("大连");

        broadcasts.put("k1", hashset1);
        broadcasts.put("k2", hashset2);
        broadcasts.put("k3", hashset3);
        broadcasts.put("k4", hashset4);
        broadcasts.put("k5", hashset5);

        List<String> bds = greedy(broadcasts);

        System.out.println(bds);

    }

    //贪心算法
    public static List<String> greedy(Map<String, Set<String>> broadcasts) {
        List<String> bds = new ArrayList<String>();

        Set<String> citys = new HashSet<String>();

        for (Map.Entry<String, Set<String>> entry : broadcasts.entrySet()) {
            for (String city : entry.getValue()) {
                citys.add(city);
            }
        }


        while (!citys.isEmpty()) {
            String maxKey = null;
            for (Map.Entry<String, Set<String>> entry : broadcasts.entrySet()) {
                Set<String> tempSet = entry.getValue();
                tempSet.retainAll(citys);

                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = entry.getKey();
                }


//                Set<String> tempSet2 = broadcasts.get(maxKey);
//                if (tempSet2 != null) {
//                    tempSet2.retainAll(citys);
//                }
//                if (tempSet.size() > 0 && (maxKey == null || (tempSet2 != null && tempSet.size() > tempSet2.size()))) {
//                    maxKey = entry.getKey();
//                }
            }
            if (maxKey != null) {
                System.out.println(maxKey);
                bds.add(maxKey);
                citys.removeAll(broadcasts.get(maxKey));
                broadcasts.remove(maxKey);
            }
        }


        return bds;
    }
}
