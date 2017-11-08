package com.github.bfh.study.slb.provider.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Represent the simplest form of a xml tag.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleType {

    @XmlValue
    private String value;

    public String getValue() {
        return value;
    }
}
