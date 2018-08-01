package com.thinkinjava.example.concurrent;

public class MainThread {
    public static void main(String [] args){
        LiftOff launch = new LiftOff();
        //直接调用run()不会开启新的线程，还是顺序执行的。
        launch.run();
    }
}
