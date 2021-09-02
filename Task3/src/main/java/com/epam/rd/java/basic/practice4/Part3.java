package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final Logger LOGGER = Logger.getLogger(Part3.class.getName());
    private static final String FILE_NAME = "part3.txt";
    private static final String FILE_DATA = readFromFile(FILE_NAME);
    private static final String ENCODING = "windows-1251";
    private static final String REGEX_FOR_DOUBLE_NUMBERS = "(?m)(\\.\\d\\d|\\d+\\.\\d+|\\d+\\.)";
    private static final String REGEX_FOR_INT_NUMBERS = "(?<![-.])\\b[0-9]+\\b(?!\\.[0-9])";
    private static final String REGEX_FOR_STRING = "(?U)(?m)([a-zA-zа-я]\\w{1,})";
    private static final String REGEX_FOR_CHAR = "(?m)(?U)(\\b\\w\\s)";


    public static void main(String[] args) {
        userChoiceHandler();
    }

    public static void userChoiceHandler() {
        Scanner scannerIn = new Scanner(System.in);

        while (scannerIn.hasNextLine()) {
            String typeData = scannerIn.nextLine();
            switch (typeData) {
                case "String":
                    System.out.println(getStringValues());
                    break;
                case "int":
                    System.out.println(getIntValues());
                    break;
                case "char":
                    System.out.println(getCharValue());
                    break;
                case "double":
                    System.out.println(getDoubleValues());
                    break;
                case "stop":
                    return;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
        scannerIn.close();
    }

    private static String getCharValue() {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX_FOR_CHAR);
        Matcher matcher = pattern.matcher(FILE_DATA);
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        return sb.toString();
    }

    public static String getIntValues() {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX_FOR_INT_NUMBERS);
        Matcher matcher = pattern.matcher(FILE_DATA);
        while (matcher.find()) {
            sb.append(matcher.group())
                    .append(' ');
        }
        return sb.toString();
    }

    public static String getDoubleValues() {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX_FOR_DOUBLE_NUMBERS);
        Matcher matcher = pattern.matcher(FILE_DATA);
        while (matcher.find()) {
            sb.append(matcher.group(1))
                    .append(' ');
        }
        return sb.toString();
    }

    public static String getStringValues() {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX_FOR_STRING);
        Matcher matcher = pattern.matcher(FILE_DATA);
        while (matcher.find()) {
            sb.append(matcher.group())
                    .append(' ');
        }

        return sb.toString();
    }

    public static String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), ENCODING);
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return stringBuilder.toString().trim();
        } catch (IOException ex) {
            LOGGER.log(Level.INFO,"File not found");
        }
        return stringBuilder.toString();
    }

}
