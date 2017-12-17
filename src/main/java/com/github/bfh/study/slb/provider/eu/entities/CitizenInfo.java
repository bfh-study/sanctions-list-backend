package com.github.bfh.study.slb.provider.eu.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CitizenInfo extends SubEntry {

    @XmlElement(name = "COUNTRY")
    private String countryIsoCode;

    public String getCountryIsoCode() {
        return countryIsoCode;
    }
}
