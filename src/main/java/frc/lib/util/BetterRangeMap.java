package frc.lib.util;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import frc.robot.util.BetterArrayList;

import java.util.Map;

@Deprecated
public class BetterRangeMap<K extends Comparable<K>, V>{

    private final RangeMap<K, V> internalMap;

    public BetterRangeMap() {
        this.internalMap = TreeRangeMap.create();
    }

    public void put(Range<K> range, V value) {
        internalMap.put(range, value);
    }

    public V get(K key) {
        return internalMap.get(key);
    }

    public BetterArrayList<V> getAll(K key) {
        BetterArrayList<V> values = new BetterArrayList<>();

        for (Map.Entry<Range<K>, V> entry : internalMap.asMapOfRanges().entrySet()) {
            if (entry.getKey().contains(key)) {
                values.add(entry.getValue());
            }
        }

        if (values.size() == 0)
            return null;

        return values;
    }
}
