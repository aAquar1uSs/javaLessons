package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.constants.XmlElementEnum;
import com.epam.rd.java.basic.practice7.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaxParser extends DefaultHandler {
    private static final Logger LOGGER = Logger.getLogger(SaxParser.class.getName());
    private final String xmlFileName;
    private String currentElement;
    private Flowers flowers;
    private Flower flower;
    //------------------------------------------
    private VisualParameters visualParameters;
    private AveLenFlower aveLenFlower;
    //-----------------------------------------
    private GrowingTips growingTips;
    private Tempreture tempreture;
    private Lighting lighting;
    private Watering watering;

    /**
     * Constructor
     * @param xmlFileName
     *      Input XML file
     */
    public SaxParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     *  Method starts the parser XML file by SAX.
     */
    public final void parseXmlFileBySAX() {
        SAXParserFactory factory = SAXParserFactory.newInstance();//NOSONAR

        factory.setNamespaceAware(true);
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
            parser.parse(xmlFileName, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public final void startElement(final String uri, final String localName,
                                   final String qName, final Attributes attributes) {

        currentElement = qName;
        if (XmlElementEnum.FLOWERS.equalsTo(currentElement)) {
            flowers = new Flowers();
            return;
        }

        if (XmlElementEnum.FLOWER.equalsTo(currentElement)) {
            flower = new Flower();
            return;
        }

        if (XmlElementEnum.VISUALPARAMETERS.equalsTo(currentElement)) {
            visualParameters = new VisualParameters();
            return;
        }

        if (XmlElementEnum.AVELENFLOWER.equalsTo(currentElement)) {
            aveLenFlower = new AveLenFlower();
            aveLenFlower.setMeasureForLenFlower(attributes.getValue(XmlElementEnum.MEASURE.getValue()));
            return;
        }

        if (XmlElementEnum.GROWINGTIPS.equalsTo(currentElement)) {
            growingTips = new GrowingTips();
            return;
        }

        if (XmlElementEnum.TEMPRETURE.equalsTo(currentElement)) {
            tempreture = new Tempreture();
            tempreture.setMeasureForTemperature(attributes.getValue(XmlElementEnum.MEASURE.getValue()));
            return;
        }

        if (XmlElementEnum.LIGHTING.equalsTo(currentElement)) {
            lighting = new Lighting();
            lighting.setLightRequiring(attributes.getValue(XmlElementEnum.LIGHTREQUIRING.getValue()));
            return;
        }

        if (XmlElementEnum.WATERING.equalsTo(currentElement)) {
            watering = new Watering();
            watering.setMeasureForWatering(attributes.getValue(XmlElementEnum.MEASURE.getValue()));
        }

    }

    @Override
    public final void characters(final char[] ch, final int start,
                                 final int length) {

        String elementText = new String(ch, start, length).trim();

        // return if content is empty
        if (elementText.isEmpty()) {
            return;
        }

        if (XmlElementEnum.NAME.equalsTo(currentElement)) {
            flower.setName(elementText);
            return;
        }

        if (XmlElementEnum.SOIL.equalsTo(currentElement)) {
            flower.setSoil(elementText);
            return;
        }

        if (XmlElementEnum.ORIGIN.equalsTo(currentElement)) {
            flower.setOrigin(elementText);
            return;
        }

        if (XmlElementEnum.STEMCOLOUR.equalsTo(currentElement)) {
            visualParameters.setStemColour(elementText);
            return;
        }

        if (XmlElementEnum.LEAFCOLOUR.equalsTo(currentElement)) {
            visualParameters.setLeafColour(elementText);
            return;
        }

        if (XmlElementEnum.AVELENFLOWER.equalsTo(currentElement)) {
            aveLenFlower.setValueForLenFlower(Integer.parseInt(elementText));
            return;
        }

        if (XmlElementEnum.TEMPRETURE.equalsTo(currentElement)) {
            tempreture.setAverageTemperature(Integer.parseInt(elementText));
            return;
        }

        if (XmlElementEnum.WATERING.equalsTo(currentElement)) {
            watering.setValue(Integer.parseInt(elementText));
            return;
        }

        if (XmlElementEnum.MULTIPLYING.equalsTo(currentElement)) {
            flower.setMultiplying(elementText);
        }

    }

    @Override
    public final void endElement(final String uri, final String localName,
                                                    final String qName)  {
        if (XmlElementEnum.FLOWER.equalsTo(localName)) {
            flowers.addFlower(flower);
            flower = null;
            return;
        }

        if (XmlElementEnum.VISUALPARAMETERS.equalsTo(localName)) {
            visualParameters.setAveLenFlower(aveLenFlower);
            flower.setVisualParameters(visualParameters);
            visualParameters = null;
            return;
        }

        if (XmlElementEnum.GROWINGTIPS.equalsTo(localName)) {
            growingTips.setTempreture(tempreture);
            growingTips.setLighting(lighting);
            growingTips.setWatering(watering);
            flower.setGrowingTips(growingTips);
            growingTips = null;
        }
    }


    public final Flowers getFlowersList() {
        return flowers;
    }
}


