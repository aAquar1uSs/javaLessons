package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {

    private static final String REGEX_PATTERN_FOR_ROMAN_NUMERAL = "M|CM|D|CD|C|XC|L|XL|X|IX|V|IV|I";
    private static final String[] ROMAN_NUMBERS = {"I", "IV", "V", "IX", "X", "XL", "L",
            "XC", "C", "CD", "D", "CM", "M"};
    private static final  int[] DEC_NUMBERS = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i + " ==> " + decimal2Roman(i) + " ==> " + roman2Decimal(decimal2Roman(i)));
        }
    }

    public static String decimal2Roman(int dec) {
        if(dec == 0 ) {
            return null;
        }
        StringBuilder romanNumeral = new StringBuilder();

        int indexDecNumArray = DEC_NUMBERS.length - 1;
        while (dec > 0) {
            int div = dec / DEC_NUMBERS[indexDecNumArray];
            dec = dec % DEC_NUMBERS[indexDecNumArray];
            while (div > 0) {
                div--;
                romanNumeral.append(ROMAN_NUMBERS[indexDecNumArray]);
            }
            indexDecNumArray--;
        }
        return romanNumeral.toString();
    }

    public static int roman2Decimal(String roman) {
        if(roman == null || roman.equals("")) {
            return -1;
        }

        int decNumeral = 0;
        Matcher matcher = Pattern.compile(REGEX_PATTERN_FOR_ROMAN_NUMERAL).matcher(roman);

        while (matcher.find()) {
            for(int i = 0; i < ROMAN_NUMBERS.length; i++) {
                if(ROMAN_NUMBERS[i].equals(matcher.group(0))) {
                    decNumeral += DEC_NUMBERS[i];
                }
            }
        }

       return decNumeral;
    }
}
