package com.thinkinjava.example.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    public static void main(String [] args){
        //SingleThreadExecutor 像是线程数量为1的FixedThreadPool;使用一个线程执行所有任务，多个任务会依次排队执行；上一个任务执行完成以后才会执行下一个任务
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for(int i = 0;i<5;i++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
