package com.epam.rd.java.basic.practice7.testParsers;

import com.epam.rd.java.basic.practice7.util.CreateXML;
import com.epam.rd.java.basic.practice7.entity.Flowers;
import com.epam.rd.java.basic.practice7.parsers.SaxParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SaxParserTest {
    private SaxParser saxParser;
    private File file;

    @Before
    public void setUp() {
        String xmlFileName = "saxParserTest.xml";
        file = new File(xmlFileName);
        saxParser = new SaxParser("input.xml");
        saxParser.parseXmlFileBySAX();
    }

    @Test
    public void shouldGetListOfFlowers() {
        Flowers flowers = saxParser.getFlowersList();
        int expectedSizeList = 2;
        int actual = flowers.getFlowerList().size();
        Assert.assertEquals(expectedSizeList, actual);
    }

    @Test
    public void shouldSaveToXmlFile() {
        Flowers flowers = saxParser.getFlowersList();
        try {
            CreateXML.saveToXML(CreateXML.getDocument(flowers), file.getName());
        } catch (ParserConfigurationException | TransformerException e) {
            Assert.fail();
        }

        if (file.exists()) {
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
