package com.github.bfh.study.slb.imports.parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parse a xml file partial. Wraps JAXB unmarshaller and StAX
 *
 * @author Samuel Ackermann
 */
public class PartialXmlParser {

    private Unmarshaller unmarshaller;

    private XMLEventReader reader;

    private FileInputStream inputStream;

    private ElementEventFilter eventFilter;

    public PartialXmlParser(List<ProcessingElement> processingElements) throws JAXBException {
        Class<?>[] classes = processingElements.stream()
                .map(e -> (Class<?>) e.getLinkedClass())
                .collect(Collectors.toList())
                .toArray(new Class<?>[]{});

        this.unmarshaller = JAXBContext.newInstance(classes).createUnmarshaller();
        this.eventFilter = new ElementEventFilter(processingElements);
    }

    public void open(String pathName) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        inputStream = new FileInputStream(pathName);
        reader = xif.createFilteredReader(
                xif.createXMLEventReader(inputStream),
                eventFilter
        );
    }

    public void close() throws XMLStreamException, IOException {
        reader.close();
        inputStream.close();
    }

    public Object parse() throws XMLStreamException, JAXBException {
        Object parsed = null;
        boolean stopped = false;
        while (!stopped && reader.hasNext()) {
            if(reader.peek().isStartElement()) {
                parsed = unmarshaller.unmarshal(reader, eventFilter.getActualClass()).getValue();
                stopped = true;
            }
        }
        return parsed;
    }
}
