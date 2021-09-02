package com.epam.rd.java.basic.practice6.part4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {
    private Range range;
    private Range rangeReverse;
    private StringBuilder actual;

    @Before
    public void setUp() {
        range = new Range(1, 10);
        rangeReverse  = new Range(1,10,true);
        actual = new StringBuilder();
    }

    @Test
    public void shouldPrintNotReverse() {
        for (Integer el : range) {
            actual.append(el)
                    .append(" ");
        }
        Assert.assertEquals("1 2 3 4 5 6 7 8 9 10",actual.toString().trim());
    }

    @Test
    public void shouldPrintReverse() {
        for (Integer el : rangeReverse) {
            actual.append(el)
                    .append(" ");
        }
        Assert.assertEquals("10 9 8 7 6 5 4 3 2 1",actual.toString().trim());
    }

}
