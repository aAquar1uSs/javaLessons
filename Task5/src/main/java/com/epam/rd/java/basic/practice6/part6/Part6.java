package com.epam.rd.java.basic.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    private String data;
    private final Part61 p61;
    private final Part62 p62;
    private final Part63 p63;
    private static final String ENCODING = "windows-1251";
    private static final String REGEX = "\\w+";

    public Part6() {
        this.p61 = new Part61();
        this.p62 = new Part62();
        this.p63 = new Part63();
    }

    public void userInputHandler(String func, String fileName, String task, String operation) {
        readData(fileName);
        if (func.equals("--input") || func.equals("-i") || task.equals("--task") || func.equals("-t")) {
            switch (operation) {
                case "frequency":
                    p61.fillList(getSplitData(data));
                    p61.frequency();
                    return;
                case "length":
                    p62.fillList(getSplitData(data));
                    p62.lengthWords();
                    return;

                case "duplicates":
                    p63.fillList(getSplitData(data));
                    p63.duplicate();
                    return;
                default:
                    System.err.println("Wrong operation!");
            }
        } else
            System.err.println("Wrong operation!");

    }

    public void readData(String fileName) {
        try {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(new File(fileName), ENCODING);
            while (sc.hasNext()) {
                sb.append(sc.next())
                        .append(" ");
            }
            data = sb.toString();
            sc.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger(Part61.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public String[] getSplitData(String data) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            sb.append(matcher.group())
                    .append(" ");
        }
        return sb.toString().split(" ");
    }

    public static void main(String[] args) {
        Part6 part6 = new Part6();
        part6.userInputHandler(args[0], args[1], args[2], args[3]);
    }

}

class Word implements Comparable<Word> {

    private final String data;
    private final int position;
    private int frequency;
    private final int lengthWord;

    public Word(String data,int lengthWord, int position) {
        this.data = data;
        this.position = position;
        this.frequency = 1;
        this.lengthWord = lengthWord;
    }

    public String getData() {
        return data;
    }

    public int getPosition() {
        return position;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int freq) {
        this.frequency = freq;
    }

    public int getLengthWord() {
        return lengthWord;
    }

    public int sortInLengthOrder(Word o) {
        int objLength = this.getLengthWord();
        int obj2Length = o.getLengthWord();
        if (objLength == obj2Length) {
            int object1Pos = this.getPosition();
            int object2Pos = o.getPosition();
            return (object1Pos - object2Pos);
        }
        return -(objLength - obj2Length);
    }

    public int sortAlphabeticalInReverseOrder(Word o2) {
        return o2.getData().compareTo(this.getData());
    }

    @Override
    public int compareTo(Word o2) {
        int objFreq = this.getFrequency();
        int obj2Freq = o2.getFrequency();
        if (objFreq == obj2Freq) {
            int object1Pos = this.getPosition();
            int object2Pos = o2.getPosition();
            return (object1Pos - object2Pos);
        }
        return -(objFreq - obj2Freq);
    }

    @Override
    public boolean equals(Object obj) { //NOSONAR
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Word word = (Word) obj;

        return Objects.equals(this.getData(), word.getData());
    }

    @Override
    public String toString () {

        return this.getData().trim();
    }
}
