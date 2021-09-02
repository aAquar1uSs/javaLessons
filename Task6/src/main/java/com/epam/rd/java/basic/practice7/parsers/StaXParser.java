package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.constants.XmlElementEnum;
import com.epam.rd.java.basic.practice7.entity.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaXParser {

    private static final Logger LOGGER = Logger.getLogger(StaXParser.class.getName());
    private final Flowers flowers;
    private final String xmlFileName;
    private final XMLInputFactory inputFactory;

    public StaXParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        flowers = new Flowers();
        inputFactory = XMLInputFactory.newInstance(); //NOSONAR
    }

    /**
     * Parses XML document with StAX.
     */
    public void parseXmlFileBySTAX() {
        FileInputStream fileInputStream = null;
        XMLStreamReader reader;
        String name;
        try {//NOSONAR
            fileInputStream = new FileInputStream(xmlFileName);
            reader = inputFactory.createXMLStreamReader(fileInputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (XmlElementEnum.valueOf(name.toUpperCase()) == XmlElementEnum.FLOWER) {
                        Flower flower = buildFlower(reader);
                        flowers.addFlower(flower);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    /**
     * Method initializes all variables of the class Flower by StaX parser {@link Flower}
     * @param reader
     *      Input XMLStreamReader
     * @return fl
     * @throws XMLStreamException
     *      If XmlStreamReader have a problem.
     */
    private Flower buildFlower(XMLStreamReader reader) throws XMLStreamException {
        Flower fl = new Flower();

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (XmlElementEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            fl.setName(getXMLText(reader));
                            break;
                        case SOIL:
                            fl.setSoil(getXMLText(reader));
                            break;
                        case ORIGIN:
                            fl.setOrigin(getXMLText(reader));
                            break;
                        case VISUALPARAMETERS:
                            fl.setVisualParameters(getXMLVisualParameters(reader));
                            break;
                        case GROWINGTIPS:
                            fl.setGrowingTips(getXMLGrowingTips(reader));
                            break;
                        case MULTIPLYING:
                            fl.setMultiplying(getXMLText(reader));
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (XmlElementEnum.valueOf(name.toUpperCase()) == XmlElementEnum.FLOWER) {
                        return fl;
                    }
                    break;
                default:
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Flower");
    }

    private VisualParameters getXMLVisualParameters(XMLStreamReader reader) throws XMLStreamException {
        VisualParameters vp = new VisualParameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (XmlElementEnum.valueOf(name.toUpperCase())) {
                        case STEMCOLOUR:
                            vp.setStemColour(getXMLText(reader));
                            break;
                        case LEAFCOLOUR:
                            vp.setLeafColour(getXMLText(reader));
                            break;
                        case AVELENFLOWER:
                            vp.setAveLenFlower(getXMLAveLenFlower(reader));
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (XmlElementEnum.valueOf(name.toUpperCase()) == XmlElementEnum.VISUALPARAMETERS) {
                        return vp;
                    }
                    break;
                default:
                    break;
            }

        }
        throw new XMLStreamException("Unknown element in tag Visual Parameters");
    }

    private AveLenFlower getXMLAveLenFlower(XMLStreamReader reader) throws XMLStreamException {
        AveLenFlower aveLenFlower = new AveLenFlower();
        aveLenFlower.setMeasureForLenFlower(reader.getAttributeValue(null, XmlElementEnum.MEASURE.getValue()));
        aveLenFlower.setValueForLenFlower(Integer.parseInt(getXMLText(reader)));
        return aveLenFlower;
    }

    private GrowingTips getXMLGrowingTips(XMLStreamReader reader) throws XMLStreamException {
        GrowingTips growingTips = new GrowingTips();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (XmlElementEnum.valueOf(name.toUpperCase())) {
                        case TEMPRETURE:
                            growingTips.setTempreture(getXMLTempreture(reader));
                            break;
                        case LIGHTING:
                            growingTips.setLighting(getXMLLighting(reader));
                            break;
                        case WATERING:
                            growingTips.setWatering(getXMLWatering(reader));
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (XmlElementEnum.valueOf(name.toUpperCase()) == XmlElementEnum.GROWINGTIPS) {
                        return growingTips;
                    }
                    break;
                default:
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag growing tips");
    }

    private Tempreture getXMLTempreture(XMLStreamReader reader) throws XMLStreamException {
        Tempreture temp = new Tempreture();
        temp.setMeasureForTemperature(reader.getAttributeValue(null, XmlElementEnum.MEASURE.getValue()));
        temp.setAverageTemperature(Integer.parseInt(getXMLText(reader)));
        return temp;
    }

    private Lighting getXMLLighting(XMLStreamReader reader) {
        Lighting lighting = new Lighting();
        lighting.setLightRequiring(reader.getAttributeValue(null, XmlElementEnum.LIGHTREQUIRING.getValue()));
        return lighting;
    }

    private Watering getXMLWatering(XMLStreamReader reader) throws XMLStreamException {
        Watering watering = new Watering();
        watering.setMeasureForWatering(reader.getAttributeValue(null, XmlElementEnum.MEASURE.getValue()));
        watering.setValue(Integer.parseInt(getXMLText(reader)));
        return watering;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }


    public final Flowers getFlowers() {
        return flowers;
    }
}
