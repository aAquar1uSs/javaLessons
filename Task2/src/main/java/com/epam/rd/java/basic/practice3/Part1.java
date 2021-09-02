package com.epam.rd.java.basic.practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part1 {

    private static final String REGEX_TEXT_PATTERN = "^(\\S+);(\\S+)\\s(\\S+);((\\S+)@(\\S+))$";
    private static final String REGEX_PATTERN_FOR_HEAD = "(\\S+;\\S+;\\S+)";
    private static final String REGEX_PATTERN_FOR_DOMAIN = "@(\\S+)";

    public static void main(String[] args) {
        String parseData = Util.getInput("part1.txt");
        final String divide = "--------------------------------";

        //Convert 1
        System.out.println(convert1(parseData));
        System.out.println(divide);
        //Convert 2
        System.out.println(convert2(parseData));
        System.out.println(divide);
        //Convert 3
        System.out.println(convert3(parseData));
        System.out.println(divide);
        //Convert 4
        System.out.println(convert4(parseData));
        System.out.println(divide);
    }


    public static String convert1(String input) {
        if (input == null || input.equals("")) {
            return null;
        }
        String[] stringArray = divideString(input);
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX_TEXT_PATTERN);

        for (String str : stringArray) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                stringBuilder.append(matcher.group(1))
                        .append(": ")
                        .append(matcher.group(4))
                        .append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }

    public static String convert2(String input) {
        if (input == null || input.equals("")) {
            return null;
        }
        String[] stringArray = divideString(input);
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX_TEXT_PATTERN);

        for (String str : stringArray) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                stringBuilder.append(matcher.group(3))
                        .append(" ")
                        .append(matcher.group(2))
                        .append(" (email: ")
                        .append(matcher.group(4))
                        .append(")")
                        .append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }

    public static String convert3(String input) {
        if (input == null || input.equals("")) {
            return null;
        }
        String[] stringArray = divideString(input);
        String[] emailDomains = searchEmailDomains(stringArray);
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX_TEXT_PATTERN);

        for (int j = 0; j < emailDomains.length; j++) {
            String domain = emailDomains[j];
            emailDomains[j] += " ==> ";
            for (String str : stringArray) {
                Matcher matcher = pattern.matcher(str);
                if (matcher.find() && (matcher.group(6).equals(domain))) {
                    emailDomains[j] += matcher.group(1) + ", ";
                }
            }
            stringBuilder.append(emailDomains[j]);
            stringBuilder.setLength(stringBuilder.length() - 2);
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public static String[] searchEmailDomains(String[] data) {
        String[] emailDomains = new String[data.length - 1];
        int i = 0;
        Pattern patternForDomain = Pattern.compile(REGEX_PATTERN_FOR_DOMAIN);

        for (String str : data) {
            Matcher matcher = patternForDomain.matcher(str);
            if (matcher.find() && (i < emailDomains.length)) {
                emailDomains[i] = matcher.group(1);
                i++;
            }
        }

        return removeRepeatedEmail(emailDomains);
    }

    public static String[] removeRepeatedEmail(String[] emailDomains) {
        int sizeArrayEmails = emailDomains.length;

        int unique = 0;
        for (int i = 0; i < sizeArrayEmails; i++) {
            for (int j = i + 1; j < sizeArrayEmails; j++) {
                if (emailDomains[i].equals(emailDomains[j])) {
                    unique++;
                }
            }
        }
        String[] result = new String[unique];
        int k = 0;
        for (int i = 0; i < sizeArrayEmails; i++) {
            for (int j = i + 1; j < sizeArrayEmails; j++) {
                if (emailDomains[i].equals(emailDomains[j]) && k < result.length) {
                    result[k++] = emailDomains[i];
                }
            }
        }

        return result;
    }

    public static String convert4(String input) {
        if (input == null || input.equals("")) {
            return null;
        }
        SecureRandom random = new SecureRandom();
        String[] stringArray = divideString(input);
        StringBuilder stringBuilder = new StringBuilder();
        Pattern patternForInfo = Pattern.compile(REGEX_TEXT_PATTERN);
        Pattern patternForHead = Pattern.compile(REGEX_PATTERN_FOR_HEAD);

        for (String str : stringArray) {
            Matcher matcherInfo = patternForInfo.matcher(str);
            Matcher matcherPassword = patternForHead.matcher(str);
            if (matcherPassword.find()) {
                stringBuilder.append(matcherPassword.group(1))
                        .append(";Password")
                        .append(System.lineSeparator());
            }
            if (matcherInfo.find()) {
                stringBuilder.append(matcherInfo.group(1))
                        .append(';')
                        .append(matcherInfo.group(2))
                        .append(' ')
                        .append(matcherInfo.group(3))
                        .append(';')
                        .append(matcherInfo.group(4))
                        .append(';')
                        .append(String.format("%04d", random.nextInt(1001)))
                        .append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }

    public static String[] divideString(String data) {
        return data.split(System.lineSeparator());
    }


}