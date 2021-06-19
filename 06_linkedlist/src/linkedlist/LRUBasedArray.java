package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于数组实现LRU缓存
 * 1.时间复杂度O(n)
 * 2.空间复杂度O(n)
 * 3.不支持null的缓存
 *
 * 思路：维护一个数组和Map映射集合（元素，元素在数组中的下标），越靠近数组尾部的节点是越早之前访问的。
 * 1）如果此数据已经被缓存在数组中了，则删除原位置，添加到数组头
 * 2）没有在缓存数组中：
 *     此时缓存未满，将数组右移，将新元素插在数组头部
 *     此时缓存已满，将数组最后一个元素删除，将新元素插入在数组头部
 */
public class LRUBasedArray<T> {

    private static final int DEFAULT_CAPACITY = (1 << 3);// 1*2^3 = 8
    private int capacity;
    private int count;
    private T[] value;
    private Map<T, Integer> holder;

    public LRUBasedArray(int capacity){
        this.capacity = capacity;
        value = (T[]) new Object[capacity];
        count = 0;
        holder = new HashMap<T, Integer>(capacity);
    }
    public LRUBasedArray(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * 模拟访问某个值
     * 时间复杂度：O(n)
     * @param object
     */
    public void offer(T object){
        if(object == null){
            throw new IllegalArgumentException("该缓存容器不支持null！");
        }
        Integer index = holder.get(object);
        if(index == null){
            if(isFull()){
                removeAndCache(object); //O(n)
            }else {
                cache(object, count); //O(n)
            }
        }else {
            update(index); //O(n)
        }
    }

    /**
     * 若缓存中有指定的值，则更新位置
     * 时间复杂度：O(n)
     * @param end
     */
    public void update(int end){
        T target = value[end];
        rightShift(end); //O(n)
        value[0] = target;
        holder.put(target, 0);
    }


    /**
     * 缓存到数据的头部，但要先右移
     * 时间复杂度：O(n)
     * @param object
     * @param end 数组右移的边界
     */
    public void cache(T object, int end){
        rightShift(end); //O(n)
        value[0] = object;
        holder.put(object, 0);
        count++;
    }
    /**
     * 缓存满的情况，踢出后，在缓存到数组的头部
     * 时间复杂度：O(n)
     * @param object
     */
    public void removeAndCache(T object){
        T key = value[--count];
        holder.remove(key);
        cache(object, count); //O(n)
    }

    /**
     * end左边的数据统一右移一位
     * 时间复杂度：O(n)
     * @param end
     */
    private void rightShift(int end){
        for(int i = end - 1; i >= 0; i--){
            value[i + 1] = value[i];
            holder.put(value[i], i + 1);
        }
    }
    public boolean isContain(T object){ //O(1)
        return holder.containsKey(object);
    }
    public boolean isEmpty(){ //O(1)
        return count == 0;
    }
    public boolean isFull(){ //O(1)
        return count == capacity;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i++){
            sb.append(value[i] + " ");
        }
        return sb.toString();
    }

    static class TestLRUBasedArray {

        public static void main(String[] args) {
            testDefaultConstructor();
//            testSpecifiedConstructor(4);
//            testWithException();
        }

        private static void testWithException() {
            LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
            lru.offer(null);
        }

        public static void testDefaultConstructor() {
            System.out.println("======无参测试========");
            LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
            lru.offer(1);
            lru.offer(2);
            lru.offer(3);
            lru.offer(4);
            lru.offer(5);
            System.out.println(lru);
            lru.offer(6);
            lru.offer(7);
            lru.offer(8);
            lru.offer(9);
            System.out.println(lru);
        }

        public static void testSpecifiedConstructor(int capacity) {
            System.out.println("======有参测试========");
            LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>(capacity);
            lru.offer(1);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
            lru.offer(3);
            System.out.println(lru);
            lru.offer(4);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
            lru.offer(4);
            System.out.println(lru);
            lru.offer(7);
            System.out.println(lru);
            lru.offer(1);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
        }
    }
}
