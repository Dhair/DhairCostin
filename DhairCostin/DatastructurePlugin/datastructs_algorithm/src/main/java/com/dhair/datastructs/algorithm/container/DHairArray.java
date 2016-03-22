package com.dhair.datastructs.algorithm.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Creator: dengshengjin on 16/2/18 10:17
 * Email: deng.shengjin@zuimeia.com
 */
public class DHairArray {
    public void test() {
        TreeMap mTreeMap = new TreeMap(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Map<String, String> treeMap = new TreeMap<>();
        List<Map.Entry<String, String>> list = new ArrayList<>(treeMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });
    }
}
