package com.epam.rd.java.basic.practice3;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static final String REGEX_PATTERN_FOR_SPLIT = "[\\s,'.-]+";

    public static void main(String[] args) {
        String parseData = Util.getInput("part2.txt");

        System.out.println(convert(parseData));
    }

    public static String convert(String input) {
        if (input == null || input.equals("")) {
            return null;
        }
        String[] splitStrings = input.split(REGEX_PATTERN_FOR_SPLIT);

        int min = findMinLengthWord(splitStrings);
        int max = findMaxLengthWord(splitStrings);

        final String REGEX_PATTERN_FOR_MAX_WORD = "\\b\\w{" + max + "}\\b";
        final String REGEX_PATTERN_FOR_MIN_WORD = "\\b\\w{" + min + "}\\b";

        Pattern patternMinLength = Pattern.compile(REGEX_PATTERN_FOR_MIN_WORD);
        Pattern patternMaxLength = Pattern.compile(REGEX_PATTERN_FOR_MAX_WORD);

        StringBuilder strWithMinWord = new StringBuilder();
        StringBuilder strWithMaxWord = new StringBuilder();

        for (String str : splitStrings) {
            Matcher matcherMinWords = patternMinLength.matcher(str);
            Matcher matcherMaxWords = patternMaxLength.matcher(str);
            if (matcherMinWords.find() && !strWithMinWord.toString().contains(matcherMinWords.group())) {
                strWithMinWord.append(matcherMinWords.group())
                        .append(", ");
            }
            if (matcherMaxWords.find() && !strWithMaxWord.toString().contains(matcherMaxWords.group())) {
                strWithMaxWord.append(matcherMaxWords.group())
                        .append(", ");
            }
        }

        strWithMinWord.setLength(strWithMinWord.length() - 2);
        strWithMaxWord.setLength(strWithMaxWord.length() - 2);


        return "Min: " +
                strWithMinWord +
                System.lineSeparator() +
                "Max: " +
                strWithMaxWord;
    }

    public static int findMinLengthWord(String[] splitStr) {
        int min = splitStr[0].length();
        for (String str : splitStr) {
            if (min > str.length()) {
                min = str.length();
            }
        }
        return min;
    }

    public static int findMaxLengthWord(String[] splitStr) {
        int max = splitStr[0].length();
        for (String str : splitStr) {
            if (max < str.length()) {
                max = str.length();
            }
        }
        return max;
    }
}
