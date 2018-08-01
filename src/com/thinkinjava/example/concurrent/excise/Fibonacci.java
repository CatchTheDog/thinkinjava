package com.thinkinjava.example.concurrent.excise;

public class Fibonacci implements Runnable{

    private int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    private int fibonacci(int n){
        if(n == 1 || n == 2) return 1;
        while(n > 2){
            return fibonacci(n-1) + fibonacci(n-2);
        }
        return 0;
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
        System.out.println("斐波那契数列 "+n+" : "+fibonacci(n));
    }
}
