package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    private static final String REGEX_REPEAT_WORDS = "(?sU)\\b(\\w+)\\b(?=.*\\b\\1\\b)";

    public static void main(String[] args) {
        String parseData = Util.getInput("part6.txt");

        System.out.println(convert(parseData));
    }

    public static String convert(String input) {
        if(input == null || input.equals(" ")) {
            return null;
        }
        Pattern pattern = Pattern.compile(REGEX_REPEAT_WORDS, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        String[] foundRepeatedWords = new String[input.length()];
        int i = 0;
        while(matcher.find()) {
            if(i < input.length()) {
                foundRepeatedWords[i] = matcher.group();
                i++;
            }
        }
        input = input.replaceAll("\\b(?:" + String.join("|", foundRepeatedWords) + ")\\b",
                "_$0");

        return input;
    }
}
