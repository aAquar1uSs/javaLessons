package com.epam.rd.java.basic.practice5;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part5 {
    private static final String FILE_PATH = "part5.txt";
    private static final int NUMBER_THREADS = 10;

    private static RandomAccessFile file;

    public static void checkFile(String fileName) {

        try {
            Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException e) {
            Logger.getLogger(Part5.class.getName()).log(Level.WARNING, e.getMessage());
        }


    }

    public static String readFromFile() {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(FILE_PATH), "windows-1251");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            Logger.getLogger(Part5.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        return sb.toString();
    }


    public static void main(final String[] args) {

        checkFile(FILE_PATH);

        try {
            file = new RandomAccessFile(FILE_PATH, "rw");
        } catch (FileNotFoundException e) {
            Logger.getLogger(Part5.class.getName()).log(Level.WARNING, e.getMessage());
        }

        Thread[] threads = new Thread[NUMBER_THREADS];

        for (int i = 0; i < NUMBER_THREADS; i++) {
            threads[i] = new Worker(file, i);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Logger.getLogger(Part5.class.getName()).log(Level.WARNING, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        try {
            file.close();
        } catch (IOException e) {
            Logger.getLogger(Part5.class.getName()).log(Level.WARNING, e.getMessage());
        }


        System.out.println(readFromFile());

    }
}

class Worker extends Thread {
    private final RandomAccessFile accessFile;
    private long position;
    private int value;

    public Worker(RandomAccessFile f, int num) {
        this.accessFile = f;
        this.value = num;
        this.position = (long) value * (20 + System.lineSeparator().length());
    }

    @Override
    public void run() {
        synchronized (accessFile) {
            for (int i = 0; i < 20; i++) {
                try {
                    sleep(1); //NOSONAR
                    accessFile.seek(i + position);
                    accessFile.write('0' + value);
                } catch (IOException | InterruptedException e) {
                    Logger.getLogger(Part5.class.getName()).log(Level.WARNING, e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }

            try {
                accessFile.seek(position + 20);
                accessFile.write(System.lineSeparator().getBytes());
            } catch (IOException e) {
                Logger.getLogger(Part5.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
    }

}
