package com.epam.rd.java.basic.practice4;


import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
    private static final int WORDS_INDEX = 0;
    private static final int INDEX_REGION = 1;

    public static void main(String[] args) {
        userChoiceHandler();
    }

    public static void userChoiceHandler() {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String[] splitData = scanner.nextLine().split(" ");
            if(splitData[WORDS_INDEX].equals("stop")) {
                return;
            }
            System.out.println(translator(splitData));
        }
        scanner.close();
    }

    public static String translator(String[] splitData) {
        StringBuilder sb = new StringBuilder();
        Locale region = new  Locale(splitData[INDEX_REGION]);
        ResourceBundle rbNewRegion = ResourceBundle.getBundle("resources",region);
        sb.append(rbNewRegion.getString(splitData[WORDS_INDEX]));

        return sb.toString();
    }

}
