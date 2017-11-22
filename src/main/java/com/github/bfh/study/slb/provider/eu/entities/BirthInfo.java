package com.github.bfh.study.slb.provider.eu.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
public class BirthInfo extends SubEntry {

    @XmlElement(name = "DATE")
    private XMLGregorianCalendar date;

    @XmlElement(name = "PLACE")
    private String place;

    @XmlElement(name = "COUNTRY")
    private String country;

    public XMLGregorianCalendar getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getCountry() {
        return country;
    }
}
