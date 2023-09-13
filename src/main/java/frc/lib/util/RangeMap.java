package frc.lib.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.collect.Range;

public class RangeMap<K extends Comparable<K>, V> {
    
    private Map<Range<K>, V> ranges = new HashMap<>();
    
    /* public static <K extends Comparable<K>, V> RangeMap<K, V> create(){
        return new RangeMap<>();
    } */

    public RangeMap(){}

    public void put(Range<K> range, V value){
        ranges.put(range, value);
    }
    
    public BetterArrayList<V> get(K key){
        BetterArrayList<V> values = new BetterArrayList<>();
        for(Map.Entry<Range<K>, V> entry : ranges.entrySet()){
            if(entry.getKey().contains(key)){
                values.add(entry.getValue());
            }
        }
        if(values.size() == 0){
            return null;
        } else {
            return values;
        }
    }
}