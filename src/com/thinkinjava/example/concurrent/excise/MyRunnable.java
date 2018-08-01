package com.thinkinjava.example.concurrent.excise;

public class MyRunnable implements Runnable {

    private static int count = 0;

    private final int threadCount = count ++;

    public MyRunnable() {
        System.out.println("MyRunnable start init!");
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("线程 "+threadCount+" run 开始。");
        for(int i = 0;i<2;i++){
            System.out.println("线程 "+threadCount+" 第 "+i+" 次run内循环。");
        }
        System.out.println("线程 "+threadCount+" run 结束。");
        Thread.yield();
    }
}
