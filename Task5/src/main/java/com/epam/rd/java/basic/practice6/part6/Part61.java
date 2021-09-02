package com.epam.rd.java.basic.practice6.part6;

import java.util.*;


public class Part61 {

    private final List<Word> listWords = new LinkedList<>();

    public void fillList(String[] splitData) {
        for (int i = 0; i < splitData.length; i++) {
            add(new Word(splitData[i], splitData[i].length(), i));
        }
    }

    public void add(Word word) {
        for (Word w : listWords) {
            if (w.equals(word)) {
                w.setFrequency(w.getFrequency() + 1);
                return;
            }
        }
        listWords.add(word);
    }

    public void frequency() {
        sortList();

        List<Word> resultList = new LinkedList<>();
        int count = 0;
        for (Word w : listWords) {
            if (count < 3) {
                resultList.add(w);
            } else {
                break;
            }
            count++;
        }

        resultList.sort(Word::sortAlphabeticalInReverseOrder);
        output(resultList);

    }

    public void sortList() {
        listWords.sort(Word::compareTo);
    }

    public void output(List<Word> list) {
        for (Word w : list) {
            System.out.println(w.getData() + " ==> " + w.getFrequency());
        }
    }

}


