package com.thinkinjava.example.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    public static void main(String [] args){
        //使用submit提交Callable任务，其产生Future对象；使用Future get()获取执行结果；可以使用isDone()方法查询Future是否已经完成
        //如果不调用isDone()直接调用get()方法获取结果，则get()将阻塞，直到结果就绪
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            results.add(exec.submit(new TaskWithResult(i)));
        for(Future<String> fs : results) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
                return;
            }finally {
                exec.shutdown();
            }
        }
    }
}
