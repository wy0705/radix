package hash;

import java.util.*;

public class ConsistentHash<T> {
    private final IHashService iHashService;
    private final int numberiHashService;
    private final SortedMap<Long,T> circle = new TreeMap<Long, T>();


    public ConsistentHash(IHashService iHashService, int numberiHashService, ArrayList<T> nodes) {
        this.iHashService = iHashService;
        this.numberiHashService = numberiHashService;
        for (T node:nodes){
            add(node);
        }
    }

    //增加真实机器节点
    public void add(T node){
        for (int i = 0; i < this.numberiHashService; i++) {
            circle.put(this.iHashService.hash(node.toString()+i),node);
        }
    }

    //删除真实机器节点
    public void remove(T node){
        for (int i = 0; i < this.numberiHashService; i++) {
            circle.remove(this.iHashService.hash(node.toString()+i));
        }
    }

    //找到虚拟节点
    public T get(String key){
        if (circle.isEmpty()) return null;

        long hash = iHashService.hash(key);

        // 沿环的顺时针找到一个虚拟节点
        if (!circle.containsKey(hash)) {
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }
}
