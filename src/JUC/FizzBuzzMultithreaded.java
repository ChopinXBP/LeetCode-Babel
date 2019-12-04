package JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 *
 * Write a program that outputs the string representation of numbers from 1 to n, however:
 * If the number is divisible by 3, output "fizz".
 * If the number is divisible by 5, output "buzz".
 * If the number is divisible by both 3 and 5, output "fizzbuzz".
 * For example, for n = 15, we output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 *
 */

public class FizzBuzzMultithreaded {
}

//ReentrantLock + AtomicInteger
//原子类用于条件判别和自增。所有线程共同争用同一把锁，直到每个线程打印之后就绪所有其他线程并释放锁，不满足条件的线程继续阻塞，满足条件的线程可以打印。
class FizzBuzz {
    private int n;
    private ReentrantLock lock;
    private Condition condition;
    private AtomicInteger num;

    public FizzBuzz(int n) {
        this.n = n;
        lock = new ReentrantLock();
        condition = lock.newCondition();
        num = new AtomicInteger(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(num.get() <= n){
            lock.lock();
            try{
                final int curNum = num.get();
                if(curNum % 3 == 0 && curNum % 5 != 0){
                    printFizz.run();
                    num.getAndAdd(1);
                    condition.signalAll();
                }else {
                    condition.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(num.get() <= n){
            lock.lock();
            try{
                final int curNum = num.get();
                if(curNum % 3 != 0 && curNum % 5 == 0){
                    printBuzz.run();
                    num.getAndAdd(1);
                    condition.signalAll();
                }else{
                    condition.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(num.get() <= n){
            lock.lock();
            try{
                final int curNum = num.get();
                if(curNum % 3 == 0 && curNum % 5 == 0){
                    printFizzBuzz.run();
                    num.getAndAdd(1);
                    condition.signalAll();
                }else {
                    condition.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while(num.get() <= n){
            lock.lock();
            try{
                final int curNum = num.get();
                if(curNum % 3 != 0 && curNum % 5 != 0){
                    printNumber.accept(num.getAndAdd(1));
                    condition.signalAll();
                }else {
                    condition.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}

//Semaphore
//由number线程负责控制信号量，每次打印前进行阻塞，根据当前数字释放信号量；其余线程打印后均释放number线程的信号量。同时仅有一个线程在打印
class FizzBuzz2 {
    private int n;
    private Semaphore semaphoreF;
    private Semaphore semaphoreB;
    private Semaphore semaphoreFB;
    private Semaphore semaphoreN;

    public FizzBuzz2(int n) {
        this.n = n;
        semaphoreF = new Semaphore(0);
        semaphoreB = new Semaphore(0);
        semaphoreFB = new Semaphore(0);
        semaphoreN = new Semaphore(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if(i % 3 == 0 && i % 5 != 0){
                semaphoreF.acquire();
                printFizz.run();
                semaphoreN.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if(i % 3 != 0 && i % 5 == 0){
                semaphoreB.acquire();
                printBuzz.run();
                semaphoreN.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if(i % 3 == 0 && i % 5 == 0){
                semaphoreFB.acquire();
                printFizzBuzz.run();
                semaphoreN.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            semaphoreN.acquire();
            if(i % 3 != 0 && i % 5 != 0){
                printNumber.accept(i);
                semaphoreN.release();
            }else if(i % 5 != 0){
                semaphoreF.release();
            }else if(i % 3 != 0){
                semaphoreB.release();
            }else{
                semaphoreFB.release();
            }
        }
    }
}

//CyclicBarrier
//CyclicBarrier每轮for循环等待四个线程，并同时释放，但只有能够打印的线程进行打印。
class FizzBuzz3 {
    private int n;
    private CyclicBarrier cyclicBarrier;

    public FizzBuzz3(int n) {
        this.n = n;
        cyclicBarrier = new CyclicBarrier(4);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            try{
                cyclicBarrier.await();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
            if(i % 3 == 0 && i % 5 != 0){
                printFizz.run();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            try{
                cyclicBarrier.await();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
            if(i % 3 != 0 && i % 5 == 0){
                printBuzz.run();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            try{
                cyclicBarrier.await();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
            if(i % 3 == 0 && i % 5 == 0){
                printFizzBuzz.run();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            try{
                cyclicBarrier.await();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
            if(i % 3 != 0 && i % 5 != 0){
                printNumber.accept(i);
            }
        }
    }
}



