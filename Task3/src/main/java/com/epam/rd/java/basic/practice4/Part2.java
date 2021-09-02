package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part2 {

    private static final int SIZE_VALUES = 10;
    private static final String FILE_NAME = "part2.txt";
    private static final String FILE_SORTED = "part2_sorted.txt";
    private static final String ENCODING = "windows-1251";
    private static final Logger LOGGER = Logger.getLogger(Part2.class.getName());

    public static void main(String[] args) {

        int[] randomValues = getRandomValues();
        writeToFile(FILE_NAME, randomValues);
        System.out.print("input ==> " + readFromFile(FILE_NAME).trim() + System.lineSeparator());
        String readData = readFromFile(FILE_NAME);
        writeToFile(FILE_SORTED, arraySort(readData));
        System.out.print("output ==> " + readFromFile(FILE_SORTED).trim() + System.lineSeparator());
    }

    public static void writeToFile(String fileName, int[] values) {
        File file = new File(fileName);

        try (final FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < SIZE_VALUES; i++) {
                final String stringWithNumbers = Integer.toString(values[i]);
                writer.write(stringWithNumbers);
                writer.write(' ');
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String readFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), ENCODING);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            LOGGER.log(Level.INFO,"File not found");
        }
        return sb.toString();
    }

    public static int[] getRandomValues() {
        SecureRandom secureRandom = new SecureRandom();
        int[] arrayNumbers = new int[SIZE_VALUES];

        for (int i = 0; i < SIZE_VALUES; i++) {
            arrayNumbers[i] = secureRandom.nextInt(50);
        }
        return arrayNumbers;
    }

    public static int[] arraySort(String data) {
        int[] numberSorted = convertToInt(data);

        for (int i = 0; i < SIZE_VALUES; i++) {
            for (int j = SIZE_VALUES - 1; j > i; j--) {
                if (numberSorted[j - 1] > numberSorted[j]) {
                    int temp = numberSorted[j - 1];
                    numberSorted[j - 1] = numberSorted[j];
                    numberSorted[j] = temp;
                }
            }
        }
        return numberSorted;
    }

    public static int[] convertToInt(String data) {
        String[] splitData = data.split(" ");
        int[] numbers = new int[SIZE_VALUES];
        for (int i = 0; i < SIZE_VALUES; i++) {
            numbers[i] = Integer.parseInt(splitData[i]);
        }
        return numbers;
    }

}
