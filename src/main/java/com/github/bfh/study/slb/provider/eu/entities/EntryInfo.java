package com.github.bfh.study.slb.provider.eu.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Name, gender, function etc. about a entity
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryInfo extends SubEntry {

    @XmlElement(name = "LASTNAME")
    private String lastName;

    @XmlElement(name = "FIRSTNAME")
    private String firstName;

    @XmlElement(name = "MIDDLENAME")
    private String middleName;

    @XmlElement(name = "WHOLENAME")
    private String wholeName;

    @XmlElement(name = "GENDER")
    private Character gender;

    @XmlElement(name = "FUNCTION")
    private String function;

    @XmlElement(name = "LANGUAGE")
    private String language;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getWholeName() {
        return wholeName;
    }

    public Character getGender() {
        return gender;
    }

    public String getFunction() {
        return function;
    }

    public String getLanguage() {
        return language;
    }
}
