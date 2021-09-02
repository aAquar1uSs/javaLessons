package com.epam.rd.java.basic.practice7.testParsers;

import com.epam.rd.java.basic.practice7.util.CreateXML;
import com.epam.rd.java.basic.practice7.entity.Flowers;
import com.epam.rd.java.basic.practice7.parsers.DomParser;
import org.junit.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DomParserTest {
    private DomParser domParser;
    private File file;

    @Before
    public void setUp() {
        domParser = new DomParser("input.xml");
        domParser.parseXmlFileByDOM();
        String xmlFile = "testDom.xml";
        file = new File(xmlFile);
    }

    @Test
    public void shouldGetListOfFlowers() {
        Flowers flowers = domParser.getFlowers();
        int expectedSizeList = 2;
        int actual = flowers.getFlowerList().size();
        Assert.assertEquals(expectedSizeList, actual);
    }

    @Test
    public void shouldSaveToXmlFile() {
        Flowers flowers = domParser.getFlowers();
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
