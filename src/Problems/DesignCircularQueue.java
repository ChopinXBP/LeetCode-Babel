package Problems;

import java.util.LinkedList;

/**
 *
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out)
 * principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 *
 */

public class DesignCircularQueue {
    class MyCircularQueue {

        final int[] array;
        int head = 0;
        int tail = -1;
        int size = 0;

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            array = new int[k];
        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if(isFull()){
                return false;
            }
            tail = (tail + 1) % array.length;
            array[tail] = value;
            size++;
            return true;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if(isEmpty()){
                return false;
            }
            head = (head + 1) % array.length;
            size--;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {
            return isEmpty() ? -1 : array[head];
        }

        /** Get the last item from the queue. */
        public int Rear() {
            return isEmpty() ? -1 : array[tail];
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return size == 0;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return size == array.length;
        }
    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
}
