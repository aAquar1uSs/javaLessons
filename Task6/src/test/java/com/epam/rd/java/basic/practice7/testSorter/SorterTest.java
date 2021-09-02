package com.epam.rd.java.basic.practice7.testSorter;


import com.epam.rd.java.basic.practice7.entity.Flowers;
import com.epam.rd.java.basic.practice7.parsers.SaxParser;
import com.epam.rd.java.basic.practice7.util.Sorter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SorterTest {
    private Flowers flowers;

    @Before
    public void setUp() {
        SaxParser saxParser = new SaxParser("input.xml");
        saxParser.parseXmlFileBySAX();
        flowers = saxParser.getFlowersList();
    }

    @Test
    public void shouldSortByName() {
        Sorter.sortByName(flowers);
        String expected = "Bambusa";
        String actual = flowers.getFlowerList().get(0).getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByOrigin() {
        Sorter.sortByOrigin(flowers);
        String expected = "China";
        String actual = flowers.getFlowerList().get(0).getOrigin();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldSortFlowersByLength() {
        Sorter.sortFlowersByFlowersLen(flowers);
        int expected = 1100;
        int actual = flowers.getFlowerList().get(0).getVisualParameters().getAveLenFlower().getValueForLenFlower();
        Assert.assertEquals(expected,actual);
    }


}
