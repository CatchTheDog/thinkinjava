package com.thinkinjava.example.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String [] args){
        //ExecutorService 知道如何构建恰当的上下文来执行Runnable对象；
        //CachedThreadPool将为每个任务都创建一个线程，创建与所需线程数量相同的线程，在回收旧线程时停止创建新线程
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++){
            exec.execute(new LiftOff());
        }
        //调用shutdown()方法可以防止新任务被提交给exec,当前线程将继续执行shutdown()被调用之前提交的所有任务，在所有任务执行完毕后才会退出。
        exec.shutdown();
    }
}
