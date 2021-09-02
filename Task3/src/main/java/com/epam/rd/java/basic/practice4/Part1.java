package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final String REGEX_FOR_FIND_WORDS = "(\\b\\S+\\b.*)";
    private static final String FILE_NAME = "part1.txt";
    private static final Logger LOGGER = Logger.getLogger(Part1.class.getName());
    private static final String ENCODING = "windows-1251";

    public static void main(String[] args) {
        System.out.println(converterString(readFromFile()));
    }

    public static String readFromFile() {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME), ENCODING);
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

    public static String converterString(String data) {
        StringBuilder formattedString = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX_FOR_FIND_WORDS, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String[] temp = splitStrings(matcher.group());
            for (String s : temp) {
                if (isWordMoreThanFour(s)) {
                    formattedString.append(s.substring(2))
                            .append(' ');
                } else {
                    formattedString.append(s)
                            .append(' ');
                }
            }
            formattedString.setLength(formattedString.length() - 1);
            formattedString.append(System.lineSeparator());
        }
        return formattedString.toString().trim();
    }

    public static String[] splitStrings(String data) {
        return data.split("\\s");
    }

    public static boolean isWordMoreThanFour(String str) {
        return str.length() > 3;
    }

}
