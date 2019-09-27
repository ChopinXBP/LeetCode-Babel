/**
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * Follow up: Could you do both operations in O(1) time complexity?
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * 进阶: 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 */

import java.util.HashMap;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
public class LRUCache {

    //双向链表构建，双向链表的尾部为最近最少使用结点
    class Data{
        int key;
        int value;
        Data pre;
        Data next;
    }

    //每次将新结点加入双向链表头部
    private void addData(Data data){
        data.next = head.next;
        data.pre = head;
        head.next.pre = data;
        head.next = data;
    }

    //删除指定结点
    private void removeData(Data data){
        Data pre = data.pre;
        Data next = data.next;
        pre.next = next;
        next.pre = pre;
        data.pre = null;
        data.next = null;
    }

    //将指定结点转移至链表头部
    private void moveToHead(Data data){
        removeData(data);
        addData(data);
    }

    //将尾部元素（LRU结点）删除并返回
    private Data popTail(){
        Data data = tail.pre;
        removeData(data);
        return data;
    }

    //成员参数设置
    private HashMap<Integer , Data> cacheMap;
    private int capacity;
    private int size;
    Data head;
    Data tail;

    public LRUCache(int capacity) {
        cacheMap = new HashMap<>();
        this.capacity = capacity;
        size = 0;
        head = new Data();
        tail = new Data();

        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if(cacheMap.containsKey(key)){
            moveToHead(cacheMap.get(key));
            return cacheMap.get(key).value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(!cacheMap.containsKey(key)){
            if(size == capacity){
                Data data = popTail();
                cacheMap.remove(data.key);
                size--;
            }
            Data data = new Data();
            data.key = key;
            data.value = value;
            addData(data);
            cacheMap.put(key, data);
            size++;
        }else{
            Data data = cacheMap.get(key);
            data.value = value;
            moveToHead(data);
        }
    }
}
