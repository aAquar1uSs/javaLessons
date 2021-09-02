package com.epam.rd.java.basic.practice6.part5;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TreeTest {
    private Tree<Integer> tree;
    private ByteArrayOutputStream output = new ByteArrayOutputStream();


    @Before
    public void setUp() {
        tree = new Tree<>();
        System.setOut(new PrintStream(output));
    }


    @Test
    public void shouldAddElementToTree() {
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(5);
        tree.print();

        Assert.assertEquals("  1" + System.lineSeparator()
                + "    2" + System.lineSeparator() + "3"
                + System.lineSeparator() +
                "  5" + System.lineSeparator()
                ,output.toString());
    }

    @Test
    public void shouldReturnTrueIfElementIsDeleted() {
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(5);

        Assert.assertTrue(tree.remove(2));
    }

    @Test
    public void shouldDeleteElement() {
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(5);
        tree.remove(2);
        tree.print();

        Assert.assertEquals("  1" + System.lineSeparator()
                         + "3" + System.lineSeparator() +
                        "  5" + System.lineSeparator()
                ,output.toString());
    }

    @Test
    public void shouldReturnFalse_IfRemoveUnexistedElement() {
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(5);

        Assert.assertFalse(tree.remove(8));
    }

    @Test
    public void shouldNotAddRepeatedElement() {
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(5);
        tree.add(3);

        tree.print();

        Assert.assertEquals("  1" + System.lineSeparator()
                        + "    2" + System.lineSeparator() + "3"
                        + System.lineSeparator() +
                        "  5" + System.lineSeparator()
                ,output.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
