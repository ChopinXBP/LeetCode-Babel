package JUC;

import java.util.concurrent.Semaphore;

/**
 *
 * The philosophers' ids are numbered from 0 to 4 in a clockwise order.
 * Implement the function void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork) where:
 * philosopher is the id of the philosopher who wants to eat.
 * pickLeftFork and pickRightFork are functions you can call to pick the corresponding forks of that philosopher.
 * eat is a function you can call to let the philosopher eat once he has picked both forks.
 * putLeftFork and pickRightFork are functions you can call to put down the corresponding forks of that philosopher.
 * The philosophers are assumed to be thinking as long as they are not asking to eat (the function is not being called with their number).
 * Five threads, each representing a philosopher, will simultaneously use one object of your class to simulate the process.
 * It is possible that the function will be called for the same philosopher more than once, even before the last call ends.
 * 哲学家从 0 到 4 按 顺时针 编号。请实现函数 void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork)：
 * philosopher 哲学家的编号。
 * pickLeftFork 和 pickRightFork 表示拿起左边或右边的叉子。
 * eat 表示吃面。
 * putLeftFork 和 pickRightFork 表示放下左边或右边的叉子。
 * 由于哲学家不是在吃面就是在想着啥时候吃面，所以思考这个方法没有对应的回调。
 * 给你 5 个线程，每个都代表一个哲学家，请你使用类的同一个对象来模拟这个过程。在最后一次调用结束之前，可能会为同一个哲学家多次调用该函数。
 *
 */

public class TheDiningPhilosophers {
}

//Dijkstra
//最后一个哲学家先右后左，其他哲学家先左后右
class DiningPhilosophers {

    private Semaphore[] forks;

    public DiningPhilosophers() {
        forks = new Semaphore[5];
        //不能用Arrays.fill的赋值方式
        for(int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        final Semaphore left = forks[philosopher];
        final Semaphore right = forks[(philosopher + 4) % 5];
        final boolean type = (philosopher & 1) == 1;
        if(type){
            right.acquire();
            pickRightFork.run();
            left.acquire();
            pickLeftFork.run();

            eat.run();

            putLeftFork.run();
            left.release();
            putRightFork.run();
            right.release();

        }else{
            left.acquire();
            pickLeftFork.run();
            right.acquire();
            pickRightFork.run();

            eat.run();

            putRightFork.run();
            right.release();
            putLeftFork.run();
            left.release();
        }
    }
}
