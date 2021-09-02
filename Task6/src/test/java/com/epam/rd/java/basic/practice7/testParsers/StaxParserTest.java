package com.epam.rd.java.basic.practice7.testParsers;

import com.epam.rd.java.basic.practice7.util.CreateXML;
import com.epam.rd.java.basic.practice7.entity.Flowers;
import com.epam.rd.java.basic.practice7.parsers.StaXParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class StaxParserTest {
    StaXParser staXParser;
    String xmlFileName = "input.xml";
    File file;

    @Before
    public void setUp() {
        file = new File("testStax.xml");
        staXParser = new StaXParser(xmlFileName);
        staXParser.parseXmlFileBySTAX();
    }

    @Test
    public void shouldGetListOfFlowers() {
        Flowers flowers = staXParser.getFlowers();
        int expectedSizeList = 2;
        int actual = flowers.getFlowerList().size();

        Assert.assertEquals(expectedSizeList,actual);
    }

    @Test
    public void shouldSaveToXmlFile() {
        Flowers flowers = staXParser.getFlowers();
        try {
            CreateXML.saveToXML(CreateXML.getDocument(flowers),file.getName());
        } catch (ParserConfigurationException | TransformerException e) {
            Assert.fail();
        }

        if(file.exists()) {
            Assert.assertTrue(true);
        }
    }

    @After
    public void delete() {
        try {
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            Assert.fail();
        }
    }


}
