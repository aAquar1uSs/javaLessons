package com.epam.rd.java.basic.practice6.part6;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Part63 {
    private final List<Word> listWords = new LinkedList<>();

    public void fillList(String[] splitData) {
        for (int i = 0; i < splitData.length; i++) {
            add(new Word(splitData[i], splitData[i].length(), i));
        }
    }

    public void add(Word word) {
        listWords.add(word);
    }

    public void duplicate() {
        List<Word> duplicateList = listWords.stream().filter(word -> (listWords.stream()
                .filter(y -> y.getData().equals(word.getData())).count() > 1))
                .limit(3)
                .collect(Collectors.toList());

        duplicateList.stream()
                .map(word -> new StringBuilder(word.getData().toUpperCase()).reverse())
                .forEach(System.out::println);
    }
}

