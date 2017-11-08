package com.github.bfh.study.slb;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper around JAXB unmarshaller
 */
public class XmlParser {

    private List<Object> listeners;

    private Unmarshaller unmarshaller;

    private XMLReader reader;

    public XmlParser() throws JAXBException {
        this(JAXBContext.newInstance());
    }

    public XmlParser(JAXBContext context) throws JAXBException {
        this.unmarshaller = context.createUnmarshaller();
        listeners = new ArrayList<>();
        // create a new XML parser
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            reader = factory.newSAXParser().getXMLReader();
            reader.setContentHandler(unmarshaller.getUnmarshallerHandler());
        } catch (ParserConfigurationException e ) {
            // @TODO: Handle exception
        } catch (SAXException e) {
            // @TODO: Handle exception
        }
    }

    public void addListener() {

    }

    public void parse() throws IOException, SAXException {
        unmarshaller.setListener(new Unmarshaller.Listener() {
            public void beforeUnmarshal(Object target, Object parent) {
                listeners.forEach(l -> {

                });
            }

            public void afterUnmarshal(Object target, Object parent) {
                listeners.forEach(l -> {

                });
            }
        });

        reader.parse(new File("").toURI().toString());
    }
}
