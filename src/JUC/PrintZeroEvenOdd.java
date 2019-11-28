package JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 *
 * Suppose you are given the following code:
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // constructor
 *   public void zero(printNumber) { ... }  // only output 0's
 *   public void even(printNumber) { ... }  // only output even numbers
 *   public void odd(printNumber) { ... }   // only output odd numbers
 * }
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506... where the length of the series must be 2n.
 * 假设有这么一个类：
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *
 */

public class PrintZeroEvenOdd {

}

class ZeroEvenOdd {
    private int n;
    private ReentrantLock lock;
    private Condition conditionZero;
    private Condition conditionOdd;
    private Condition conditionEven;
    private volatile int flag;

    public ZeroEvenOdd(int n) {
        this.n = n;
        lock = new ReentrantLock();
        conditionZero = lock.newCondition();
        conditionOdd = lock.newCondition();
        conditionEven = lock.newCondition();
        flag = 0;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try{
            for(int i = 1; i <= n; i++) {
                while(flag != 0){
                    conditionZero.await();
                }
                printNumber.accept(0);
                if((i & 1) == 1){
                    flag = 1;
                    conditionOdd.signal();
                }else{
                    flag = 2;
                    conditionEven.signal();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try{
            for(int i = 1; i <= n; i += 2) {
                while(flag != 1){
                    conditionOdd.await();
                }
                printNumber.accept(i);
                flag = 0;
                conditionZero.signal();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try{
            for(int i = 2; i <= n; i += 2) {
                while(flag != 2){
                    conditionEven.await();
                }
                printNumber.accept(i);
                flag = 0;
                conditionZero.signal();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
