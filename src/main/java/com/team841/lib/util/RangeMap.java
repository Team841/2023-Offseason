package com.team841.lib.util;
import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.Range;

import com.team841.offseason2023.util.BetterArrayList;

@Deprecated
public class RangeMap<K extends Comparable<K>, V> {

    private final Map<Range<K>, V> ranges = new HashMap<>();

    /* public static <K extends Comparable<K>, V> RangeMap<K, V> create(){
        return new RangeMap<>();
    } */

    public RangeMap(){}

    public void put(Range<K> range, V value){
        ranges.put(range, value);
    }

    public BetterArrayList<V> get(K key){
        BetterArrayList<V> values = new BetterArrayList<>();
        for(Map.Entry<Range<K>, V> entry : this.ranges.entrySet()){
            if(entry.getKey().contains(key)){
                values.add(entry.getValue());
            }
        }

        return values;
    }
}
