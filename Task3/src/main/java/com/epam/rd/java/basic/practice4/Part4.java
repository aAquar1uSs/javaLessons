package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {
    private static final Logger LOGGER = Logger.getLogger(Part4.class.getName());
    private static final String PATTERN_FOR_STRING = "\\p{javaUpperCase}.*?\\.";
    private static final String FILE_NAME = "part4.txt";
    private static final String DATA = readFromFile(FILE_NAME);
    private static final String ENCODING = "windows-1251";

    private Matcher matcher;

    public Part4(Matcher matcher) {
        setMatcher(matcher);
    }

    private void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    @Override
    public Iterator<String> iterator() {
        return new IteratorImpl(getMatcher());
    }

    private static class IteratorImpl implements Iterator<String> {
        Matcher matcherIter;

        public IteratorImpl(Matcher matcher) {
            this.matcherIter = matcher;
        }

        @Override
        public boolean hasNext() {
            return matcherIter.find();
        }

        @Override
        public String next() {
            try {
                return matcherIter.group();
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static String readFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), ENCODING);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine())
                        .append(" ");
            }
            scanner.close();
            return sb.toString().trim();
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.INFO,"File not found");
        }
        return sb.toString().trim();

    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(PATTERN_FOR_STRING);
        Part4 part4 = new Part4(pattern.matcher(DATA));
        Iterator<String> it = part4.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
