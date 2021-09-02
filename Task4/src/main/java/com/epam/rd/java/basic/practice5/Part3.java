package com.epam.rd.java.basic.practice5;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Part3 {

    private final CountersWork lockCounterWork = new CountersWork();

    private int counter = 0;
    private int counter2 = 0;
    private int numberOfThreads;
    private int numberOfIterations;

    private Thread[] threadsSync;
    private Thread[] threadsNotSync;

    public static void main(final String[] args) {
        Part3 part3 = new Part3(2, 1);

        part3.compare();

        part3.compareSync();
    }

    public Part3(int numberOfThreads, int numberOfIterations) {
        this.numberOfThreads = numberOfThreads;
        this.numberOfIterations = numberOfIterations;
        threadsSync = new ThreadSync[numberOfThreads];
        threadsNotSync = new ThreadNotSync[numberOfThreads];
    }

    private void createThreadsNotSync() {
        for (int i = 0; i < numberOfThreads; i++) {
            threadsNotSync[i] = new ThreadNotSync(new CountersWork());
        }
    }

    public void runThreadsNotSync() {
        for (Thread thread : threadsNotSync) {
            thread.start();
        }

        for (Thread thread : threadsNotSync) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Logger.getLogger(Part3.class.getName()).log(Level.WARNING, e.getMessage());
                thread.interrupt();
            }
        }
    }

    private void createThreadsSync() {
        for (int i = 0; i < numberOfThreads; i++) {
            threadsSync[i] = new ThreadSync();
        }
    }

    public void runThreadsSync() {
        for (Thread thread : threadsSync) {
            thread.start();
        }
        for (Thread thread : threadsSync) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Logger.getLogger(Part3.class.getName()).log(Level.WARNING, e.getMessage());
                thread.interrupt();
            }
        }
    }

    public void compare() {
        createThreadsNotSync();
        runThreadsNotSync();
    }

    public void compareSync() {
        createThreadsSync();
        runThreadsSync();
    }

    class ThreadNotSync extends Thread {

        CountersWork countersWork;

        ThreadNotSync(CountersWork cw) {
            this.countersWork = cw;
        }

        @Override
        public void run() {
            for (int i = 0; i < numberOfIterations; i++) {
                countersWork.countAndIncrement();
            }
        }
    }

    class ThreadSync extends Thread {

        @Override
        public void run() {
            synchronized (lockCounterWork) {
                for (int i = 0; i < numberOfIterations; i++) {
                    lockCounterWork.countAndIncrement();
                }
            }
        }
    }

    class CountersWork {
        public void countAndIncrement() {
            try {
                System.out.println(Thread.currentThread().getName() + " ==> " + counter + " == " + counter2
                        + " ==> " + (counter == counter2));
                counter++;
                Thread.sleep(100);
                counter2++;
            } catch (Exception e) {
                Logger.getLogger(Part3.class.getName()).log(Level.WARNING, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }


}




