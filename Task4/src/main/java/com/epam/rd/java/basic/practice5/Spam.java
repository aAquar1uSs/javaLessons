package com.epam.rd.java.basic.practice5;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Spam {

    private Thread[] threads;
    private String[] messages;
    private int[] delays;

    public Spam(final String[] messages, final int[] delays) {
        this.delays = delays;
        this.messages = messages;
        this.initThreads();
    }

    public static void main(final String[] args) {

        String[] messages = new String[]{"@@@", "bbbbbbb"};
        int[] times = new int[]{333, 222};

        Spam spam = new Spam(messages, times);
        spam.start();
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().isEmpty()) {
            spam.stop();
        }
    }

    private void initThreads() {
        this.threads = new Thread[messages.length];
        for (int i = 0; i < messages.length; i++) {
            threads[i] = new Worker(messages[i], delays[i]);
        }
    }

    public void start() {
        for (int i = 0; i < messages.length; i++) {
            threads[i].start();
        }
    }

    public void stop() {
        for (Thread thread : threads) {
            Worker worker = (Worker) thread;
            worker.setThreadState(true);
            try {
                thread.join();
            } catch (InterruptedException e) {
                Logger.getLogger(Spam.class.getName()).log(Level.WARNING, e.getMessage());
                Thread.currentThread().interrupt();
            }
            thread.interrupt();
        }
    }

    private static class Worker extends Thread {
        String data;
        int timeDelay;
        boolean isStoped;

        public Worker(String msg, int time) {
            this.data = msg;
            this.timeDelay = time;
            this.isStoped = false;
        }

        public boolean getStateThread() {
            return isStoped;
        }

        public void setThreadState(boolean f) {
            this.isStoped = f;
        }

        @Override
        public void run() {
            while (!getStateThread()) {
                System.out.println(data);
                try {
                    sleep(timeDelay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

        }
    }

}
