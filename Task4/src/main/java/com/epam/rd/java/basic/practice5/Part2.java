package com.epam.rd.java.basic.practice5;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part2 {

    public static void main(final String[] args) {
        MyInputStream myInputStream = new MyInputStream();
        System.setIn(myInputStream);

        Thread t = new Thread(() -> Spam.main(null));
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            Logger.getLogger(Part2.class.getName()).log(Level.WARNING, e.getMessage());
            Thread.currentThread().interrupt();
        }

        System.setIn(System.in);
    }

    static class MyInputStream extends InputStream { //NOSONAR

        private static char[] data = System.lineSeparator().toCharArray();
        private int countBytes = 0;

        @Override
        public int read() {

            if (countBytes == 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Logger.getLogger(Part2.class.getName()).log(Level.WARNING, e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }

            if (countBytes < data.length) {
                return data[countBytes++];
            }

            return -1;
        }

    }
}

