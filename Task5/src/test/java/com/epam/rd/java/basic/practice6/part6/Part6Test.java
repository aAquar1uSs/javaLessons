package com.epam.rd.java.basic.practice6.part6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Part6Test {

    private ByteArrayOutputStream outputConsole = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputConsole));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void shouldExecuteFrequencyTask_withFullOptions() {
        Part6.main(new String[]{"--input", "part6.txt", "--task",
                "frequency"});

        String expected = "whale ==> 3" + System.lineSeparator() +
                "cheetah ==> 4" + System.lineSeparator() +
                "bison ==> 3" + System.lineSeparator();

        Assert.assertEquals(expected,outputConsole.toString());

    }

    @Test
    public void shouldExecuteFrequencyTask_withMixedOptions() {
        Part6.main(new String[]{"-i", "part6.txt", "--task",
                "frequency"});

        String expected = "whale ==> 3" + System.lineSeparator() +
                "cheetah ==> 4" + System.lineSeparator() +
                "bison ==> 3" + System.lineSeparator();

        Assert.assertEquals(expected,outputConsole.toString());
    }

    @Test
    public void shouldExecuteFrequencyTask_withShortOptions() {
        Part6.main(new String[]{"-i", "part6.txt", "-t",
                "frequency"});

        String expected = "whale ==> 3" + System.lineSeparator() +
                "cheetah ==> 4" + System.lineSeparator() +
                "bison ==> 3" + System.lineSeparator();

        Assert.assertEquals(expected,outputConsole.toString());
    }

    @Test
    public void shouldExecuteLengthTask_withFullOptions() {
        Part6.main(new String[] {"--input", "part6.txt", "--task", "length"});

        String expected = "chimpanzee ==> 10" + System.lineSeparator() +
                "mongoose ==> 8" + System.lineSeparator() +
                "tortoise ==> 8" + System.lineSeparator();

        Assert.assertEquals(expected,outputConsole.toString());
    }

    @Test
    public void shouldExecuteLengthTask_withMixedOptions() {
        Part6.main(new String[] {"-i", "part6.txt", "--task", "length"});

        String expected = "chimpanzee ==> 10" + System.lineSeparator() +
                "mongoose ==> 8" + System.lineSeparator() +
                "tortoise ==> 8" + System.lineSeparator();

        Assert.assertEquals(expected,outputConsole.toString());
    }

    @Test
    public void shouldExecuteLengthTask_withShortOptions() {
        Part6.main(new String[] {"-i", "part6.txt", "-t", "length"});

        String expected = "chimpanzee ==> 10" + System.lineSeparator() +
                "mongoose ==> 8" + System.lineSeparator() +
                "tortoise ==> 8" + System.lineSeparator();

        Assert.assertEquals(expected,outputConsole.toString());
    }


    @Test
    public void shouldExecuteDuplicatesTask_withMixedOptions() {
        Part6.main(new String[] {"-i", "part6.txt", "--task", "duplicates"});

        String expected = "RAUGAJ" + System.lineSeparator() +
                "NOSIB" + System.lineSeparator() +
                "ELAHW"+ System.lineSeparator();

        Assert.assertEquals(expected,outputConsole.toString());
    }

    @Test
    public void shouldExecuteDuplicatesTask_withShortOptions() {
        Part6.main(new String[] {"-i", "part6.txt", "-t", "duplicates"});

        String expected = "RAUGAJ" + System.lineSeparator() +
                "NOSIB" + System.lineSeparator() +
                "ELAHW"+ System.lineSeparator();

        Assert.assertEquals(expected,outputConsole.toString());
    }

    @Test
    public void shouldExecuteDuplicatesTask_withFullOptions() {
        Part6.main(new String[] {"--input", "part6.txt", "--task", "duplicates"});

        String expected = "RAUGAJ" + System.lineSeparator() +
                "NOSIB" + System.lineSeparator() +
                "ELAHW"+ System.lineSeparator();

        Assert.assertEquals(expected,outputConsole.toString());
    }

    @Test
    public void shouldReturnSortedDataByLength() {
        Part62 p62 = new Part62();
        Word w1 = new Word("Mamont",6,1);
        Word w2 = new Word("dadadaada",9,2);
        Word w3 = new Word("ygy",3,3);
        p62.add(w1);
        p62.add(w2);
        p62.add(w3);

        p62.lengthWords();

        String expected = "dadadaada ==> 9" + System.lineSeparator()
                + "Mamont ==> 6" + System.lineSeparator()
                +"ygy ==> 3" + System.lineSeparator();

        Assert.assertEquals(expected,outputConsole.toString());
        System.setOut(System.out);
    }
}
