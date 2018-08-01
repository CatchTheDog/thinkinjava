package com.thinkinjava.example.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {

    private int countDown = 5;
    private volatile double d; //No optimization
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + " : " + countDown;
    }

    /**
     * 通过调用yield()方法来建议具有相同优先级的其他线程可以运行，但是不会保证此建议会被采纳
     */
    @Override
    public void run() {
       Thread.currentThread().setPriority(priority);
       while(true){
           for(int i = 1; i < 100000; i++){
               d += (Math.PI + Math.E)/i;
               if(i % 1000 == 0)
                   Thread.yield();
           }
           System.out.println(this);
           if(--countDown == 0) return;
       }
    }

    public static void main(String [] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5;i++)
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
