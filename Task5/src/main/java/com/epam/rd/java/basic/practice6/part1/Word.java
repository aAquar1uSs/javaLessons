package com.epam.rd.java.basic.practice6.part1;


import java.util.Objects;

public class Word implements Comparable<Word> {

    private String content;

    private int frequency;

    public Word(String obj) {
        this.content = obj;
        this.frequency = 1;
    }

    public String getContent() {
        return content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int number) {
        this.frequency = number;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getContent())
                .append(" : ")
                .append(getFrequency());
        return sb.toString();
    }


    @Override
    public int compareTo(Word o) {
        if (this.getFrequency() > o.getFrequency()) {
            return -1;
        }

        if (this.getFrequency() < o.getFrequency()) {
            return 1;
        }

        return 0;
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

        return Objects.equals(this.content, word.content);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + getFrequency();

        return result;
    }
}

