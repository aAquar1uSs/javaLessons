package com.epam.rd.java.basic.practice5;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Part1 {

    public static void main(String[] args) {
        Thread t1 = new MyThread1(500);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            Logger.getLogger(Part1.class.getName()).log(Level.WARNING,"Thread has been interrupted ==> main");
            Thread.currentThread().interrupt();
        }

        Thread t2 = new Thread(new MyThread2(500));
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            Logger.getLogger(Part1.class.getName()).log(Level.WARNING,"Thread has been interrupted==> MyThread2");
            Thread.currentThread().interrupt();
        }
    }
}

class MyThread1 extends Thread {
    private int delay;

    public MyThread1(int delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        int count = 0;
        int size = 4;
        while(!Thread.currentThread().isInterrupted() && count < size) {
            System.out.println(this.getName());
            try {
                sleep(this.delay);
                count++;
            } catch (InterruptedException e) {
                Logger.getLogger(MyThread1.class.getName()).log(Level.WARNING,"Thread has been interrupted ==> MyThread1");
                Thread.currentThread().interrupt();
            }
        }
    }
}

class MyThread2 implements Runnable{
    private int delay;

    public MyThread2(int delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        int count = 0;
        int size = 4;
        while(!Thread.currentThread().isInterrupted() && count < size) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(this.delay);
                count++;
            } catch (InterruptedException e) {
                Logger.getLogger(MyThread2.class.getName()).log(Level.WARNING,"Thread has been interrupted ==> MyThread2");
                Thread.currentThread().interrupt();
            }
        }
    }
}