package com.thinkinjava.example.concurrent;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {



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
       try{
           while(true){
               TimeUnit.MILLISECONDS.sleep(100);
               System.out.println(Thread.currentThread() + " " + this);
           }
       }catch (InterruptedException e) {
           System.out.println("sleep() interrupted");
       }
    }

    public static void main(String [] args) throws InterruptedException {
        for(int i =0;i<10;i++){
            Thread daemon = new Thread(new SimpleDaemons());
            //将线程设置为后台线程
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started ");
        TimeUnit.MILLISECONDS.sleep(200);
    }
}
