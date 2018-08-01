package com.thinkinjava.example.concurrent.excise;

public class TestRunnable {
    public static void main(String [] args){
        for(int i = 0;i<3;i++){
            new Thread(new MyRunnable()).start();
        }
        System.out.println("Waiting for MyRunnable!");
    }
}
