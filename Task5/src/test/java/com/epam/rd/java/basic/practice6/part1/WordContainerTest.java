package com.epam.rd.java.basic.practice6.part1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class WordContainerTest {
    private WordContainer container;
    private Word w1, w2, w3;
    private String actual;
    private String expected;


    @Before
    public void setUp() {
        container = new WordContainer();
        w1 = new Word("B");
        w2 = new Word("A");
        w3 = new Word("C");
    }

    @Test
    public void addElementsToArrayList_TEST() {
        container.add(w1);
        container.add(w2);
        container.add(w3);

        int actual = container.getListWithWords().size();
        int expected = 3;

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldPrintCorrectResultWithOutSort() {
        container.add(w1);
        container.add(w2);
        container.add(w3);
        actual = container.output();

        expected = "B : 1" + System.lineSeparator() +
                "A : 1" + System.lineSeparator() +
                "C : 1";

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldPrintInformation_NOT_NULL() {
        container.fillCustomContainer();
        expected = container.output();

        Assert.assertNotNull(expected);
    }

    @Test
    public void shouldSortByDuplicateCounts() {
        container.add(w1);
        container.add(w2);
        container.add(w1);
        container.add(w1);
        container.add(w2);
        container.add(w3);

        container.sortArrayByDuplicateCounts();
        actual = container.output();

        String expected = "B : 3" + System.lineSeparator() +
                "A : 2" + System.lineSeparator() +
                "C : 1";

        Assert.assertEquals(expected,actual);

    }
}
