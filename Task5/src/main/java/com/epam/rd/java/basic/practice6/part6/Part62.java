package com.epam.rd.java.basic.practice6.part6;

import java.util.LinkedList;
import java.util.List;

public class Part62 {
    private final List<Word> listWords = new LinkedList<>();

    public void fillList(String[] splitData) {

        for (int i = 0; i < splitData.length; i++) {
            add(new Word(splitData[i], splitData[i].length(), i));
        }
    }

    public void add(Word word) {

        for (Word w : listWords) {
            if (w.equals(word)) {
                return;
            }
        }
        listWords.add(word);

    }

    public void lengthWords() {
        listWords.sort(Word::sortInLengthOrder);
        outputList();
    }


    public void outputList() {
        int coutWords = 0;
        for (Word w : listWords) {
            if (coutWords < 3) {
                System.out.println(w.getData() + " ==> " + w.getLengthWord());
            }
            coutWords++;
        }
    }
}

