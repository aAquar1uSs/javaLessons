package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String REGEX_LOWER_CASE = "^[a-z]{3,}";
    private static final String REGEX_UPPER_CASE = "^[A-Z][a-z]{3,}+";

    public static void main(String[] args) {
        String parseData = Util.getInput("part3.txt");

        System.out.println(convert(parseData));
    }

    public static String convert(String input) {
        if (input == null || input.equals("")) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        String[] splitStrings = input.split("\\b");

        Pattern patternLowerCase = Pattern.compile(REGEX_LOWER_CASE);
        Pattern patternUpperCase = Pattern.compile(REGEX_UPPER_CASE);

        for (String str : splitStrings) {
            Matcher matcherLowerCase = patternLowerCase.matcher(str);
            Matcher matcherUpperCase = patternUpperCase.matcher(str);
            char[] word = str.toCharArray();
            if (matcherLowerCase.find()) {
                char temp = Character.toUpperCase(word[0]);
                word[0] = temp;
                result.append(new String(word));
            } else if (matcherUpperCase.find()) {
                char temp = Character.toLowerCase(word[0]);
                word[0] = temp;
                result.append(new String(word));
            } else {
                result.append(new String(word));
            }
        }
        return result.toString();
    }
}
