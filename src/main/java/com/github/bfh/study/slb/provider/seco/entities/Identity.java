package com.github.bfh.study.slb.provider.seco.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents a person, organisation or entity.
 *
 * @author Samuel Ackermann
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Identity extends SSIDType {

    @XmlElement(name = "name")
    private NameEntity nameEntity;

    @XmlElement(name = "day-month-year")
    private Birthday birthday;

    @XmlElement(name = "place-of-birth")
    private Birthplace birthplace;

    @XmlElement(name = "identification-document")
    private PassportInfo passportInfo;

    public NameEntity getNameEntity() {
        return nameEntity;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public Birthplace getBirthplace() {
        return birthplace;
    }

    public PassportInfo getPassportInfo() {
        return passportInfo;
    }
}
