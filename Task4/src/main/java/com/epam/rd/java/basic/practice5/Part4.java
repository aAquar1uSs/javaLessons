package com.epam.rd.java.basic.practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {
    private static final String ENCODING = "CP1251";
    private static int countLine;
    private static final String REGEX_FOR_NUMBERS = "\\d+";
    private static int maxValueInMatrix  = 0;
    private static Thread[] workers;

    public static void main(final String[] args) {

        String data = null;
        try {
            data = getInput("part4.txt");
        } catch (FileNotFoundException e) {
            Logger.getLogger(Part4.class.getName()).log(Level.WARNING, "File not found!");
        }

        String[] splitData = data.split(System.lineSeparator());

        //Multithreaded implementation----------------------------------------------------------------------------------
        initThreads(splitData);

        long startTime = System.currentTimeMillis();

        startThreads();
        stopThreads();

        System.out.println(maxValueInMatrix);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        //Single-threaded implementation--------------------------------------------------------------------------------
        maxValueInMatrix = 0;
        long startTime2 = System.currentTimeMillis();

        for(int i = 0; i < countLine; i++) {
                foundMaxValue(splitData[i]);
        }
        System.out.println(maxValueInMatrix);
        long endTime2 = System.currentTimeMillis();
        System.out.println(endTime2 - startTime2);
    }

    public static void initThreads(String[] data) {
        workers = new Thread[countLine];
        for(int i = 0; i < countLine;i++) {
            workers[i] = new Worker(data[i]);
        }
    }

    public static void startThreads() {
        for(Thread thread : workers) {
            thread.start();
        }
    }

    public static void stopThreads() {
        for(Thread thread : workers) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Logger.getLogger(Part4.class.getName()).log(Level.WARNING, e.getMessage());
                thread.interrupt();
            }
        }
    }

    public static String getInput(String fileName) throws FileNotFoundException {
        int str = 0;
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(fileName), ENCODING);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
            str++;
            sb.append(System.lineSeparator());
        }
        scanner.close();
        countLine = str;
        return sb.toString().trim();
    }

    public static void foundMaxValue(String str) {

        Pattern pattern = Pattern.compile(REGEX_FOR_NUMBERS);
        Matcher matcher = pattern.matcher(str);

        int max = Integer.MIN_VALUE;
        while(matcher.find()) {
            int currentNum = Integer.parseInt(matcher.group());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Logger.getLogger(Part4.class.getName()).log(Level.WARNING, e.getMessage());
                Thread.currentThread().interrupt();
            }
            if(currentNum > max) {
                max = currentNum;
            }
        }
        if(maxValueInMatrix < max) {
            maxValueInMatrix = max;
        }
    }

    static class Worker extends Thread {
        String line;
        Worker(String str) {
            this.line = str;
        }

        @Override
        public void run() {
            synchronized (this) {
                foundMaxValue(line);
            }
        }
    }

}
