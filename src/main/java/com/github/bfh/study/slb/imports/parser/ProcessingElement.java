package com.github.bfh.study.slb.imports.parser;

/**
 * Defines which element will be process and converted in the linked class
 *
 * @author Samuel Ackermann
 */
public class ProcessingElement {

    private Class<?> clazz;

    private String tagName;

    public ProcessingElement(String tagName, Class<?> clazz) {
        this.tagName = tagName;
        this.clazz = clazz;
    }

    public Class<?> getLinkedClass() {
        return clazz;
    }

    public String getTagName() {
        return tagName;
    }
}
