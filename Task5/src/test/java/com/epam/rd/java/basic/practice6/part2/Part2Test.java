package com.epam.rd.java.basic.practice6.part2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Part2Test {
    private Part2 part2;

    @Before
    public void setUp() {
        part2 = new Part2(50000,4);
    }

    @Test
    public void shouldFillList() {
        List<Integer> list = new LinkedList<>();
        part2.fillListOfPeople(list);

        int expectedSize = 50000;
        int actualSize = list.size();

        Assert.assertEquals(expectedSize,actualSize);
    }

    @Test
    public void shouldGetNumberOfPeople() {
        Assert.assertEquals(50000,part2.getNumberOfPeople());
    }

    @Test
    public void shouldPrintInformation_NOT_NULL() {
        List<Integer> list = new ArrayList<>();
        part2.fillListOfPeople(list);
        String expected = part2.outputList(list);

        Assert.assertNotNull(expected);
    }

    @Test
    public void shouldDeleteElementsByIndex() {
        List<Integer> list = new ArrayList<>();
        part2.fillListOfPeople(list);
        Part2.removeByIndex(list,3);
        int expectedSize = 1;
        int actualSize = list.size();

        Assert.assertEquals(expectedSize,actualSize);
    }

    @Test
    public void shouldDeleteIndexByIterator() {
        List<Integer> list = new ArrayList<>();
        part2.fillListOfPeople(list);
        Part2.removeByIterator(list,3);
        int expectedSize = 1;
        int actualSize = list.size();

        Assert.assertEquals(expectedSize,actualSize);
    }



}