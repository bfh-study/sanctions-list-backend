package com.github.bfh.study.slb.provider.eu.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PassportInfo extends SubEntry {

    @XmlElement(name = "NUMBER")
    private String number;

    @XmlElement(name = "COUNTRY")
    private String country;

    public String getNumber() {
        return number;
    }

    public String getCountry() {
        return country;
    }
}
