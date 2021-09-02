package com.epam.rd.java.basic.practice6.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordContainer {
    private final List<Word> listWords;

    public WordContainer() {
        this.listWords = new ArrayList<>();
    }

    public List<Word> getListWithWords() {
        return this.listWords;
    }

    public void add(Word obj) {
        for (Word w : listWords) {
            if (w.equals(obj)) {
                w.setFrequency(w.getFrequency() + 1);
                return;
            }
        }
        listWords.add(obj);
    }

    public void fillCustomContainer() {
        String[] data = readConsole();

        for (String s : data) {
            add(new Word(s));
        }

        sortArrayByDuplicateCounts();
    }

    public void sortArrayByDuplicateCounts() {
        getListWithWords().sort(Word::compareTo);
    }

    public String output() {
        StringBuilder sb = new StringBuilder();

        listWords.forEach(s->sb.append(s)
                .append(System.lineSeparator()));

        return sb.toString().trim();
    }

    public String[] readConsole() {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in, System.getProperty("file.encoding"));
        String text;
        while (sc.hasNext()) {
            text = sc.next();
            if (text.equals("stop")) {
                break;
            }
            sb.append(text)
                    .append(System.lineSeparator());
        }
        String tmp = sb.toString().replaceAll("\n|\r\n", " ");
        return tmp.split(" ");
    }

    public static void main(String[] args) {
        WordContainer container = new WordContainer();

        container.fillCustomContainer();
        System.out.println(container.output());
    }
}
