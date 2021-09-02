package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.entity.*;
import com.epam.rd.java.basic.practice7.constants.*;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DomParser {
    private static final Logger LOGGER = Logger.getLogger(DomParser.class.getName());

    private final String xmlFileName;

    private final Flowers flowers;

    private DocumentBuilder docBuilder;

    /**
     * Constructor
     * @param xmlFileName
     *      Input XML file.
     *
     */
    public DomParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        flowers = new Flowers();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//NOSONAR
        factory.setNamespaceAware(true);

        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * This method initializes all necessary variable and runs parser xml file by DOM.
     * All received data record to class variables {@link Flower} then flower object add to List {@link Flowers}.
     */
    public void parseXmlFileByDOM() {
        Document doc;
        try {
            doc = docBuilder.parse(xmlFileName);
            Element root = doc.getDocumentElement();
            NodeList flowerList = root.getElementsByTagName(XmlElementEnum.FLOWER.getValue());

            for (int i = 0; i < flowerList.getLength(); i++) {
                Element flowerElement = (Element) flowerList.item(i);
                Flower flower = getFlower(flowerElement);
                flowers.addFlower(flower);
            }

        } catch (IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Method extracts flower object from the flower XML node.
     * @return flower
     */
    private Flower getFlower(Element flowerElement) {
        Flower flower = new Flower();
        //set name
        flower.setName(getElementTextContent(flowerElement, XmlElementEnum.NAME.getValue()));
        //set soil
        flower.setSoil(getElementTextContent(flowerElement, XmlElementEnum.SOIL.getValue()));
        //set origin
        flower.setOrigin(getElementTextContent(flowerElement, XmlElementEnum.ORIGIN.getValue()));
        //set visual parameters
        flower.setVisualParameters(getVisualParameters(flowerElement));
        //set growing tips
        flower.setGrowingTips(getGrowingTips(flowerElement));
        // set multiplying
        flower.setMultiplying(getElementTextContent(flowerElement, XmlElementEnum.MULTIPLYING.getValue()));
        return flower;
    }


    private VisualParameters getVisualParameters(Element flowerElement) {
        VisualParameters visualParameters = new VisualParameters();
        visualParameters.setStemColour(getElementTextContent(flowerElement, XmlElementEnum.STEMCOLOUR.getValue()));
        visualParameters.setLeafColour(getElementTextContent(flowerElement, XmlElementEnum.LEAFCOLOUR.getValue()));
        visualParameters.setAveLenFlower(getLenFlower(flowerElement));
        return visualParameters;
    }


    private AveLenFlower getLenFlower(Element element) {
        AveLenFlower aveLenFlower = new AveLenFlower();
        NodeList nList = element.getElementsByTagName(XmlElementEnum.AVELENFLOWER.getValue());
        Node node = nList.item(0);
        Element el = (Element) node;
        String measure = el.getAttribute(XmlElementEnum.MEASURE.getValue());
        aveLenFlower.setMeasureForLenFlower(measure);

        aveLenFlower.setValueForLenFlower(Integer.parseInt(getElementTextContent(element, XmlElementEnum.AVELENFLOWER.getValue())));

        return aveLenFlower;
    }


    private GrowingTips getGrowingTips(Element element) {
        GrowingTips growingTips = new GrowingTips();
        growingTips.setTempreture(getTempreture(element));
        growingTips.setWatering(getWatering(element));
        growingTips.setLighting(getLighting(element));

        return growingTips;
    }

    private Tempreture getTempreture(Element element) {
        Tempreture tempreture = new Tempreture();
        NodeList nList = element.getElementsByTagName(XmlElementEnum.TEMPRETURE.getValue());
        Node node = nList.item(0);
        Element el = (Element) node;
        String measure = el.getAttribute(XmlElementEnum.MEASURE.getValue());
        tempreture.setMeasureForTemperature(measure);
        tempreture.setAverageTemperature(Integer.parseInt(getElementTextContent(element, XmlElementEnum.TEMPRETURE.getValue())));
        return tempreture;
    }

    private Watering getWatering(Element element) {
        Watering watering = new Watering();
        NodeList nList = element.getElementsByTagName(XmlElementEnum.WATERING.getValue());
        Node node = nList.item(0);
        Element el = (Element) node;
        String measure = el.getAttribute(XmlElementEnum.MEASURE.getValue());
        watering.setMeasureForWatering(measure);
        watering.setValue(Integer.parseInt(getElementTextContent(element, XmlElementEnum.WATERING.getValue())));

        return watering;
    }

    private Lighting getLighting(Element element) {
        Lighting lighting = new Lighting();
        NodeList nList = element.getElementsByTagName(XmlElementEnum.LIGHTING.getValue());
        Node node = nList.item(0);
        Element el = (Element) node;
        String lightingStr = el.getAttribute(XmlElementEnum.LIGHTREQUIRING.getValue());
        lighting.setLightRequiring(lightingStr);
        return lighting;
    }

    /**
     * Method return text content which located in XMl elements.
     * @return node.getTextContent
     */
    private static String getElementTextContent(Element element, String nameElement) {
        NodeList nList = element.getElementsByTagName(nameElement);
        Node node = nList.item(0);
        return node.getTextContent();
    }



    public final Flowers getFlowers() {
        return flowers;
    }

}
