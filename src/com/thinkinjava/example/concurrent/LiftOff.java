package com.thinkinjava.example.concurrent;

public class LiftOff implements Runnable {

    protected  int countDown = 3; //Default

    private static int taskCount = 0;

    private final int id = taskCount++;

    public LiftOff() {  }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "),";
    }

    /**
     * Thread.yield() 线程让步；调用此方法，可以使当前线程由Running（运行）状态进入到Runnable（就绪）状态
     * 当前线程会和其他线程再次竞争获取CPU时间片，所以调用Thread.yield()方法后，有可能是当前线程将CPU时间片让步给其他线程，
     * 也有可能是当前线程在进入就绪状态后又竞争获得了CPU时间片，再次继续运行。
     * 关于线程优先级：线程优先级是统计概念，高优先级的线程在大量执行次数中获取CPU时间片的概率高；但是在单次运行中，
     * 不一定是优先级最高的线程获得CPU时间片。
     */
    @Override
    public void run() {
      while (countDown -- > 0){
          System.out.println(status());
          Thread.yield();
      }
    }
}
