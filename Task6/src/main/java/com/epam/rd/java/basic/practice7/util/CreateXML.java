package com.epam.rd.java.basic.practice7.util;

import com.epam.rd.java.basic.practice7.constants.XmlElementEnum;
import com.epam.rd.java.basic.practice7.entity.Flower;
import com.epam.rd.java.basic.practice7.entity.Flowers;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


public class CreateXML {

    private CreateXML() {

    }

    /**
     * Method forms a document from flower object {@link Flower}
     *
     * @return Document
     * @throws ParserConfigurationException
     *          Indicates a serious configuration error.
     */
    public static Document getDocument(Flowers flowers) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance(); //NOSONAR
        documentBuilderFactory.setNamespaceAware(true);

        DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();

        Document document = db.newDocument();

        // create root element
        Element rootElement = document.createElement(XmlElementEnum.FLOWERS.getValue());

        document.appendChild(rootElement);

        for (Flower flower : flowers.getFlowerList()) {
            //add flower
            Element flowerElement = document.createElement(XmlElementEnum.FLOWER.getValue());
            rootElement.appendChild(flowerElement);

            //add name
            Element nameElement = document.createElement(XmlElementEnum.NAME.getValue());
            nameElement.setTextContent(flower.getName());
            flowerElement.appendChild(nameElement);

            //add soil
            Element soilElement = document.createElement(XmlElementEnum.SOIL.getValue());
            soilElement.setTextContent(flower.getSoil());
            flowerElement.appendChild(soilElement);

            //origin
            Element originElement = document.createElement(XmlElementEnum.ORIGIN.getValue());
            originElement.setTextContent(flower.getOrigin());
            flowerElement.appendChild(originElement);

            //visualParameters
            Element visualParametersElement = document.createElement(XmlElementEnum.VISUALPARAMETERS.getValue());
            flowerElement.appendChild(visualParametersElement);

            //stem Colour
            Element stemColourElement = document.createElement(XmlElementEnum.STEMCOLOUR.getValue());
            stemColourElement.setTextContent(flower.getVisualParameters().getStemColour());
            visualParametersElement.appendChild(stemColourElement);

            //lead colour
            Element leafColourElement = document.createElement(XmlElementEnum.LEAFCOLOUR.getValue());
            leafColourElement.setTextContent(flower.getVisualParameters().getLeafColour());
            visualParametersElement.appendChild(leafColourElement);

            // aveLenFlower
            Element aveLenFlowerElement = document.createElement(XmlElementEnum.AVELENFLOWER.getValue());
            aveLenFlowerElement.setAttribute(XmlElementEnum.MEASURE.getValue(),
                    flower.getVisualParameters().getAveLenFlower().getMeasureForLenFlower());
            aveLenFlowerElement.setTextContent(Integer.toString(flower.getVisualParameters().getAveLenFlower()
                    .getValueForLenFlower()));
            visualParametersElement.appendChild(aveLenFlowerElement);

            //growing tips
            Element growingTipsElement = document.createElement(XmlElementEnum.GROWINGTIPS.getValue());
            flowerElement.appendChild(growingTipsElement);

            //Temperature
            Element tempretureElement = document.createElement(XmlElementEnum.TEMPRETURE.getValue());
            tempretureElement.setAttribute(XmlElementEnum.MEASURE.getValue(),
                    flower.getGrowingTips().getTempreture().getMeasureForTemperature());
            tempretureElement.setTextContent(Integer.toString(flower.getGrowingTips().getTempreture()
                    .getAverageTemperature()));
            growingTipsElement.appendChild(tempretureElement);

            //lighting
            Element lightingElement = document.createElement(XmlElementEnum.LIGHTING.getValue());
            lightingElement.setAttribute(XmlElementEnum.LIGHTREQUIRING.getValue(), flower.getGrowingTips()
                    .getLighting().getLightRequiring());
            growingTipsElement.appendChild(lightingElement);

            //watering
            Element wateringElement = document.createElement(XmlElementEnum.WATERING.getValue());
            wateringElement.setAttribute(XmlElementEnum.MEASURE.getValue(), flower.getGrowingTips().getWatering()
                    .getMeasure());
            wateringElement.setTextContent(Integer.toString(flower.getGrowingTips().getWatering().getValue()));
            growingTipsElement.appendChild(wateringElement);

            //multiplying
            Element multiplyingElement = document.createElement(XmlElementEnum.MULTIPLYING.getValue());
            multiplyingElement.setTextContent(flower.getMultiplying());
            flowerElement.appendChild(multiplyingElement);
        }
        return document;
    }

    /**
     * Method transform variable document to XML File.
     * @throws TransformerException
     *          If error during the transformation process.
     *
     */
    public static void saveToXML(final Document document,
                                 final String xmlFileName) throws TransformerException {

        StreamResult result = new StreamResult(new File(xmlFileName));

        TransformerFactory tf = TransformerFactory.newInstance(); //NOSONAR

        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        t.transform(new DOMSource(document), result);
    }
}
