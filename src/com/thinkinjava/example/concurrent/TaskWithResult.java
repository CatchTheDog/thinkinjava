package com.thinkinjava.example.concurrent;

import java.util.concurrent.Callable;

/**
 * Runnable 不返回任何值，如果希望能够返回一个值，则可以使用Callable接口
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }
}
