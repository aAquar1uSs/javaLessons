package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.entity.Flowers;
import com.epam.rd.java.basic.practice7.parsers.DomParser;
import com.epam.rd.java.basic.practice7.parsers.SaxParser;
import com.epam.rd.java.basic.practice7.parsers.StaXParser;
import com.epam.rd.java.basic.practice7.util.CreateXML;
import com.epam.rd.java.basic.practice7.util.Sorter;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Main {

    public static void main(final String[] args) {
        if (args.length != 1) {
            System.err.println("Error, please input file");
            return;
        }

        String xmlFile = args[0];

        ///////////////////////////////////////////////////////////////////////////////////////////
        //                          DOM                                                         //
        /////////////////////////////////////////////////////////////////////////////////////////
        DomParser domParser = new DomParser(xmlFile);
        domParser.parseXmlFileByDOM();
        Flowers flowers = domParser.getFlowers();

        Sorter.sortByName(flowers);

        try {
            Document doc = CreateXML.getDocument(flowers);
            CreateXML.saveToXML(doc, Constants.XML_FILE_PARSED_BY_DOM);

        } catch (ParserConfigurationException | TransformerException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, e.getMessage());
        }

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        //                          SAX                                                    //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

        SaxParser saxParser = new SaxParser(xmlFile);
        saxParser.parseXmlFileBySAX();
        flowers = saxParser.getFlowersList();

        Sorter.sortFlowersByFlowersLen(flowers);

        try {
            Document doc2 = CreateXML.getDocument(flowers);
            CreateXML.saveToXML(doc2, Constants.XML_FILE_PARSED_BY_SAX);
        } catch (ParserConfigurationException | TransformerException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, e.getMessage());
        }

        //--------------------------------------------------------------------------------//
        //                         StAX                                                  //
        //------------------------------------------------------------------------------//

        StaXParser staXParser = new StaXParser(xmlFile);
        staXParser.parseXmlFileBySTAX();
        flowers = staXParser.getFlowers();

        Sorter.sortByOrigin(flowers);

        try {
            Document doc3 = CreateXML.getDocument(flowers);
            CreateXML.saveToXML(doc3, Constants.XML_FILE_PARSED_BY_STAX);
        } catch (ParserConfigurationException | TransformerException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, e.getMessage());
        }


    }
}
