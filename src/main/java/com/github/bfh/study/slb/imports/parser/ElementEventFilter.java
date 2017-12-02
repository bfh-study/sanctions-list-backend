package com.github.bfh.study.slb.imports.parser;

import java.util.List;
import java.util.Optional;
import javax.xml.stream.EventFilter;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * This filter accepts only elements that will process with JAXB later.
 *
 * @author Samuel Ackermann
 */
public class ElementEventFilter implements EventFilter {

    private ProcessingElement actualElement;

    private DecisionStore store;

    private List<ProcessingElement> interestedElements;

    public ElementEventFilter(List<ProcessingElement> interestedElements) {
        this.interestedElements = interestedElements;
    }

    @Override
    public boolean accept(XMLEvent event) {
        // Why a store? It could be that the same event is fired several times
        if (store != null && store.event.equals(event)) {
            return store.decision;
        }
        // create new store for storing decision
        store = new DecisionStore(false, event);

        if (actualElement == null && event.isStartElement()) {
            handleStartElement(event.asStartElement());
        } else if (actualElement != null && event.isEndElement()) {
            handleEndElement(event.asEndElement());
        // actualClass != null means a tag was found in the xml that we will parse
        // all sub tags are acceptable
        } else if (actualElement != null) {
            store.decision = true;
        }

        return store.decision;
    }

    public Class<?> getActualClass() {
        if (actualElement != null) {
            return actualElement.getLinkedClass();
        }
        return null;
    }

    private void handleStartElement(StartElement startElement) {
        Optional<ProcessingElement> optional = searchClass(startElement.getName().getLocalPart());
        if (optional.isPresent()) {
            actualElement = optional.get();
            store.decision = true;
        }
    }

    private void handleEndElement(EndElement endElement) {
        String actualTagName = actualElement.getTagName();
        if (actualTagName.equals(endElement.getName().getLocalPart())) {
            actualElement = null;
        }
        store.decision = true;
    }

    private Optional<ProcessingElement> searchClass(String tagName) {
        return interestedElements.stream()
                .filter(element -> element.getTagName().equals(tagName))
                .findFirst();
    }

    /**
     * stores the last decision from the decision tree.
     *
     * @author Samuel Ackermann
     */
    private class DecisionStore {

        boolean decision;

        XMLEvent event;

        DecisionStore(boolean decision, XMLEvent event) {
            this.decision = decision;
            this.event = event;
        }
    }
}
