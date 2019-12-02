package JUC;

import java.util.concurrent.Semaphore;

/**
 *
 * There are two kinds of threads, oxygen and hydrogen. Your goal is to group these threads to form water molecules.
 * There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads will be given releaseHydrogen
 * and releaseOxygen methods respectively, which will allow them to pass the barrier. These threads should pass the barrier in groups of three,
 * and they must be able to immediately bond with each other to form a water molecule.
 * You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.
 * In other words:
 * If an oxygen thread arrives at the barrier when no hydrogen threads are present, it has to wait for two hydrogen threads.
 * If a hydrogen thread arrives at the barrier when no other threads are present, it has to wait for an oxygen thread and another hydrogen thread.
 * We don’t have to worry about matching the threads up explicitly; that is, the threads do not necessarily know which other threads they are paired up with.
 * The key is just that threads pass the barrier in complete sets; thus, if we examine the sequence of threads that bond and divide them into groups of three,
 * each group should contain one oxygen and two hydrogen threads.
 * Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.
 * 现在有两种线程，氢 oxygen 和氧 hydrogen，你的目标是组织这两种线程来产生水分子。
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
 * 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 * 换句话说:
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 * 书写满足这些限制条件的氢、氧线程同步代码。
 *
 */

public class BuildingH2O {
}

//Semaphore
class H2O {
    private Semaphore semaphoreH;
    private Semaphore semaphoreO;

    public H2O() {
        semaphoreH = new Semaphore(2);
        semaphoreO = new Semaphore(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        try{
            semaphoreH.acquire(1);
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphoreO.release(1);
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        try{
            semaphoreO.acquire(2);
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphoreH.release(2);
        }
    }
}