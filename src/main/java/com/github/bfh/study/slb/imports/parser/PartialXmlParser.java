package com.github.bfh.study.slb.imports.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 * Parse a xml file partial. Wraps JAXB unmarshaller and StAX
 *
 * @author Samuel Ackermann
 */
public class PartialXmlParser {

    private Unmarshaller unmarshaller;

    private XMLEventReader reader;

    private InputStream inputStream;

    private ElementEventFilter eventFilter;

    /**
     * constructor
     *
     * @param processingElements list of elements that should be parsed
     * @throws JAXBException throws this exception when no JAXB context can be created
     */
    public PartialXmlParser(List<ProcessingElement> processingElements) throws JAXBException {
        Class<?>[] classes = processingElements.stream()
                .map(e -> (Class<?>) e.getLinkedClass())
                .collect(Collectors.toList())
                .toArray(new Class<?>[]{});

        this.unmarshaller = JAXBContext.newInstance(classes).createUnmarshaller();
        this.eventFilter = new ElementEventFilter(processingElements);
    }

    public void open(String pathName) throws FileNotFoundException, XMLStreamException {
        open(new FileInputStream(pathName));
    }

    public void open(URL url) throws IOException, XMLStreamException {
        open(url.openConnection().getInputStream());
    }

    private void open(InputStream inputStream) throws XMLStreamException {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        this.inputStream = inputStream;
        reader = xif.createFilteredReader(
            xif.createXMLEventReader(inputStream),
            eventFilter
        );
    }

    public void close() throws XMLStreamException, IOException {
        reader.close();
        inputStream.close();
    }

    /**
     * parsing the xml file partially
     *
     * @return parsed object
     * @throws XMLStreamException If xml reader stream not open
     * @throws JAXBException If any unexpected errors occur while unmarshalling
     */
    public Object parse() throws XMLStreamException, JAXBException {
        Object parsed = null;
        boolean stopped = false;
        while (!stopped && reader.hasNext()) {
            if (reader.peek().isStartElement()) {
                parsed = unmarshaller.unmarshal(reader, eventFilter.getActualClass()).getValue();
                stopped = true;
            }
        }
        return parsed;
    }
}
