package com.epam.rd.java.basic.practice7.util;

import com.epam.rd.java.basic.practice7.constants.Constants;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validator {
    private static final Logger LOGGER = Logger.getLogger(Validator.class.getName());

    /**
     * Method checks validation XML file with XSD file.
     * @param xmlFileName
     *      Input XML File.
     * @param xsdFileName
     *      Input XSD File.
     * @return boolean
     */
    public static boolean checkValidate(String xmlFileName, String xsdFileName) {
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);//NOSONAR
            File schemaLocation = new File(xsdFileName);
            Schema schema = factory.newSchema(schemaLocation);
            javax.xml.validation.Validator validator = schema.newValidator(); //NOSONAR
            Source source = new StreamSource(xmlFileName);
            validator.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) {
                    LOGGER.log(Level.SEVERE,exception.getMessage());
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void fatalError(SAXParseException exception) {
                    LOGGER.log(Level.SEVERE,exception.getMessage());
                }
            });


            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean result;

        result = Validator.checkValidate(Constants.XML_FILE_NAME, Constants.SCHEMA_FILE_NAME);
        System.out.println(Constants.XML_FILE_NAME + " is " +
                (result ? "" : "NOT ") + "valid against " + Constants.SCHEMA_FILE_NAME);

       result = Validator.checkValidate(Constants.XML_FILE_PARSED_BY_DOM, Constants.SCHEMA_FILE_NAME);
        System.out.println(Constants.XML_FILE_PARSED_BY_DOM + " is " +
                (result ? "" : "NOT ") + "valid " + Constants.SCHEMA_FILE_NAME);

        result = Validator.checkValidate(Constants.XML_FILE_PARSED_BY_SAX , Constants.SCHEMA_FILE_NAME);
        System.out.println(Constants.XML_FILE_PARSED_BY_SAX + " is " +
                (result ? "" : "NOT ") + "valid " + Constants.SCHEMA_FILE_NAME);

        result = Validator.checkValidate(Constants.XML_FILE_PARSED_BY_STAX , Constants.SCHEMA_FILE_NAME);
        System.out.println("Document " + Constants.XML_FILE_PARSED_BY_STAX + " is " +
                (result ? "" : "NOT ") + "valid against " + Constants.SCHEMA_FILE_NAME);
    }
}


