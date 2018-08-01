package com.thinkinjava.example.concurrent.excise;

public class FibonacciThreads {
    public static void main(String [] args){
        for(int i = 1;i<10;i++){
            new Thread(new Fibonacci(i)).start();
        }
    }
}
